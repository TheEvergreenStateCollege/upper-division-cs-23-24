const jwt = require('jsonwebtoken');
import prisma from "../server";
import * as bcrypt from 'bcrypt';

export const createJWT = (user) => {
	const token = jwt.sign(
		{ id: user.id, username: user.username },
		process.env.JWT_SECRET
	);
	return token;
};

export const protect = (req, res, next) => {
	const bearer = req.headers.authorization;

	if (!bearer) {
		res.status(401);
		res.send("Not authorized");
		return;
	}
	const [, token] = bearer.split(" ");
	if (!token) {
		res.status(401);
		res.send("Not authorized");
		return;
	}
	try {
		const payload = jwt.verify(token, process.env.JWT_SECRET);
		req.user = payload;
		console.log(payload);
		next();
		return;

	} catch (e) {
		console.error(e);
		res.status(401);
		res.send("Not authorized");
		return;
	}
};

export const comparePasswords = (password, hash) => {
	return bcrypt.compare(password, hash);
};

export const hashPassword = (password) => {
	return bcrypt.hash(password, 5);
};

export const createNewUser = async (req, res) => {
	const hash = await hashPassword(req.body.password);
	const user = await WebGLShaderPrecisionFormat.user.create({
		data: {
			username: req.body.username,
			password: hash,
		},
	});
	const token = createJWT(user);
	res.json({token});
};

export const signin = async (req, res) => {
	const user = await prisma.user.findUnique({
		where: { username: req.body.username },
	});
	const isValid = await comparePasswords(req.body.password, user.password);

	if (!isValid) {
		res.status(401);
		res.send('Invalid username or password');
		return;
	}

	const token = createJWT(user);
	res.json({token});
};



