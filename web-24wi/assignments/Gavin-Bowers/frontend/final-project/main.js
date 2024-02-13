import fs from "fs";

window.onload = function() {
    const dir = "C:/Users/super/Desktop/music_to_add";
    const extension = ".mp3"
    const fileNames = fs.readdirSync(dir).filter(fn => fn.endsWith(extension));

    for (let fileName in fileNames) {
        console.log(fileName);
    }
}