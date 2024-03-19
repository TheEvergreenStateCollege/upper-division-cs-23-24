import prisma from "../db";
import { Word } from '@prisma/client';
import { Request, Response } from 'express';
import { validationResult } from "express-validator";

export const getOneWord = async ( req: Request, res: Response ) => {
    const word = await prisma.word.findFirst({
      where: {
        authorId: Number(req.user!.id)
      }
    });
  
    res.json({ data: word });
}

export const getWords = async ( req: Request, res: Response ) => {
    const allWords = await prisma.word.findMany({
      where: {
        authorId: Number(req.user!.id)
      }
    });
    res.json({ data: allWords });
}

export const deleteWord = async ( req: Request, res: Response ) => {
    const deleted = await prisma.word.delete({
        where: {
            id:Number(req.user!.id)
        }
    });
    res.json({ data: deleteWord });
}

export const createWord = async ( req: Request, res: Response ) => {
    const errors = validationResult(req);
  
    
    if (!errors.isEmpty()) {
      res.statusCode = 400;
      res.json({ errors: errors.array() });
    }

    const word = await prisma.word.create({
        data: {
          name: req.body.name,
          define: req.body.define,
          language: req.body.language,
          authorId: Number(req.user!.id),
        }
      });
      res.json({ data: word });
}