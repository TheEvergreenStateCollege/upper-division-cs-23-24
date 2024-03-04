const prisma = require('../db');
const { hashPassword, comparePasswords } = require('../modules/auth');
const { createJWT } = require('../modules/auth');

    createNewUser = async function(req, res) {
    try {
        const user = await prisma.user.create({
            data: {
                username: req.body.username,
                password: await hashPassword(req.body.password)
            }
        });

        const token = createJWT(user);
        res.json({ token });

    } catch (error) {
        console.error(error);
        res.status(500).json({ message: 'Internal server error' });
    }
};


exports.signin = async function(req, res) {
    try {
        const user = await prisma.user.findUnique({
            where: {
                username: req.body.username,
            }
        });

        if (!user) {
            res.status(401).json({ message: 'User not found' });
            return;
        }

        const isValid = await comparePasswords(req.body.password, user.password);

        if (!isValid) {
            res.status(401).json({ message: 'Invalid credentials' });
            return;
        }

        const token = createJWT(user);
        res.json({ token });
    } catch (error) {
        console.error(error);
        res.status(500).json({ message: 'Internal server error' });
    }
};
