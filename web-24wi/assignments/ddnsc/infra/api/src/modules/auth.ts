import jwt, { JwtPayload } from 'jsonwebtoken';
import bcrypt from 'bcrypt';

export const comparePasswords = async (password: string, hash: string) => {
    return await bcrypt.compare(password, hash);
};

export const hashPassword = async (password: string) => {
    return await bcrypt.hash(password, 10); // Increase the number of salt rounds for better security
};

export const createJWT = (user: { id: string; username: string }) => {
    const jwtSecret = process.env.JWT_SECRET || 'defaultSecret'; // Provide a default value if JWT_SECRET is undefined
    const token = jwt.sign({
        id: user.id,
        username: user.username
    }, jwtSecret, { expiresIn: '1h' }); // Set an expiration time for the token
    return token;
};

export const protect = (req: any, res: any, next: any) => {
    const bearer = req.headers.authorization;

    if (!bearer) {
        return res.status(401).json({ message: 'Unauthorized' });
    }

    const [, token] = bearer.split(' ');

    if (!token) {
        return res.status(401).json({ message: 'Invalid token' });
    }

    try {
        const jwtSecret = process.env.JWT_SECRET || 'defaultSecret'; // Provide a default value if JWT_SECRET is undefined
        const user = jwt.verify(token, jwtSecret) as JwtPayload;
        req.user = user;
        next();
    } catch (e) {
        console.error(e);
        return res.status(401).json({ message: 'Invalid token' });
    }
};
