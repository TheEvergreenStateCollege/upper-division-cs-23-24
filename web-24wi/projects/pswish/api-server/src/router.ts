import { Router } from "express";
import { body } from "express-validator";
import { handleInputErrors } from "./modules/middlewares";
import { createProduct, deleteProduct, getOneProduct, getProducts } from "./handlers/product";

const router = Router();

// Product

router.get("/product", getProducts);
router.get("/product/:id", getOneProduct);
router.put(
  "/product/:id",
  body("name").isString(),
  handleInputErrors,
  (req, res) => {}
);
router.post(
  "/product",
  body("name").isString(),
  handleInputErrors,
  createProduct
);

router.delete("/product/:id", deleteProduct);

// Update
router.get("/update", (req, res) => {});
router.get("/updbelongsToId, belongsToe/:id", (req, res) => {});
router.put(
  "/update/:id",
  body("title").optional,
  body("body").optional,
  body("status").isIn(["IN_PROGRESS", "SHIPPED", "DEPRECATED"]),
  body("version").optional,
  () => {}
);
router.post(
  "/update/:id",
  body("title").exists().isString(),
  body("body").exists().isString(),
  () => {}
);
router.delete("/update/:id", (req, res) => {});

// UpdatePoint
router.get("/updatePoint", (req, res) => {});
router.get("/updatePoint/:id", (req, res) => {});
router.put(
  "/updatePoint/:id",
  body("name").optional().isString(),
  body("description").optional().isString(),
  () => {}
);
router.post(
  "/updatePoint/:id",
  body("name").optional().isString(),
  body("description").optional().isString(),
  body("updateID").exists().isString(),
  () => {}
);

router.delete("/updatePoint/:id", (req, res) => {});

// error handler for the router
router.use((err, req, res, next) => {
  console.log(err)
  res.json({message: "in router handler"})
})

export default router;
