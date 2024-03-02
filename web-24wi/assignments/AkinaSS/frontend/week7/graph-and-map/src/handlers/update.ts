import { PrismaClient } from "@prisma/client";
import app from '../server';

const prisma = new PrismaClient()

app.get('/users', async (req, res) => {
    const users = await prisma.user.findMany()
    res.json(users)
})

app.post(`/update`, async (req, res) => {
    const { title, content, authorEmail } = req.body
    const result = await prisma.update.create({
      data: {
        title: "Product",
        body: "Description",
        id: "Update",
        version: "1.0",
      },
    })
    res.json(result)
})