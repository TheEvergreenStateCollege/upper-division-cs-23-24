const fs = require('fs/promises')
const path = require('path')
const read = async () => {
    const result = fs.readFile(path.join(__dirname, 'package.json'));
}

read.then(f => console.log(f))