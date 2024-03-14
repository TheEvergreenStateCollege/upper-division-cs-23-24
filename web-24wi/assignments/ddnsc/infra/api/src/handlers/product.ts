import prisma from "../db"

// Get all
export const getProducts = async (req: { user: { id: any } }, res: { json: (arg0: { data: { id: string; createdAt: Date; name: string; belongsToId: string }[] }) => void }) => {
    const user = await prisma.user.findUnique({
        where: {
            id: req.user.id
        },
        include: {
            products: true
        }
    })

    res.json({ data: user?.products ?? [] });}

// Get one
export const getOneProduct = async (req: { params: { id: any; }; user: { id: any; }; }, res: { json: (arg0: { data: { id: string; createdAt: Date; name: string; belongsToId: string; } | null; }) => void; }) => {
    const id = req.params.id

    const product = await prisma.product.findFirst({
        where: {
            id,
            belongsToId: req.user.id
        }
    })

    res.json({data: product})
}

// Create one
export const createProduct = async (req: { body: { name: any; }; user: { id: any; }; }, res: { json: (arg0: { data: { id: string; createdAt: Date; name: string; belongsToId: string; }; }) => void; }) => {
    const product = await prisma.product.create({
        data: {
            name: req.body.name,
            belongsToId: req.user.id
        }
    })

    res.json({data: product})
}


// Update one
export const updateProduct = async (req: { params: { id: any; }; user: { id: any; }; body: { name: any; }; }, res: { json: (arg0: { data: { id: string; createdAt: Date; name: string; belongsToId: string; }; }) => void; }) => {
    const updated = await prisma.product.update({
        where: {
            id_belongsToId: {
                id: req.params.id,
                belongsToId: req.user.id
            }
        },
        data: {
            name: req.body.name
        }
    })

    res.json({data: updated})
}

// Delete one
export const deleteProduct = async (req: { params: { id: any; }; user: { id: any; }; }, res: { json: (arg0: { data: { id: string; createdAt: Date; name: string; belongsToId: string; }; }) => void; }) => {
    const deleted = await prisma.product.delete({
        where: {
            id_belongsToId: {
                id: req.params.id,
                belongsToId: req.user.id
            }
        }
    })

    res.json({data: deleted})
}