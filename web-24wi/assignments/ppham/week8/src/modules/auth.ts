import jwt, { JwtPayload } from 'jsonwebtoken';
import bcrypt from 'bcrypt';

import { Response, NextFunction } from 'express';
import { Request } from '../types/express';
import { User } from '@prisma/client';

export const comparePasswords = async (password: string, hash: string): Promise<boolean> => {
  return await bcrypt.compare(password, hash);
}

export const hashPassword = (password: string) => {
  return bcrypt.hash(password, 5);
}

export const createJWT = (user: User ) => {
  const token = jwt.sign({
     id: user.id,
     username: user.username,
    },
    process.env.JWT_SECRET!
  );
  return token;
}

export const protect = ( req: Request, res: Response, next: NextFunction ) => {
  const bearer = req.headers.authorization;

  if (!bearer) {
    res.status(401);
    res.json({ message: "not authorized" });
    return;
  }

  const [, token] = bearer.split(' ');

  if (!token) {
    res.status(401);
    res.json({ message: "not valid token" });
    return;
  }

  try {
    const authenticationToken: string | JwtPayload = jwt.verify(token, process.env.JWT_SECRET!);
    if (!req.user) {
      req.user = { authenticationToken };
    } else {
      req.user.authenticationToken = authenticationToken;
    }
    next();
  } catch (e) {
    console.error(e);
    res.status(401);
    res.json({ message: "not valid token" });
    return;
  }
}
