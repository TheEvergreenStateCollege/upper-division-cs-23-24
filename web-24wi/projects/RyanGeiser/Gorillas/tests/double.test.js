const { double } = require('../src/double');

describe("double function", () => {
  it("should return twice its argument", async () => {
    // .,...

    expect(double(25)).toBe(50);
  });

  it("works on negative arguments", async () => {
    expect(double(-2)).toBe(-4);
  });

});