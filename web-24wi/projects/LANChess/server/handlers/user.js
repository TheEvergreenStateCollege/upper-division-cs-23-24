import prisma from "../db.js";
import { comparePasswords, createJWT, hashPassword } from "../modules/auth.js";

// auth handlers
export const createNewUser = async (req, res) => {
    console.log(req.body.username);
    const user = await prisma.user.create({
        data: {
            username: req.body.username,
            password: await hashPassword(req.body.password),
        }

    })
    const token = createJWT(user);
    res.json({ token, id: user.id });
}

export const signin = async (req, res) => {
    const user = await prisma.user.findUnique({
        where: {
            username: req.body.username,
        }
    })
    console.log();
    if (user == null) {
        res.status(401);
        res.json("incorrect username");
        return;
    }
    const isValid = await comparePasswords(req.body.password, user.password);
    if (!isValid) {
        res.status(401);
        res.json({ message: "failed login" });
        return;
    }

    const token = createJWT(user);
    res.json({ token, id: user.id });
}

// api handlers
export const getOneUser = async (req, res) => {
    const user = await prisma.user.findFirst({
        where: {
            id: req.params.id,
        }
    });
    res.json({ data: user });
}

export const getUsers = async (req, res) => {
    const users = await prisma.user.findMany();
    res.json({ data: users });
}

export const updateUser = async (req, res) => {
    const user = await prisma.user.update({
        where: {
            id: req.params.id,
        },
        data: {
            username: req.body.username,
        }
    });
}


export const deleteUser = async (req, res) => {
    const deleted = await prisma.user.delete({
        where: {
            id: req.params.id,
        }
    });
    res.json({ data: deleted });
}
