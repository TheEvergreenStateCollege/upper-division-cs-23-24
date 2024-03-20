import { createJWT } from '../../modules/auth';
import { describe, it, expect } from '@jest/globals';

// Mock the jsonwebtoken library
jest.mock('jsonwebtoken', () => ({
    sign: jest.fn().mockReturnValue('mocked.token.value') // Mock the sign method to return a fixed token value
}));

describe('createJWT', () => {
    it('should generate a JWT token for a valid user', () => {
        // Mock user object
        const user = { id: '123', username: 'testuser' };

        // Call the createJWT function
        const token = createJWT(user);

        // Log the generated JWT token
        console.log('Generated JWT token:', token);

        // Verify that the sign method was called with the correct arguments
        expect(require('jsonwebtoken').sign).toHaveBeenCalledWith(
            { id: '123', username: 'testuser' },
            process.env.JWT_SECRET
        );

        // Verify that the token is generated correctly
        expect(token).toBe('mocked.token.value');
    });
});
