#!/usr/bin/env ts-node

function isEven(num: number) {
    let absNum = Math.abs(num);
    if (absNum <= 1) {
        let result;
        (absNum == 0) ? result = true : result = false;
        return result;
    } else {
        return isEven(absNum - 2);
    }
}

console.log(isEven(-1));
console.log(isEven(50));
console.log(isEven(75));
