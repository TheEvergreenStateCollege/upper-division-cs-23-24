import express from 'express';
import supertest from 'supertest';
import router from './router';

const app = express();
app.use(router);

// run npm test

describe('Router Tests', () => {
  it('should respond with status 200 for the root endpoint', async () => {
    const response = await supertest(app).get('/');
    expect(response.status).toBe(200);
  });

  it('should respond with status 200 for the product endpoint', async () => {
    const response = await supertest(app).post('/product');
    expect(response.status).toBe(200);
  });

  it('should respond with status 200 for the cart endpoint', async () => {
    const response = await supertest(app).post('/cart');
    expect(response.status).toBe(200);
  });

  // Add more test cases for other routes as needed
  it('should respond with status 200 for the product endpoint', async () => {
    const response = await supertest(app).post('/product');
    expect(response.status).toBe(200);
  });

});

// Cart

describe("cart methods", function (id) {
    it("responds with json", async () => {
      const res = await supertest(app)
        .put(`/cart/` + id)
        .send({ name: "hello", body: "hola", productId: "123", status: "FOOD" })
        .set("Accept", "application/json")
  
      expect(res.headers["Content-Type"]).toMatch(/json/);
      expect(res.status).toEqual(200);
    });

    
    it("responds with json", async () => {
        const res = await supertest(app).get("/cart");
        expect(res.status).toEqual(200);
    });

    it("responds with json", async () => {
        const res = await supertest(app).delete(`/cart/` + id);
        expect(res.status).toEqual(200);
    });
      
});    //sessionStorage

describe("product methods", function (id) {
    it("responds with json", async () => {
      const res = await supertest(app)
        .put(`/product/` + id)
        .send({ name: "hello", body: "hola", productId: "123", status: "FOOD" })
        .set("Accept", "application/json")
  
      expect(res.headers["Content-Type"]).toMatch(/json/);
      expect(res.status).toEqual(200);
    });

    
    it("responds with json", async () => {
        const res = await supertest(app).get("/product");
        expect(res.status).toEqual(200);
    });

    it("responds with json", async () => {
        const res = await supertest(app).delete(`/product/` + id);
        expect(res.status).toEqual(200);
    });
      
});