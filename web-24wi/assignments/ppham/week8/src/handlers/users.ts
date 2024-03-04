import prisma from '../db';
import { Prisma, User } from '@prisma/client';
import { comparePasswords, createJWT, hashPassword } from '../modules/auth';
import { Request, Response } from 'express';

export const createNewUser = async ( req: Request, res: Response ) => {
  try {
    const user: User = await prisma.user.create({
      data: {
        username: req.body.username,
        password: await hashPassword(req.body.password),
      }
    });
  
    const token = createJWT(user);
    res.json({ token });
  
  } catch(e) {
    res.status(401);
    res.json({ message: `username ${req.body.username} already exists. Try logging in.` });
  }
}

export const getAllUsers = async ( req: Request, res: Response ) => {
  const allUsers: User[] = await prisma.user.findMany();

  res.json(allUsers);
}

export const signin = async ( req: Request, res: Response ) => {
  const user = await prisma.user.findFirst({
    where: {
      username: req.body.username
    }
  });

  if (!user) {
    res.status(401);
    res.json({ message: `username ${req.body.username} not found` });
    return;
  }
  const isValid = await comparePasswords(req.body.password, user.password);

  if (!isValid) {
    res.status(401);
    res.json({ message: "invalid password" });
    return;
  }

  const token = createJWT(user);
  res.json({ token });
}
