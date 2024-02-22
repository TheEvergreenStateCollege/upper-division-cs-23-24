import { Router } from "express";
import { body, validationResult } from "express-validator";
import { handleInputErrors } from "./modules/middleware.js";
import { getOneUser, getUsers, updateUser, deleteUser } from "./handlers/user.js";
const router = Router();

// user api
router.get("/users", getUsers);
router.get("/user/:id", getOneUser);
router.put("/user/:id", body("username").isString(), handleInputErrors, updateUser);
router.delete("/user/:id", deleteUser);

// game api
router.get("/game/:id", (req, res) => {
    console.log(`GET game ${req.params.id}`);
})
router.get("/games", (req, res) => {
    console.log("GET games");
    console.log(req.body);
})

// moves api
router.post("/moves", body("fen_string"), handleInputErrors, (req, res) => {
    console.log("POST move");
    console.log(req.body);
})

export default router;

