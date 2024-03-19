#!/usr/bin/env ts-node

let arrays = [[1, 2, 3], [4, 5], [6]];
// Your code here.
// → [1, 2, 3, 4, 5, 6]
let flat = arrays.reduce((acc, v) => acc.concat(v));
console.log(flat);
