import { Router } from "express";

const router = Router();
/**
 * Product
 */
router.get("/word", (req, res) => {
  res.json({ message: "word" });
});

router.get("/word/:id", (req, res) => {});

router.post("/word", (req, res) => {});

router.put("/word/:id", (req, res) => {});

router.delete("/word/:id", (req, res) => {});

/**
 * Update
 */

router.get("/update", (req, res) => {});

router.get("/update/:id", (req, res) => {});

router.post("/update", (req, res) => {});

router.put("/update/:id", (req, res) => {});

router.delete("/update/:id", (req, res) => {});

/**
 * UpdatePoint
 */

router.get("/updatepoint", (req, res) => {});

router.get("/updatepoint/:id", (req, res) => {});

router.post("/updatepoint", (req, res) => {});

router.put("/updatepoint/:id", (req, res) => {});

router.delete("/updatepoint/:id", (req, res) => {});

export default router;