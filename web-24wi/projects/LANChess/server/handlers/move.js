import prisma from "../db.js";

export const createMove = async (req, res) => {
    console.log(req.body.userId);
    console.log(req.body.gameId);
    const result = await prisma.move.create({
        data: {
            fen_string: req.body.fenString,
            user_id: req.body.userId,
            game_id: req.body.gameId,
        }
    });

    res.json({ data: result })
}
export const getOneMove = async (req, res) => { }

//get all moves for a given game
export const getMoves = async (req, res) => {
    const result = await prisma.move.findMany({
        where: {
            game_id: req.params.gameid,
        },
        select: {
            fen_string: true,
            timestamp: true,
            user_fk: {
                select: { username: true }
            },
        },
    });

    console.log(result);
    res.json({ data: result });
}
