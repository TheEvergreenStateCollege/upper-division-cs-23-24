import { Router } from "express";

const router = Router();

router.get("/user", (req, res) => {
    res.json({ message: "product" });
});

router.get("/user/:id", (req, res) => {
    console.log(req.body);
    console.log("GET");
});

router.post("/user", (req, res) => {
    console.log(req.body);
    console.log("POST");
});

router.put("/user/:id", (req, res) => {
    console.log(req.body);
    console.log("PUT");
});

router.delete("/user/:id", (req, res) => {
    console.log(req.body);
    console.log("DELETE");
});

export default router;
