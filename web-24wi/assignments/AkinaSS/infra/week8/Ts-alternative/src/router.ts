import { Router } from "express";
import { createNewUser, getAllUsers, signin } from "./handlers/user";

const router = Router();
/**
 * User
 */
router.get("/user", getAllUsers);

router.get("/user/:id", signin);

router.post("/user", createNewUser);

router.put("/user/:id", (req, res) => {});

router.delete("/user/:id", (req, res) => {});

export default router;