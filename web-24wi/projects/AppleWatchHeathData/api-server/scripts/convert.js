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
        jsonBody["steps"] = dataPoint["dataPoint"];
        jsonBody["distance"] = dataPoint["dataPoint"];
        jsonBody["flights"] = dataPoint["dataPoint"];
        jsonBody["activeEnergyCals"] = dataPoint["dataPoint"];
        jsonBody["handwashingSeconds"] = dataPoint["dataPoint"];
        jsonBody["restingEnergyCals"] = dataPoint["dataPoint"];
        jsonBody["soundLevel"] = dataPoint["dataPoint"];
        jsonBody["userId"] = id;

        const response = await fetch(
            "http://localhost:5000/daily-Watch-Data",
            {
                "method": "POST",
                "headers": {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`,
                },
                "body": JSON.stringify(dataPoint)
            }
        );
        const json = await response.json();

        console.log(JSON.stringify(json));

         
    }


}

main().then(() => console.log("Done"));