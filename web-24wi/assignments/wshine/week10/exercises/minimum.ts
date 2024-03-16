#!/usr/bin/env ts-node

function min(x: number, y: number) {
    let minimum;
    (x < y) ? minimum = x : minimum = y;
    return minimum;
}

console.log(min(0, 10));
console.log(min(0, -10));
