import * as dotenv from "dotenv";
dotenv.config();

import app from "./server"

app.listen(3000, () => {
    console.log('Hello from express, please visit http://pswish-corp.arcology.builders:3000/')
    }
)
