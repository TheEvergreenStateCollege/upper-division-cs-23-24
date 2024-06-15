const express = require('express');
const fs = require('fs');
const path = require('path');
const musicMetadata = require('music-metadata');
const bcrypt = require('bcrypt');
const cors = require('cors');
const jwt = require('jsonwebtoken');

const { PrismaClient } = require('@prisma/client');
const { parsed } = require('dotenv').config();
// import { protect, createNewUser, signin } from './modules/auth';
// import { body, validationResult } from 'express-validator';

const app = express();
app.use(express.static("static"));
app.use(express.json());
app.use(cors()); // Use CORS for all routes

const port = 5000;
const mediaDir = "/home/ubuntu/src/media/server_music";

// console.log(parsed['DATABASE_URL']);
// console.log(process.env['DATABASE_URL']);

const prisma = new PrismaClient();

const createJWT = (user) => {
	const token = jwt.sign(
		{ id: user.id, username: user.username },
		process.env.JWT_SECRET
	);
	return token;
};

const protect = (req, res, next) => {
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
	} catch (error) {
		console.error(error);
		res.status(401);
		res.send("Not authorized");
		return;
	}
};

// app.use("/protected", protect);

// app.post("protected/save", async (req, res) => {
// 	console.log("user accessed protected route");
// });

// Authentication system
app.post("/auth/login", async (req, res) => {
	console.log(req.body);
	const user = await findUser(req.body.username);
	if (user) {
		if (bcrypt.compareSync(req.body.password, user.password)) {
			console.log("login: success");
			const token = createJWT(user);
			res.json({status: 'ok', token});
		} else {
			console.log("login: invalid password");
			res.status(401);
			res.send({status: 'error', message: 'Invalid password'});
		}
	} else {
		console.log("login: user doesn't exist");
		res.status(401);
		res.send({status: 'error', message: 'User does not exist'});
	}
});
app.post("/auth/register", async (req, res) => {
	console.log(req.body);
	const salt = bcrypt.genSaltSync(10); //alternatively just use bcrypt.hash
	const hash = bcrypt.hashSync(req.body.password, salt);

	const userFound = await findUser(req.body.username);
	if (userFound) {
		console.log("register: user already exists");
		res.status(401);
		res.send({status: 'error', message: 'User already exists'});
	} else {
		console.log("register: success");
		//prisma.user.create({data: user});
		const user = await prisma.user.create({
			data: {
				password: hash,
				username: req.body.username,
			},
		});
		const token = createJWT(user);
		res.send({status: 'ok', token});
	}
});
async function findUser(username) {
	const matchingUsers = await prisma.user.findMany({
		where: {
			username: {
				equals: username,
			},
		}
	});
	if (matchingUsers.length == 0) {
		return undefined;
	} else {
		return matchingUsers[0];
	}
}


/*Static website and music streaming*/

app.get("/", function(req, res) {
    res.sendFile(path.join(__dirname, 'index.html'));
});

app.get("/map", function(req, res) {
	res.sendFile(path.join(__dirname, '/static/pages/maps.html'));
});

app.get('/audio/:category/:fileName', (req, res) => {
	const category = req.params.category;
	const fileName = req.params.fileName;
	const filePath = path.join(mediaDir, category, fileName);

	if (fs.existsSync(filePath) && fs.statSync(filePath).isFile()) {
		fs.stat(filePath, (err, stats) => {
			if (err) {
				console.error(err);
				res.writeHead(500, {'Content-Type': 'text/plain'});
				res.end('File not found');
				return;
			}
			const range = req.headers.range;
			if (!range) {
				res.writeHead(416, {'Content-Type': 'text/plain'});
                res.end('Requested range not satisfiable');
                return;
			}
			const fileSize = stats.size;
			const chunkSize = 1024 * 1024;
			const start = Number(range.replace(/\D/g, ""));
			const end = Math.min(start + chunkSize, fileSize - 1);

			const headers = {
				"Content-Type": "audio/mpeg",
				"Content-Length": end - start + 1,
				"Content-Range": `bytes ${start}-${end}/${fileSize}`,
				"Accept-Ranges": "bytes",
			};
			res.writeHead(206, headers);
			const audioStream = fs.createReadStream(filePath, { start, end });
			audioStream.pipe(res);
		});
	} else {
		res.writeHead(404,  {'Content-Type': 'text/plain'});
		res.end('File not found');
	}
});

var musicData = [];

const songPrototype = {
	title: "song title",
	artist: "song artist(s)",
	filename: "song filename"
}

async function indexMusic(subdir, index) {
	let songs = [];
	try {
		const categoryDir = path.join(mediaDir, subdir);
		const files = await fs.promises.readdir(categoryDir);
		for (const file of files) {
			const filePath = path.join(categoryDir, file)
			const stat = await fs.promises.stat(filePath);
			if (stat.isFile()) {
				let title = "error: no title";
				let artist = "error: no artist";
				try {
					const metadata = await musicMetadata.parseFile(filePath);
					title = metadata.common.title;
					artist = metadata.common.artist;
				} catch (error) {
					console.error('Error parsing metadata', error);
				}
				let song = Object.create(songPrototype);
				song.title = title;
				song.artist = artist;
				song.filename = path.join(subdir,file);
				songs.push(song);
			}
		}
	} catch (error) {
		console.error("media file error: ", error);
	}
	musicData[index] = songs;
}

indexMusic("halley-labs-mix", 0);
indexMusic("progressive", 1);
indexMusic("chill-vibes", 2);

app.get("/musicdata", function(req, res) {
	res.json(musicData);
});
	
app.listen(port, () => {
	console.log("Web app listening at port 5000");
});
