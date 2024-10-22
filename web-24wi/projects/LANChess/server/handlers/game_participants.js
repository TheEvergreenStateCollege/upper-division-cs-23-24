import prisma from "../db.js";

export const createGameParticipant = async (req, res) => {
    const result = await prisma.game_participant.create({
        data: {
            user_id: req.user.id,
            game_id: req.body.gameId,
        }
    });

    res.json({ data: result });
}
