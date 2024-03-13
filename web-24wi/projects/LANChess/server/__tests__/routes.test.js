import supertest from "supertest";
import app from "../server";
console.log(process.env.DATABASE_URL);
describe("GET /", () => {
    it("should send back an html page", async () => {
        const res = await supertest(app)
            .get("/");
        expect(res.status).toBe(200);
    });
});
describe("POST /login", () => {
    it("should return status 401", async () => {
        const res = await supertest(app)
            .post("/login")
            .send({ username: "fake", password: "login" });

        expect(res.status).toBe(401);
    })
})
