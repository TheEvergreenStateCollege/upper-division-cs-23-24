import { PrismaClient } from "@prisma/client";
import app from '../server';

const prisma = new PrismaClient()

app.get('/user', async (req, res) => {
  const users = await prisma.user.findUnique(username)
  res.json(users)
})

app.get(`/product/:id`, async (req, res) => {
    const { id } = req.params
    const post = await prisma.product.findUnique({
      where: { id: String(id) },
    })
    res.json(post)
})

app.post(`/product`, async (req, res) => {
    const { title, content, authorEmail } = req.body
    const result = await prisma.product.create({
      data: {
        id: "Product",
        name: "User",
        belongsToId: "User",
      },
    })
    res.json(result)
})