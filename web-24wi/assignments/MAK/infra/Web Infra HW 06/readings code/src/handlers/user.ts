import prisma from "../db"
import { createJWT, hashPassword, comparePasswords } from "../modules/auth"

//creates a new user
export const createNewUser = async (req, res, next) => {
    try {
        const user = await prisma.user.create({
            data: {
                username: req.body.username,
                password: await hashPassword(req.body.password)
            }
        })
        const token = createJWT(user)
        res.json({ token })
    } catch (e) { //error catching
        e.type = 'input' //most likely, username already taken
        next(e)
    }
}

//signs in
export const signin = async (req, res) => {
    const user = await prisma.user.findUnique({
        where: {
            username: req.body.username //check if username exists
        }
    })

    const isValid = await comparePasswords(req.body.password, user.password)

    if (!isValid) { //wrong password
        res.status(401)
        res.json({message: 'password incorect'})
        return
    }

    //correct password, give acsess 
    const token = createJWT(user)
    res.json({ token })
}