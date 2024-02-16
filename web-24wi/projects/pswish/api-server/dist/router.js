"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var express_1 = require("express");
var router = (0, express_1.Router)();
// Product
router.get("/product", function (req, res) {
    res.json({ message: 'Hello' });
});
router.get("/product/:id", function (req, res) { });
router.put("/product/:id", function (req, res) { });
router.post("/product", function (req, res) { });
router.delete("/product/:id", function (req, res) { });
// Update
router.get("/update", function (req, res) { });
router.get("/update/:id", function (req, res) { });
router.put("/update/:id", function (req, res) { });
router.post("/update", function (req, res) { });
router.delete("/update/:id", function (req, res) { });
// UpdatePoint
router.get("/updatePoint", function (req, res) { });
router.get("/updatePoint/:id", function (req, res) { });
router.put("/updatePoint/:id", function (req, res) { });
router.post("/updatePoint", function (req, res) { });
router.delete("/updatePoint/:id", function (req, res) { });
exports.default = router;
//# sourceMappingURL=router.js.map