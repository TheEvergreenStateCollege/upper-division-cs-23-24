import { Router } from "express";

const router = Router();

// Product

router.get("/product", (req, res) => {
    res.json({message: 'Hello'})
})
router.get("/product/:id", (req,res) => {})
router.put("/product/:id", (req,res) => {})
router.post("/product", (req,res) => {})
router.delete("/product/:id", (req,res) => {})

// Update
router.get("/update", (req,res) => {})
router.get("/update/:id", (req,res) => {})
router.put("/update/:id", (req,res) => {})
router.post("/update", (req,res) => {})
router.delete("/update/:id", (req,res) => {})

// UpdatePoint
router.get("/updatePoint", (req,res) => {})
router.get("/updatePoint/:id", (req,res) => {})
router.put("/updatePoint/:id", (req,res) => {})
router.post("/updatePoint", (req,res) => {})
router.delete("/updatePoint/:id", (req,res) => {})

export default router;