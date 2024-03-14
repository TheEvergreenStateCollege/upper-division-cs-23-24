import { Router } from "express";
import { body } from "express-validator";
import { handleInputErrors } from "./modules/middleware";
import { createCart, deleteCart, getCarts, getOneCart, updateCart } from "./handlers/cart";
import { createProduct, deleteProduct, getOneProduct, getProducts, updateProduct } from "./handlers/product";

const router = Router();

/**
 * Cart
 */

router.get("/cart", getCarts);

router.get("/cart/:id", getOneCart);

router.post("/cart", body('name').isString(), 
body('body').isString(), body('productId').exists().isString(),
body('status').isIn(['FOOD', 'UTILITIES', 'PET_CARE', 'HABITAT']).optional(), handleInputErrors, createCart);

router.put("/cart/:id", body('name').isString(), 
body('body').isString(), body('productId').exists().isString(),
body('status').isIn(['FOOD', 'UTILITIES', 'PET_CARE', 'HABITAT']).optional(), handleInputErrors, updateCart);

router.delete("/cart/:id", deleteCart);

/**
 * Product
 */

router.get("/product", getProducts);

router.get("/product/:id", getOneProduct);

router.post("/product", body('name').exists().isString(), body('body').exists().isString(), 
body('status').isIn(['FOOD', 'UTILITIES', 'PET_CARE', 'HABITAT']).optional(), 
createProduct);

router.put("/product/:id", body('name').exists().isString(), body('body').exists().isString(), 
body('status').isIn(['FOOD', 'UTILITIES', 'PET_CARE', 'HABITAT']).optional(),
updateProduct);

router.delete("/product/:id", deleteProduct);

/**
 * UpdatePoint
 */

router.get("/updatepoint", (req, res) => {});

router.get("/updatepoint/:id", (req, res) => {});

router.post("/updatepoint", body('name').optional().exists().isString(), 
body('description').exists().isString(), 
body('updateId').exists().isString(), (req, res) => {});

router.put("/updatepoint/:id", body('name').optional().isString(), 
body('description').optional().isString(), (req, res) => {});

router.delete("/updatepoint/:id", (req, res) => {});

router.use((err, req, res, next) => {
    console.log(err);
    res.json({message: 'route error'});
});

export default router;