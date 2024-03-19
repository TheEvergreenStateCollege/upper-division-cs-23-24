import merge from "lodash.merge";
import local from "./local.js";
import testing from "./testing.js";
import prod from "./prod.js";
process.env.NODE_ENV = process.env.NODE_ENV || "developmet";
const stage = process.env.STAGE || "local";
let envConfig;

if (stage === "production") {
    envConfig = prod;
} else if (stage === "testing") {
    envConfig = testing;
    process.env.DATABASE_URL = process.env.TEST_DATABASE_URL;
} else {
    envConfig = local;
}

export default merge({
    stage,
    env: process.env.NODE_ENV,
    port: 3001,
    secrets: {
        jwt: process.env.JWT_SECRET,
        dbUrl: process.env.DATABASE_URL,
    }
}, envConfig)
