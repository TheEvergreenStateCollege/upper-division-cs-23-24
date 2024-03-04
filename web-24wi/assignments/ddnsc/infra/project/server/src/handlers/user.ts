import {createJWT, hashPassword} from "../auth/auth";
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
