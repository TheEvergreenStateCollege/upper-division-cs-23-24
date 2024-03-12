import {
    createNewUser,
    signin,
    updateUser,
    updatePass,
    deletUser,
    logout,
} from '../user';

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
                Username: 'newUser',
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
                    findUnique: jest.fn().mockResolvedValueOnce(null),
                    create: jest.fn().mockResolvedValueOnce({ id: 1, username: 'newUser' }),
                },
            },
        }));

        await createNewUser(req, res);

        expect(res.cookie).toHaveBeenCalledWith('token', expect.any(String));
        expect(res.cookie).toHaveBeenCalledWith('user', 'newUser');
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
                    findUnique: jest.fn().mockResolvedValueOnce({ id: 1, username: 'existingUser' }),
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
                Username: 'existingUser',
                Password: 'correctPassword',
            },
        };
        const res = {
            cookie: jest.fn(),
            redirect: jest.fn(),
            status: jest.fn(),
            send: jest.fn(),
        };

        jest.mock('../db', () => ({
            __esModule: true,
            default: {
                user: {
                    findUnique: jest.fn().mockResolvedValueOnce({ id: 1, username: 'existingUser', password: 'hashedPassword' }),
                },
            },
        }));

        jest.mock('../modules/auth', () => ({
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

        jest.mock('../db', () => ({
            __esModule: true,
            default: {
                user: {
                    findUnique: jest.fn().mockResolvedValueOnce({ id: 1, username: 'existingUser', password: 'hashedPassword' }),
                },
            },
        }));

        jest.mock('../modules/auth', () => ({
            __esModule: true,
            comparePasswords: jest.fn().mockResolvedValueOnce(false),
        }));

        await signin(req, res);

        expect(res.status).toHaveBeenCalledWith(401);
        expect(res.send).toHaveBeenCalledWith('Invalid username or password');
    });

    // updateUser
    it('should update user information successfully', async () => {
        const req = {
            user: { id: 1 },
            body: {
                Username: 'newUsername',
            },
        };
        const res = {
            cookie: jest.fn(),
            redirect: jest.fn(),
        };

        jest.mock('../db', () => ({
            __esModule: true,
            default: {
                user: {
                    update: jest.fn().mockResolvedValueOnce({ id: 1, username: 'newUsername' }),
                },
            },
        }));

        await updateUser(req, res);

        expect(res.cookie).toHaveBeenCalledWith('user', 'newUsername');
        expect(res.redirect).toHaveBeenCalledWith('/api/profile');
    });

    // updatePass
    it('should update user password successfully', async () => {
        const req = {
            user: { id: 1 },
            body: {
                Password: 'newPassword',
            },
        };
        const res = {
            redirect: jest.fn(),
        };

        jest.mock('../db', () => ({
            __esModule: true,
            default: {
                user: {
                    update: jest.fn().mockResolvedValueOnce({ id: 1 }),
                },
            },
        }));

        jest.mock('../modules/auth', () => ({
            __esModule: true,
            hashPassword: jest.fn().mockResolvedValueOnce('hashedNewPassword'),
        }));

        await updatePass(req, res);

        expect(res.redirect).toHaveBeenCalledWith('/api/profile');
    });

    // deleteUser
    it('should delete a user successfully', async () => {
        const req = {
            user: { id: 1 },
        };
        const res = {
            json: jest.fn(),
            redirect: jest.fn(),
        };

        jest.mock('../db', () => ({
            __esModule: true,
            default: {
                user: {
                    delete: jest.fn().mockResolvedValueOnce({ id: 1 }),
                },
            },
        }));

        await deletUser(req, res);

        expect(res.json).toHaveBeenCalledWith({ data: { id: 1 } });
        expect(res.redirect).toHaveBeenCalledWith('/');
    });

    it('should handle user deletion failure', async () => {
        const req = {
            user: { id: 1 },
        };
        const res = {
            json: jest.fn(),
            redirect: jest.fn(),
        };

        jest.mock('../db', () => ({
            __esModule: true,
            default: {
                user: {
                    delete: jest.fn().mockRejectedValueOnce(new Error('Deletion error')),
                },
            },
        }));

        await deletUser(req, res);

        expect(res.json).toHaveBeenCalledWith({ error: 'Deletion error' });
        expect(res.redirect).toHaveBeenCalledWith('/');
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
