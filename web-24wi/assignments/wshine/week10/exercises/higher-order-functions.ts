#!/usr/bin/env ts-node
import SCRIPTS = require("./05_higher_order/code/scripts");
import { characterScript, countBy } from "./05_higher_order/code/chapter/05_higher_order";
console.log(SCRIPTS);

let arrays = [[1, 2, 3], [4, 5], [6]];
// Your code here.
// → [1, 2, 3, 4, 5, 6]
let flat = arrays.reduce((acc, v) => acc.concat(v));
console.log(flat);

// loop
function loop(n: number, test: Function, update: Function, body: Function) {
    while (test(n)) {
        body(n);
        n = update(n);
    }
}
let test = (n: number) => n > 0;
let update = (n: number) => n - 1;
loop(3, test, update, console.log);
// → 3
// → 2
// → 1

// every

function every(arr: Array<any>, p: Function) {
    let result = true;
    for (const v of arr) {
        if (!p(v)) {
            result = false;
            break;
        }
    }
    return result;
}

function _every(arr: Array<any>, p: Function) {
    return !arr.some(x => !p(x));
}

console.log(every([1, 3, 5], (n: number) => n < 10));
console.log(_every([1, 3, 5], (n: number) => n < 10));
// → true
console.log(every([2, 4, 16], (n: number) => n < 10));
console.log(_every([2, 4, 16], (n: number) => n < 10));
// → false
console.log(every([], (n: number) => n < 10));
console.log(_every([], (n: number) => n < 10));
// → true

function dominantDirection(text: string) {
    let scripts = countBy(text, (char: string) => {
        let script = characterScript(char.codePointAt(0));
        return script ? script.direction : "none";
    })
    const dominantScript = scripts.reduce((acc, v) => {
        return v.count > acc.count ? v : acc;
    });
    return dominantScript.name;
}
console.log(dominantDirection("Hello!"));
// → ltr
console.log(dominantDirection("Hey, مساء الخير"));
// → rtl
