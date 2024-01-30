"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
exports.__esModule = true;
var express_1 = __importDefault(require("express"));
// const express = require("express");
var app = (0, express_1["default"])();
var port = 5000;
var path = require("path");
app.use(express_1["default"].static("static"));
/**
 * app.[method]([route], [route handler])
 */
app.get("/", function (req, res) {
    // sending back an HTML file that a browser can render on the screen.
    res.sendFile(path.resolve("pages/index.html"));
});
// creates and starts a server for our API on a defined port
// app.listen(port, () => {
//   console.log(`Express api listening at http://pswish-corp.arcology.builders:${port}`);
// });
exports["default"] = app;
//# sourceMappingURL=server.js.map