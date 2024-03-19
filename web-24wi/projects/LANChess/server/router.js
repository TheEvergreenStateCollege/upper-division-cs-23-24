import { Router } from "express";
import { body, validationResult } from "express-validator";
import { handleInputErrors } from "./modules/middleware.js";
import { getOneUser, getUsers, updateUser, deleteUser, createNewUser } from "./handlers/user.js";
import { deleteGame, getGames, getGameHistory, getOneGame, postGame, updateGame } from "./handlers/game.js";
import { createMove, getMoves } from "./handlers/move.js";
import { createGameParticipant } from "./handlers/game_participants.js";

const router = Router();

// user api
router.get("/users", getUsers);
router.get("/users/:id", getOneUser);
router.put("/users/:id", body("username").isString(), handleInputErrors, updateUser);
router.delete("/users/:id", deleteUser);
 
// game api
router.get("/games/:id", getOneGame);
router.get("/games", body("status").isString(), handleInputErrors, getGames);
router.get("/game-history", getGameHistory);
router.post("/games", postGame);
router.put("/games/:id", updateGame);
router.delete("/games/:id", deleteGame);

// moves api
router.post("/moves", body("fenString").isString(), handleInputErrors, createMove);
router.get("/moves/:gameid", getMoves);

// games participant api
router.post("/gameParticipant", body("gameId").isString(), handleInputErrors, createGameParticipant);

export default router;
