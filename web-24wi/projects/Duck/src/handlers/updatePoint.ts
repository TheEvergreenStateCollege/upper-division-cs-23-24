import { PrismaClient } from "@prisma/client";
import app from '../server';

const prisma = new PrismaClient()

app.get('/updatepoint', async (req, res) => {
    const posts = await prisma.updatePoint.findMany({
        //placeholder for now
    })
    res.json(posts)
  })