import prisma from "../db.js";
import { comparePasswords, createJWT, hashPassword } from "../modules/auth.js";

export const createNewUser = async (req, res) => {
    console.log(req.body.username);
    const user = await prisma.user.create({
        data: {
            username: req.body.username,
            password: await hashPassword(req.body.password),
        }

    })

    const token = createJWT(user);
    res.json({ token });
}

export const signin = async (req, res) => {
    const user = await prisma.user.findUnique({
        where: {
            username: req.body.username,
        }
    })

    const isValid = await comparePasswords(req.body.password, user.password);
    if (!isValid) {
        res.status(401);
        res.json({ message: "failed login" });
        return;
    }

    const token = createJWT(user);
    res.json({ token });
}
