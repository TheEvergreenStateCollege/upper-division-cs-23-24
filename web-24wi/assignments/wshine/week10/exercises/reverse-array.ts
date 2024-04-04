#!/usr/bin/env ts-node

function reverseArray(arr: Array<any>) {
    let reversed = [];
    let i = arr.length - 1;

    while (i >= 0) {
        reversed.push(arr[i]);
        i--;
    }
    return reversed;
}

function reverseArrayInPlace(arr: Array<any>) {
    let i = 0;
    let mid = arr.length / 2;
    let end = arr.length - 1;

    while (i < mid) {
        let tmp = arr[i];
        arr[i] = arr[end - i];
        arr[end - i] = tmp;
        i++;
    }
}

let myArray = ["A", "B", "C"];
console.log(reverseArray(myArray));

let arrayValue = [1, 2, 3, 4, 5];
reverseArrayInPlace(arrayValue);
console.log(arrayValue);
