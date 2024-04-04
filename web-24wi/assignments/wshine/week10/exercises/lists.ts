#!/usr/bin/env ts-node

function toList(arr: Array<any>) {
    let list: any = null;
    let i = arr.length - 1;
    while (i >= 0) {
        list = { value: arr[i], rest: list };
        i--;
    }
    return list;
}

function toArray(list: { value: any, rest: any }) {
    let arr = [];
    while (list != null) {
        arr.push(list.value);
        list = list.rest;
    }
    return arr;
}

function prepend(list: any, val: any) {
    return { value: val, rest: list };
}

function nth(list: { value: any, rest: any }, n: number) {
    let count = 0;
    let val;
    while (count <= n) {
        val = list.value;
        list = list.rest;
        count++;
    }
    return val;
}

function recursiveNth(list: { value: any, rest: any }, n: number) {
    if (n === 0) {
        return list.value;
    } else if (list.rest === null){
        return undefined;
    } else {
        return recursiveNth(list.rest, n-1);
    }
}

console.log(toList([10, 20]));
console.log(toArray(toList([10, 20, 30])));
console.log(prepend(prepend(null, 20), 10));
console.log(nth(toList([10, 20, 30]), 2));
console.log(recursiveNth(toList([10, 20, 30]), 1));
