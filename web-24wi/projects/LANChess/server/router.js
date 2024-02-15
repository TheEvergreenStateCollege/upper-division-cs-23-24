import { PrismaClient } from "@prisma/client";
import { Router } from "express";
const router = Router();

// user api
router.get("/users", (req, res) => {
    res.json({ message: "user" });
})

router.get("/user/:id", (req, res) => {
    console.log(`GET user ${req.params.id}`);
})

router.post("/user", (req, res) => {
    console.log("POST user");
    console.log(req.body);
})

router.put("/user/:id", (req, res) => {
    console.log(`PUT user ${req.params.id}`);
    console.log(req.body);
})

router.delete("/user/:id", (req, res) => {
    console.log(`DELETE user ${req.params.id}`);
    console.log(req.body);
})


// game api
// do i need to delete individual games?
router.get("/game/:id", (req, res) => {
    console.log(`GET game ${req.params.id}`);
})

router.get("/games", (req, res) => {
    console.log("GET games");
    console.log(req.body);
})

router.post("/game", (req, res) => {
    console.log("POST game");
    console.log(req.body);
})

router.put("/game/:id", (req, res) => {
    console.log(`PUT game ${req.params.id}`);
    console.log(req.body);
})

// moves api
router.post("/moves", (req, res) => {
    console.log("POST move");
    console.log(req.body);
})

export default router;

