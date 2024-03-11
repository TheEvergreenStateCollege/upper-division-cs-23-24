import { Router } from "express";
import { PrismaClient } from "@prisma/client";

const router = Router();
const prisma = new PrismaClient();

router.get("/users", async (req, res) => {
    const allUsers = await prisma.users.findMany();
    console.log(req.body);
    res.json(allUsers);
});

router.get("/user/:id", (req, res) => {
    console.log(req);
    console.log("GET");
});

router.post("/user", async (req, res) => {
    console.log(req.body);
    console.log("POST");

    const newUser = await prisma.users.create({
        data: {
            username: req.body.username,
            password: '',
        }
    })
});

router.put("/user/:id", (req, res) => {
    console.log(req.body);
    console.log("PUT");
});

router.delete("/user/:id", (req, res) => {
    console.log(req.body);
    console.log("DELETE");
});

export default router;
