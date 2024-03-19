const fs = require('fs');
const jwt = require('jsonwebtoken');
const file = fs.readFileSync("./data/uscities.json")

const cities = JSON.parse(file.toString())
const lines = file.toString().split("\n")

const token = fs.readFileSync("token.txt").toString().trim();
console.log(`Token ${token}`);

const { id } = jwt.decode(token, {json: true});

const main = async() => {
  for (let i in cities) {

    console.log(`City ${i} of ${cities.length}`);
    const city = cities[i];
    city["name"] = city["city"];
    city["authorId"] = id;
    delete city["city"];
    console.log(JSON.stringify(city));

    const response = await fetch(
      "https://indira.arcology.builders/api/uscity",
      {
        "method": "POST",
        "headers": {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`,
        },
        "body": JSON.stringify(city)
      }
      );
    const json = await response.json();

    console.log(JSON.stringify(json));
  }
  
}

main().then(() => console.log("Done."));
