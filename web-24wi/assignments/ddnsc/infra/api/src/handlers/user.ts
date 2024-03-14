import prisma from '../db'
import { comparePasswords, createJWT, hashPassword } from '../modules/auth'

export const createNewUser = async (req: { body: { username: any; password: string } }, res: { json: (arg0: { token: string }) => void }) => {
    const user = await prisma.user.create({
        data: {
            username: req.body.username,
            password: await hashPassword(req.body.password)
        }
    })

    const token = createJWT(user)
    res.json({ token })
}

export const signin = async (req: { body: { username: any; password: string; }; }, res: { status: (arg0: number) => void; json: (arg0: { message?: string; token?: string; }) => void; }) => {
    const user = await prisma.user.findUnique({
        where: {
            username: req.body.username
        }
    })

    const isValid = user ? await comparePasswords(req.body.password, user.password) : false;

    if (!isValid) {
        res.status(401)
        res.json({message: 'nope'})
        return
    }

    if (user !== null) {
        const token = createJWT(user);
        res.json({ token });
    } else {
        // Handle the case where user is null
    }
}