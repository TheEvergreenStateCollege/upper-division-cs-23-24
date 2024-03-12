const prisma = require('../db');
const { hashPassword, comparePasswords } = require('../modules/auth');
const { createJWT } = require('../modules/auth');

    console.log(JSON.stringify(Object.keys(prisma)));

    exports.createNewUser = async function(req, res, next) {
    try {
        const user = await prisma.user.create({
            data: {
                username: req.body.username,
                passwordHash: await hashPassword(req.body.password),
                name: req.body.username
            }
        });
        console.log(user);
        const token = createJWT(user);
        res.json({ token });

    } catch (error) {
        console.error(error);
        res.status(500).json({ message: 'Internal server error' });
        next(error)
    }
};


exports.signin = async function(req, res) {
    try {

        console.log(JSON.stringify(req.body))

        const user = await prisma.user.findFirst({
            where: {
                username: req.body.username,
            }
            
        });

        if (!user) {
            res.status(401).json({ message: 'User not found' });
            return;
        }

        const isValid = await comparePasswords(req.body.password, user.passwordHash);

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
