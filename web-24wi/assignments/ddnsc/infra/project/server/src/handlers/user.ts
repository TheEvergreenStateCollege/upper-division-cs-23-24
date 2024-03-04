import {createJWT, hashPassword, comparePasswords} from "../auth/auth";
import prisma from "../db/db";

export const createNewUser = async (req, res) => {
    const { username, password, email } = req.body;

    // Input validation
    if (!username || !password || !email) {
        return res.status(400).json({ error: "Username, password, and email are required." });
    }

    try {
        const user = await prisma.user.create({
            data: {
                username,
                password: await hashPassword(password),
            },
        });

        const token = createJWT(user);
        res.json({ token });
    } catch (error) {
        console.error('Error creating user:', error);
        res.status(500).json({ error: 'Internal Server Error' });
    }
};


export const signIn = async (req, res) => {
    const { username, password } = req.body;

    try {
        const user = await prisma.user.findUnique({
            where: {
                username,
            },
        });

        if (!user) {
            res.status(401).json({ message: 'Invalid credentials' });
            return;
        }

        const isValid = await comparePasswords(password, user.password);

        if (!isValid) {
            res.status(401).json({ message: 'Invalid credentials' });
            return;
        }

        const token = createJWT(user);
        res.json({ token });
    } catch (error) {
        console.error('Error during sign-in:', error);
        res.status(500).json({ error: 'Internal Server Error' });
    }
};
