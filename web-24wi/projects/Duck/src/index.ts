import app from "./server"
import * as dotenv from "dotenv";

dotenv.config();

app.listen(5000, () => {
    console.log('Hello from express, check out http://AkinaSS.arcology.builders:5000/ for result')
})
