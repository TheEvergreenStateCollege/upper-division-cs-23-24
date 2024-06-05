import jwt from 'jsonwebtoken'
import bcrypt from 'bcrypt'

//check if enter password matches saved hashed password
export const comparePasswords = (password, hash) => {
    return bcrypt.compare(password, hash)
}

//hashes password
export const hashPassword = (password) => {
    return bcrypt.hash(password, 5) //5 is the salt
}

export const createJWT = (user) => {
    const token = jwt.sign({
            id: user.id, 
            username: user.username
        }, 
        process.env.JWT_SECRET
    )
    return token
}

export const protect = (req, res, next) => {
    const bearer = req.headers.authorization

    //check for bearer
    if (!bearer) {
        res.status(401)
        res.json({message: 'not authorized'})
        return
    }

    //split token from bearer
    const [, token] = bearer.split(' ')

    //check token
    if (!token) {
        res.status(401)
        res.json({message: 'no token'})
        return
    }

    try {
        const user = jwt.verify(token, process.env.JWT_SECRET)
        req.user = user
        next()

    } catch (e) {
        console.error(e)
        res.status(401)
        res.json({message: 'not valid token'})
        return
    }

}