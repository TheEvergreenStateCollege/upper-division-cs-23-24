import {
    createNewUser,
    signin,
    updateUser,
    updatePass,
    deletUser,
    logout,
} from '../user';

const newUser = "newUser3"
const newPassword = "password123"

describe('User handler', () => {
    // syntax:
    // it('should handle a scenario when something happens', async () => {
    //     // test logic and expectations
    //     expect(/* expectation */).toBe(/* expected value */);
    // });

    // createNewUser
    it('should create a new user successfully', async () => {
        const req = {
            body: {
                Username: `${newUser}`,
                Password: `${newPassword}`,
            },
        };
        const res = {
            send: jest.fn(),
            cookie: jest.fn(),
            redirect: jest.fn(),
        };

        jest.mock('../../db', () => ({
            __esModule: true,
            default: {
                user: {
                    findUnique: jest.fn().mockResolvedValueOnce(null),
                    create: jest.fn().mockResolvedValueOnce({ id: '1', username: 'newUser2' }),
                },
            },
        }));

        await createNewUser(req, res);

        // expect(res.cookie).toHaveBeenCalledWith('token', expect.any(Array));
        expect(res.cookie).toHaveBeenCalledWith('user', 'newUser2');
        expect(res.redirect).toHaveBeenCalledWith('/api/profile');
    });

    it('should handle an existing user during creation', async () => {
        const req = {
            body: {
                Username: 'existingUser',
                Password: 'password123',
            },
        };
        const res = {
            send: jest.fn(),
            cookie: jest.fn(),
            redirect: jest.fn(),
        };

        jest.mock('../../db', () => ({
            __esModule: true,
            default: {
                user: {
                    findUnique: jest.fn().mockResolvedValueOnce({ id: '1', username: 'existingUser' }),
                },
            },
        }));

        await createNewUser(req, res);

        expect(res.send).toHaveBeenCalledWith({ ok: false, message: 'User already exists' });
        expect(res.cookie).not.toHaveBeenCalled();
        expect(res.redirect).not.toHaveBeenCalled();
    });

    // signIn
    it('should sign in a user successfully', async () => {
        const req = {
            body: {
                Username: `${newUser}`,
                Password: `${newPassword}`,
            },
        };
        const res = {
            cookie: jest.fn(),
            redirect: jest.fn(),
            status: jest.fn(),
            send: jest.fn(),
        };

        jest.mock('../../db', () => ({
            __esModule: true,
            default: {
                user: {
                    findUnique: jest.fn().mockResolvedValueOnce({ id: '1', username: 'newUser2', password: 'hashedPassword' }),
                },
            },
        }));

        jest.mock('../../modules/auth', () => ({
            __esModule: true,
            comparePasswords: jest.fn().mockResolvedValueOnce(true),
            createJWT: jest.fn().mockReturnValueOnce('mockedJWT'),
        }));

        await signin(req, res);

        expect(res.cookie).toHaveBeenCalledWith('token', 'mockedJWT');
        expect(res.cookie).toHaveBeenCalledWith('user', 'existingUser');
        expect(res.redirect).toHaveBeenCalledWith('/api/profile');
    });

    it('should handle sign-in failure with incorrect password', async () => {
        const req = {
            body: {
                Username: 'existingUser',
                Password: 'incorrectPassword',
            },
        };
        const res = {
            status: jest.fn(),
            send: jest.fn(),
        };

        jest.mock('../../db', () => ({
            __esModule: true,
            default: {
                user: {
                    findUnique: jest.fn().mockResolvedValueOnce({ id: '1', username: 'existingUser', password: 'hashedPassword' }),
                },
            },
        }));

        jest.mock('../../modules/auth', () => ({
            __esModule: true,
            comparePasswords: jest.fn().mockResolvedValueOnce(false),
        }));

        await signin(req, res);

        expect(res.status).toHaveBeenCalledWith(401);
        expect(res.send).toHaveBeenCalledWith('Invalid username or password');
    });

    // logout
    it('should logout a user successfully', async () => {
        const req = {};
        const res = {
            clearCookie: jest.fn(),
            redirect: jest.fn(),
        };

        await logout(req, res);

        expect(res.clearCookie).toHaveBeenCalledWith('user');
        expect(res.clearCookie).toHaveBeenCalledWith('token');
        expect(res.redirect).toHaveBeenCalledWith('/');
    });
});
