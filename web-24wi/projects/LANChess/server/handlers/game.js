import pkg from "@prisma/client";
import prisma from "../db.js";
const { Status } = pkg;

export const getOneGame = async (req, res) => {
    const result = await prisma.game.findFirst({
        where: {
            id: req.params.id,
        }
    });
    res.json({ data: result });
}

// get games filtered by status
export const getGames = async (req, res) => {
    const result = await prisma.game.findMany({
        where: {
            status: req.body.status,
        }
    });
    res.json({ data: result });
}

export const getGameHistory = async (req, res) => {
    const result =  await prisma.game.findMany({
        where: {
            user_games: {
                some: {
                    user_id: req.user.id,
                }
            }
        }
    })
res.json({ data: result })
}
// does this need to be api or just a function of the server?
export const postGame = async (req, res) => {
    const result = await prisma.game.create({
        data: {}
    });
    res.json({ data: result });
}

//updates status of a game stats: enum { COMPLETE, WAITING, ONGOING }
export const updateGame = async (req, res) => {
    var status;
    console.log(req.body.status)
    switch (req.body.status) {
        case "COMPLETE":
            status = Status.COMPLETE;
            break;
        case "WAITING":
            status = Status.WAITNG;
            break;
        case "ONGOING":
            status = Status.ONGOING;
            break;
        default:
            res.json({ error: "invalid status" });
            return;
    }

    const result = await prisma.game.update({
        where: {
            id: req.params.id,
        },
        data: {
            status: status,
        }
    });
    res.json({ data: result });
}
export const deleteGame = async (req, res) => {
    const result = await prisma.game.delete({
        where: {
            id: req.params.id,
        }
    });
    res.json({ data: result });
}
