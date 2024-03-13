import { AsyncLocalStorage } from 'async_hooks';
import prisma from '../db';
import { comparePasswords, createJWT, hashPassword } from '../modules/auth';
import path from 'path'
import { cookie } from 'express-validator';


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
    return res.redirect("/api/profile")

    }
    
}

export const Users = async (req,res)=> {
    const allUsers = await prisma.user.findMany({
        select: {
            username: true
        }
});
	res.json(allUsers);

}

export const signin = async (req, res) => {
    const user = await prisma.user.findUnique({
        where: {
            username: req.body.Username
        }
    });
    console.log(user)
    const isValid = await comparePasswords(req.body.Password, user.password);

    if(!isValid) {
        res.status(401);
        res.send("Invalid username or password");
        return;
    }

    const token = createJWT(user);
    res.cookie('token', token)
    res.cookie('user', user.username )
    // res.json({token});
    return res.redirect("/api/profile")
}

export const updateUser = async (req, res) => {

    const updated = await prisma.user.update({
        where: {
            id: req.user.id
        },

        data: {
            username: req.body.Username
        }
    })
    res.cookie('user', req.body.Username)
    return res.redirect("/api/profile")
}

export const updatePass = async (req, res) => {
    const updated = await prisma.user.update({
        where: {
            id: req.user.id
        },

        data: {
            password: await hashPassword(req.body.Password)
        }
    })
  
    return res.redirect("/api/profile")
}



export const deletUser = async (req, res) => {
    const deleted = await prisma.user.delete({
        where: {
            id: req.user.id,
        }
    })

    // res.json({data: deleted})
    return res.redirect("/")
}

export const logout = async (req, res) => {
    res.clearCookie('user');
    res.clearCookie('token');
    return res.redirect("/")
}
