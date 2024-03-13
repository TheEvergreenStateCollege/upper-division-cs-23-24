import { User } from '@prisma/client'
import { Response, NextFunction } from 'express'
import { Request } from '../types/express'
import jwt from 'jsonwebtoken'
import bcrypt from 'bcrypt'

export const comparePasswords = (password: string, hash: string) => {
    return bcrypt.compare(password, hash)
}

export const hashPassword = (password: string) => {
    return bcrypt.hash(password, 5)
}

export const createJWT = (user: User) => {
    const token = jwt.sign({
            id: user.id,
            username: user.username
        },
        process.env.JWT_SECRET!
    );
    return token;
}

export const protect = (req: Request, res: Response, next: NextFunction) => {
    const bearer = req.headers.authorization;

    if (!bearer) {
        res.status(401);
        res.json({ message: 'not authorized' });
        return;
    }

    const [, token] = bearer.split(' ');

    if (!token) {
        res.status(401);
        res.json({ message: 'not valid token' });
        return;
    }

    try {
        // add payload from Paul's auth.ts
        const user = jwt.verify(token, process.env.JWT_SECRET!)
        //req.user = user
        next()
    } catch (e) {
        console.error(e)
        res.status(401)
        res.json({message: 'not valid token'})
        return
    }
}