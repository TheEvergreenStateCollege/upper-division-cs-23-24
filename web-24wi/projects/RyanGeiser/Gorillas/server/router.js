import { Router } from "express";
import { PrismaClient } from "@prisma/client";
import bcrypt from 'bcrypt';

const router = Router();
const prisma = new PrismaClient();

router.get("/users", async (req, res) => {
    try {
        const allUsers = await prisma.user.findMany();
        res.json(allUsers);
    } catch (error) {
        console.error("Error fetching users:", error);
        res.status(500).json({ error: "Internal server error" });
    }
});

router.get("/user/:id", async (req, res) => {
    try {
        const userId = parseInt(req.params.id);
        if (isNaN(userId)) {
            return res.status(400).json({ error: "Invalid user ID" });
        }

        const user = await prisma.user.findUnique({
            where: {
                id: userId
            }
        });

        if (!user) {
            return res.status(404).json({ error: "User not found" });
        }

        res.json(user);
    } catch (error) {
        console.error("Error fetching user:", error);
        res.status(500).json({ error: "Internal server error" });
    }
});

router.post("/user", async (req, res) => {
    try {
        const { username, password } = req.body;

        // Validate input data
        if (!username || !password) {
            return res.status(400).json({ error: "Username and password are required" });
        }

        // Hash the password
        const hashedPassword = await bcrypt.hash(password, 10);

        const newUser = await prisma.user.create({
            data: {
                username,
                password: hashedPassword
            }
        });

        res.status(201).json(newUser);
    } catch (error) {
        console.error("Error creating user:", error);
        res.status(500).json({ error: "Internal server error" });
    }
});

router.put("/user/:id", async (req, res) => {
    try {
        const userId = parseInt(req.params.id);
        if (isNaN(userId)) {
            return res.status(400).json({ error: "Invalid user ID" });
        }

        const updatedUser = await prisma.user.update({
            where: {
                id: userId
            },
            data: req.body
        });

        res.json(updatedUser);
    } catch (error) {
        console.error("Error updating user:", error);
        res.status(500).json({ error: "Internal server error" });
    }
});

router.delete("/user/:id", async (req, res) => {
    try {
        const userId = parseInt(req.params.id);
        if (isNaN(userId)) {
            return res.status(400).json({ error: "Invalid user ID" });
        }

        await prisma.user.delete({
            where: {
                id: userId
            }
        });

        res.status(204).end();
    } catch (error) {
        console.error("Error deleting user:", error);
        res.status(500).json({ error: "Internal server error" });
    }
});

export default router;
