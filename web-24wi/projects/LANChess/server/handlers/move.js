import prisma from "../db.js";
// adds a move to db, not used for a route
export const createMove = async (data) => {
    const result = await prisma.move.create({
        data: {
            fen_string: data.FEN_string,
            user_id: data.userid,
            game_id: data.gameid,
        }
    });
    return ({ data: result });
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
