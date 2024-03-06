import prisma from "../db"

//this one works, needs interface
export const getPost = async (req,res) => {
    const id = req.params.id

    const post = await prisma.post.findFirst({
        where: {
            id,
            belongsToId: req.user.id
        }
    })
    res.json({data: post})
}

//ths one works
export const getPosts = async (req, res) => {
    const user = await prisma.user.findUnique({
        where: {
            id: req.user.id
        },
        include: {
            posts: true
        }
    })

    res.json({data: user.posts})
}

//this one works
export const createPost = async (req, res) => {
    const post = await prisma.post.create({
        data: {
            name: req.body.name,
            body: req.body.body,
            belongsToId: req.user.id
        }
    })
    return res.redirect("/api/profile")
    // res.json({data: post})
}

//needs to be tested - needs interface
export const updatePost = async (req, res) => {
    const updated = await prisma.post.update({
        where: {
            id: req.params.id
        },

        data: {
            name: req.body.name,
            body: req.body.body
        }
    })
    res.json({data: updated})
}

//needs to be tested - needs interface
export const deletPost = async (req, res) => {
    const deleted = await prisma.post.delete({
        where: {
            id_belongsToId: {
                id: req.params.id,
            belongsToId: req.user.id
            }
            
        }
    })

    res.json({data: deleted})
}
