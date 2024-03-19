import prisma from "../db";
import { USCity } from '@prisma/client';
import { Request, Response } from 'express';
import { validationResult } from "express-validator";

export const getOneCity = async ( req: Request, res: Response ) => {
  const city = await prisma.uSCity.findFirst({
    where: {
      name: req.params.name
    }
  });

  res.json({ data: city });
}

export const getCities = async ( req: Request, res: Response ) => {
  const allCities = await prisma.uSCity.findMany();

  res.json({ data: allCities });
}

export const createCity = async ( req: Request, res: Response ) => {
  const errors = validationResult(req);

  
  if (!errors.isEmpty()) {
    res.statusCode = 400;
    res.json({ errors: errors.array() });
  }

  const city = await prisma.uSCity.create({
    data: {
      name: req.body.name,
      longitude: Number(req.body.longitude),
      latitude: Number(req.body.latitude),
      population: Number(req.body.population),
      authorId: Number(req.user!.id),
    }
  });

  res.json({ data: city });
}
