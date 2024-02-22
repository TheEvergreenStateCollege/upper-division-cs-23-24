import prisma from "../db"

//get one product
export const getProduct = async (req,res) => {
    const id = req.params.id

    const product = await prisma.product.findFirst({
        where: {
            id,
            belongsToId: req.user.id
        }
    })
}

//get all products
export const getProducts = async (req, res) => {
    const user = await prisma.user.findUnique({
        where: {
            id: req.user.id
        },
        include: {
            products: true
        }
    })

    res.json({data: user.products})
}

//create a product
export const createProduct = async (req, res) => {
    const product = await prisma.product.create({
        data: {
            name: req.body.name,
            belongsToId: req.user.id
        }
    })

    res.json({data: product})
}

//update
export const updateProduct = async (req, res) => {
    const updated = await prisma.product.update({
        where: {
            id: req.params.id
        },

        data: {
            name: req.body.name
        }
    })
    res.json({data: updated})
}

//delete
export const deletProduct = async (req, res, next) => {
    try{
        const deleted = await prisma.product.delete({
            where: {
                id_belongsToId: {
                    id: req.params.id,
                belongsToId: req.user.id
                }
                
            }
        })

        res.json({data: deleted})
    } catch (e) {
        next(e)
    }
}