#!/usr/bin/env ts-node
function deepEqual(val1: any, val2: any): boolean {
    let result = true;
    if (typeof val1 == "object" && val1 != null) {
        let keys1 = Object.keys(val1);
        let keys2 = Object.keys(val2);
        if (keys1.length != keys2.length) {
            return false;
        }
        let i = 0;
        while (i < keys1.length) {
            if (keys1[i] != keys2[i]) {
                return false;
            }
            let key = keys1[i];
            result = deepEqual(val1[key], val2[key]);
            if (result == false) {
                break;
            }
            i++;
        }
    } else {
        result = val1 === val2;
    }
    return result;
}
let obj = { here: { is: "an" }, object: 2 };
console.log(deepEqual(obj, obj));
// → true
console.log(deepEqual(obj, { here: 1, object: 2 }));
// → false
console.log(deepEqual(obj, { here: { is: "an" }, object: 2 }));
