import app from "./server"
import * as dotenv from "dotenv";
import { PrismaClient } from '@prisma/client'

dotenv.config();
const prisma = new PrismaClient()

app.listen(5000, () => {
    console.log('Hello from express, please visit http://AkinaSS.arcology.builders:5000/')
    console.log('This is a test')
})

app.get('/users', async (req, res) => {
  const users = await prisma.user.findMany()
  res.json(users)
})

app.post(`/user`, async (req, res) => {
  const result = await prisma.user.create({
    data: { ...req.body },
  })
  res.json(result)
})

/*
async function main() {
  // ... your Prisma Client queries will go here
  const newUser = await prisma.user.create({
    data: {
      username: 'Alice',
      password: 'alice@prisma.io',
      Product: {
        create: {
          name: 'Hello World',
        },
      },
    },
  })
  console.log('Created new user: ', newUser)

  const allUsers = await prisma.user.findMany({
    include: { Product: true },
  })
  console.log('All users: ')
  console.dir(allUsers, { depth: null })
}

main()
  .catch((e) => console.error(e))
  .finally(async () => await prisma.$disconnect())
*/