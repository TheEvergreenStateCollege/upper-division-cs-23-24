import jwt from 'jsonwebtoken'
import * as bcrypt from 'bcrypt'
require('dotenv').config();

export const comparePasswords = (password: string | Buffer, hash: string)=> {
    return bcrypt.compare(password, hash);
};

export const hashPassword = (password: string | Buffer) => {
    return bcrypt.hash(password, 5);
};

export const createJWT = (user: { id: any; createdAt?: Date; username: any; password?: string; }) => {
    return jwt.sign({
            id: user.id,
            username: user.username
        },
        process.env.JWT_SECRET
    )
}

export const protect = (req: { headers: { authorization: any; }; user: jwt.Jwt & jwt.JwtPayload & void; }, res: {
    status: (arg0: number) => void;
    json: (arg0: { message: string; }) => void;
}, next: () => void) => {
    const bearer = req.headers.authorization
    if(!bearer) {
        res.status(401)
        res.json({message: 'not authorized'})
        return
    }

    const [, token] = bearer.split(' ');
    if (!token) {
        res.status(401);
        res.json({message: 'invalid token'});
        return
    }

    try {
        req.user = jwt.verify(token, process.env.JWT_SECRET)
        next();
    } catch (e) {
        console.error(e)
        res.status(401);
        res.json({message: 'invalid token'});
        return
    }
}