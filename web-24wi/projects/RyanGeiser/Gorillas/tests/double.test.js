const { double } = require('../src/double');

describe("double function", () => {
  it("should return twice its argument", async () => {
    // .,...

    expect(double(25)).toBe(50);
  });
});