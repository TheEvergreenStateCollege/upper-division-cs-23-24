#!/usr/bin/env ts-node
import process, { exit } from "process";

const size: number = Number(process.argv[2]);
if (Number.isNaN(size)) {
    console.log("invalid input.");
    exit();
}
const odd = size % 2;

let space = true;
let board = "";
let i = 0;
while (i < size) {
    let j = 0;
    while (j < size) {
        (space) ? board += " " : board += "#";
        space = !space;
        j++;
    }
    board += "\n";
    if (!odd) space = !space;
    i++;
}


console.log(board);
