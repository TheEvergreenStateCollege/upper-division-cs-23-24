#!/usr/bin/env ts-node

function countChar(str: string, char: string) {
    let count = 0;
    let i = 0;
    while (i < str.length) {
        if (str[i] === char) {
            count++;
        }
        i++;
    }
    return count;
}

console.log(countChar("BBC", "B"));
console.log(countChar("kakkerlak", "k"));
