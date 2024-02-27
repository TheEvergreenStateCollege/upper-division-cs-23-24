import prisma from "../db.js";

export const getOneGame = async (req, res) => {
    const game = await prisma.game.findFirst({
        where: {
            id: req.params.id,
        }
    });
    res.json({ data: game });
}

// get games filtered by status
export const getGames = async (req, res) => {
    const users = await prisma.game.findMany({
        where: {
            status: req.body.status,
        }
    });
    res.json({ data: users });
}

// does this need to be api or just a function of the server?
export const postGame = async (req, res) => {
    const game = await prisma.game.create({
        data: {}
    });
    res.json({ game });
}

//updates status of a game stats: enum { COMPLETED, WAITING, ONGOING }
export const updateGame = async (req, res) => {
    const result = await prisma.game.update({
        where: {
            id: req.params.id,
        },
        data: {
            status: req.body.status,
        }
    });
    res.json({ data: result });
}
export const deleteGame = async (req, res) => {}
