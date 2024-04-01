import express from 'express';
import supertest from 'supertest';
import app from './server';

// run npm test

describe('Server Tests', () => {
  it('should respond with status 200 for the root endpoint', async () => {
    const response = await supertest(app).get('/');
    expect(response.status).toBe(200);
  });

  it('should respond with status 200 for the login endpoint', async () => {
    const response = await supertest(app).post('/login');
    expect(response.status).toBe(200);
  });

  it('should respond with status 200 for the cart endpoint', async () => {
    const response = await supertest(app).post('/cart');
    expect(response.status).toBe(200);
  });

  // Add more test cases as needed
});