//../../../../dsa-23au/java-dsa/Torsten-David.app/src/main/resources/AppleWatchData_myData.csv 

const fs = require('fs');
const jwt = require('jsonwebtoken');
const file = fs.readFileSync("../../../../dsa-23au/java-dsa/Torsten-David.app/src/main/resources/AppleWatchData_myData.csv")

const lines = file.toString().split("\n")

const token = fs.readFileSync("token.txt").toString().trim();
console.log(`Token ${token}`);

const { id } = jwt.decode(token, {json: true });

const main = async() => {
    console.log(lines.length);
    for (let i in lines){
        const dataPoint = lines[i].split(',');
        console.log(`Data ${ i } of ${ lines.length }`);
        console.log(JSON.stringify(dataPoint));

        if(i === 0 ){
            continue;
        }

        const jsonBody = {};
        jsonBody["date"] = dataPoint[0];
        jsonBody["steps"] = dataPoint[1];
        jsonBody["distance"] = dataPoint[2];
        jsonBody["flights"] = dataPoint[3];
        jsonBody["activeEnergyCals"] = dataPoint[4];
        jsonBody["handwashingSeconds"] = dataPoint[5];
        jsonBody["restingEnergyCals"] = dataPoint[6];
        jsonBody["soundLevel"] = dataPoint[7];
        jsonBody["userId"] = id;


        const response = await fetch(
            "http://localhost:5000/daily-watch-data",
            {
                "method": "POST",
                "headers": {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`,
                },
                "body": JSON.stringify(jsonBody)
            }
        );
        const json = await response.json();

        console.log(JSON.stringify(json));

         
    }


}

main().then(() => console.log("Done"));