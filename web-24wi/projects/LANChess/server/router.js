import { Router } from "express";
import { body, validationResult } from "express-validator";
import { handleInputErrors } from "./modules/middleware.js";
import { getOneUser, getUsers, updateUser, deleteUser, createNewUser } from "./handlers/user.js";
import { deleteGame, getGames, getOneGame, postGame, updateGame } from "./handlers/game.js";
const router = Router();

// user api
router.get("/users", getUsers);
router.get("/users/:id", getOneUser);
router.put("/users/:id", body("username").isString(), handleInputErrors, updateUser);
router.delete("/users/:id", deleteUser);
router.post("/users", createNewUser); //Didn't see this here yet, figured I'd add it, feel free to remove it.

// game api
router.get("/games/:id", getOneGame);
router.get("/games", getGames);
router.post("/games", postGame);
router.put("/games/:id", updateGame);
router.delete("/games/:id", deleteGame);

// moves api
router.post("/moves", body("fen_string"), handleInputErrors, (req, res) => {
    console.log("POST move");
    console.log(req.body);
})

export default router;
