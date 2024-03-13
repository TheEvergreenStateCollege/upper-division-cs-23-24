/** @type {import('ts-jest').JestConfigWithTsJest} */
module.exports = {
    preset: 'ts-jest',
    testEnvironment: 'node',
    transformIgnorePatterns: ["node_modules/(?!(sucrase)/)"],
    transform: { "^.+\\.(js|jsx|ts|tsx|mjs)$": "babel-jest" },
};
