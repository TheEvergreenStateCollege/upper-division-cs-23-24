import { AsyncLocalStorage } from 'async_hooks';
import prisma from '../db';
import { comparePasswords, createJWT, hashPassword } from '../modules/auth';
import path from 'path'


export const createNewUser = async (req,res) => {
    const results = await prisma.user.findUnique({
        where: {
            username: req.body.Username
        }
    })
    if(results){
         res.send({ok: false, message: 'User already exists'})
    }
    else{
        const user = await prisma.user.create({
        
            data: {
                username: req.body.Username,
                password: await hashPassword(req.body.Password)
            },
        })
    const token = createJWT(user);
    res.cookie('token', token)
    res.cookie('user', user.username )
    
    res.json({token});
    // res.sendFile(path.resolve("../client/dist/profile/index.html"))
    }
    
}

export const signin = async (req, res) => {
    const user = await prisma.user.findUnique({
        where: {
            username: req.body.Username
        }
    });

    const isValid = await comparePasswords(req.body.Password, user.password);

    if(!isValid) {
        res.status(401);
        res.send("Invalid username or password");
        return;
    }

    const token = createJWT(user);
    res.json({token});
}
