import supertest from "supertest";
import app from "../server";
import prisma from "../db";

let token;
beforeAll(async () => {
    const res = await supertest(app)
        .post("/register")
        .send({ username: "unittest", password: "pass" })
        .set("Content-Type", "application/json")
    console.log(res.body.token);
    token = res.body.token;
})
afterAll(async () => {
    const result = await prisma.user.deleteMany();
});
describe("GET /", () => {
    it("should send back an html page", async () => {
        const res = await supertest(app)
            .get("/");
        expect(res.status).toBe(200);
    });
});


// login tests
describe("POST /login", () => {
    //setup
    it("should return status 200", async () => {
        const res = await supertest(app)
            .post("/login")
            .send({ username: "unittest", password: "pass" })
            .set("Content-Type", "application/json")
            .expect(200);
    });

    it("should return status 401", async () => {
        const res = await supertest(app)
            .post("/login")
            .send({ username: "fake", password: "user" })
            .set("Content-Type", "application/json")
            .expect(401);
    });
});

// register tests
describe("POST /register", () => {
    it("should return status 200 and return a jwt", async () => {
        const res = await supertest(app)
            .post("/register")
            .send({ username: "fake", password: "user" })
            .set("Content-Type", "application/json")

        expect(res.status).toBe(200);
        expect(res.body).toHaveProperty("token");
    })
});


// protected routes
describe("GET /api/game-history", () => {
    it("should return status 200", async () => {
        const res = await supertest(app)
            .get("/api/game-history")
            .set("Content-Type", "application/json")
            .set("Authorization", `Bearer ${token}`);
        expect(res.status).toBe(200);
        expect(res.body).toBeTruthy();
    });
})
