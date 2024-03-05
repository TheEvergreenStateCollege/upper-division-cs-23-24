const fs = require('fs');

const file = fs.readFileSync("./data/uscities.json")

const cities = JSON.parse(file.toString())
const lines = file.toString().split("\n")

const token = fs.readFileSync("token.txt");

const main = async() => {
  for (let city of cities) {
    console.log(JSON.stringify(city));
    /*
    const response = await fetch(
      "http://localhost:5001/api/",
      {
        "method": "POST",
        "headers": {
          "Content-Type": "application/json"
        },
        "body": JSON.stringify(city)
      }
      );
      */
  }
  
}

main().then(() => console.log("Done."));