#!/usr/bin/env ts-node

function range(start: number, end: number, step?: number) {
    if (!step) {
        step = 1;
    }
    let arr = [];
    let i = 0;
    let size = Math.abs(end - start) + 1;
    while (i < size) {
        arr.push(start);
        start += step;
        i++;
    }
    return arr;
}
function sum(arr: Array<number>) {
    return arr.reduce((acc, x) => acc + x);
}


console.log(range(1, 10));
console.log(range(5, 2, -1));
console.log(sum(range(1, 10)));
console.log(sum(range(5, 2, -1)));
