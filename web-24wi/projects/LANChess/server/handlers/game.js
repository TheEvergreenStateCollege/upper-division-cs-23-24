import prisma from "../db.js";

export const getOneGame = async (req, res) => {
    const game = await prisma.game.findFirst({
        where: {
            id: req.params.id,
        }
    });
    res.json({ data: game });
}
export const getGames = async (req, res) => {
    const users = await prisma.game.findMany();
    res.json({ data: users });
}

// does this need to be api or just a function of the server?
export const postGame = async (req, res) => {
    const game = await prisma.game.create({
        data: {}
    });
    res.json({ game });
}
export const updateGame = async (req, res) => { }
export const deleteGame = async (req, res) => { }
