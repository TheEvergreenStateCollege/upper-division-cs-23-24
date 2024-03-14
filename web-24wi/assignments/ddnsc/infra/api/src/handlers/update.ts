import { $Enums } from "@prisma/client"
import prisma from "../db"

export const getOneUpdate = async (req: { params: { id: any } }, res: { json: (arg0: { data: { id: string; createdAt: Date; updatedAt: Date; title: string; body: string; status: $Enums.UPDATE_STATUS; version: string | null; asset: string | null; productId: string } | null }) => void }) => {
    const update = await prisma.update.findUnique({
        where: {
            id: req.params.id
        }
    })

    res.json({data: update})
}

export const getUpdates = async (req: { user: { id: any } }, res: { json: (arg0: { data: any[] }) => void }) => {
    const products = await prisma.product.findMany({
        where: {
            belongsToId: req.user.id
        },
        include: {
            updates: true
        }
    })

    const updates = products.reduce((allUpdates, product) => {
        return [...allUpdates, ...product.updates]
    }, [])

    res.json({data: updates})
}
export const createUpdate = async (req: { body: { productId: any; title: any; body: any } }, res: { json: (arg0: { message?: string; data?: { id: string; createdAt: Date; updatedAt: Date; title: string; body: string; status: $Enums.UPDATE_STATUS; version: string | null; asset: string | null; productId: string } }) => void }) => {


    const product = await prisma.product.findUnique({
        where: {
            id: req.body.productId
        }
    })

    if (!product) {
        // does not belong to user
        return res.json({message: 'nope'})
    }

    const update = await prisma.update.create({
        data: {
            title: req.body.title,
            body: req.body.body,
            product: {connect: {id: product.id}}
        }
    })

    res.json({data: update})
}

export const updateUpdate = async (req: { user: { id: any }; params: { id: any }; body: any }, res: { json: (arg0: { message?: string; data?: { id: string; createdAt: Date; updatedAt: Date; title: string; body: string; status: $Enums.UPDATE_STATUS; version: string | null; asset: string | null; productId: string } }) => void }) => {
    const products = await prisma.product.findMany({
        where: {
            belongsToId: req.user.id,
        },
        include: {
            updates: true
        }
    })

    const updates = products.reduce((allUpdates, product) => {
        return [...allUpdates, ...product.updates]
    }, [])

    const match = updates.find((update: { id: any }) => update.id === req.params.id)

    if (!match) {
        // handle this
        return res.json({message: 'nope'})
    }


    const updatedUpdate = await prisma.update.update({
        where: {
            id: req.params.id
        },
        data: req.body
    })

    res.json({data: updatedUpdate})
}

export const deleteUpdate = async (req: { user: { id: any }; params: { id: any } }, res: { json: (arg0: { message?: string; data?: { id: string; createdAt: Date; updatedAt: Date; title: string; body: string; status: $Enums.UPDATE_STATUS; version: string | null; asset: string | null; productId: string } }) => void }) => {
    const products = await prisma.product.findMany({
        where: {
            belongsToId: req.user.id,
        },
        include: {
            updates: true
        }
    })

    const updates = products.reduce((allUpdates, product) => {
        return [...allUpdates, ...product.updates]
    }, [])

    const match = updates.find((update: { id: any }) => update.id === req.params.id)

    if (!match) {
        // handle this
        return res.json({message: 'nope'})
    }

    const deleted = await prisma.update.delete({
        where: {
            id: req.params.id
        }
    })

    res.json({data: deleted})
}