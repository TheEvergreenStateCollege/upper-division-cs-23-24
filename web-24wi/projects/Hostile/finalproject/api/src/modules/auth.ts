import jwt from 'jsonwebtoken'
import bycrypt from 'bcrypt'

export const comparePasswords = (password, hash)=> {
    return bycrypt.compare(password, hash);
};

export const hashPassword = (password) => {
    return bycrypt.hash(password, 5);
};

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
    const bearer = req.cookies['token']
    if(!bearer) {
        res.status(401)
        // res.json({message: 'not authorized'})
        return res.redirect("/landing")
    }

    const [token] = bearer.split(' ');

    if (!token) {
        res.status(401);
        res.json({message: 'invalid token'});
        return
    }

    try {
        const payload = jwt.verify(token, process.env.JWT_SECRET);
        req.user = payload
        next();
    } catch (e) {
        console.error(e)
        res.status(401);
        res.json({message: 'invalid token'});
        return
    }
}
