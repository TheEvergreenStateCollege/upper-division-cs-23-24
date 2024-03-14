import { Router } from "express";

const router = Router();

/*** Product*/
router.get("/product", (req, res) => {
    res.json({ message: "product" });
});

router.get("/product/:id", (req, res) => {});
router.put("/product/:id", body('name').isString(),  handleInputErrors (req, res) => {

});
router.post("/product", body('name').isString(),  handleInputErrors (req, res) => {
});
router.delete("/product/:id", (req, res) => {});

/*** Update*/
router.get("/update", getUpdates);
router.get("/update/:id", getOneUpdate);
router.put("/update/:id",
    body('title').optional(),
    body('body').optional(),
    body('status').isIn(['IN_PROGRESS', 'SHIPPED', 'DEPRECATED']),
    body('version').optional(),
    updateUpdate
);
router.post("/update",
    body('title').exists().isString(),
    body('body').exists().isString(),
    body('productId').exists().isString(),
    createUpdate
);
router.delete("/update/:id", () => {});

/*** UpdatePoint*/
router.get("/updatepoint", (req, res) => {});
router.get("/updatepoint/:id", (req, res) => {});
router.put("/updatepoint/:id",
    body('name').optional().isString(),
    body('description').optional().isString(),
    () => {}
);
router.post("/updatepoint",
    body('name').isString(),
    body('description').isString(),
    body('updateId').exists().isString(),
    () => {}
);
router.delete("/updatepoint/:id", (req, res) => {});

export default router;
