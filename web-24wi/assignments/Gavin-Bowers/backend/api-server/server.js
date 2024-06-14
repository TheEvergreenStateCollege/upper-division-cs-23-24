const express = require('express');
const fs = require('fs');
const path = require('path');
const musicMetadata = require('music-metadata');
const bcrypt = require('bcrypt');
const cors = require('cors');


const { PrismaClient } = require('@prisma/client');
const { parsed } = require('dotenv').config();

const app = express();
app.use(express.static("static"));
app.use(express.json());
app.use(cors()); // Use CORS for all routes
// app.use("/api", protect, router); //currently this line breaks things

const port = 5000;

console.log(parsed['DATABASE_URL']);
console.log(process.env['DATABASE_URL']);

const prisma = new PrismaClient();

//New authentication system
app.post("/auth/login", async (req, res) => {
	console.log(req.body);
	const user = await findUser(req.body.username);
	if (user) {
		if (bcrypt.compareSync(req.body.password, user.password)) {
			console.log("login: success");
			res.send({ok: true, username: user.username});
		} else {
			console.log("login: invalid password");
			res.send({ok: false, message: 'Data is invalid'});
		}
	} else {
		console.log("login: user doesn't exist");
		res.send({ok: false, message: 'User does not exist'});
	}
});
app.post("/auth/register", async (req, res) => {
	console.log(req.body);
	const salt = bcrypt.genSaltSync(10);
	const hash = bcrypt.hashSync(req.body.password, salt);
	// const user = {
	// 	username: req.body.username,
	// 	password: hash
	// };
	const userFound = await findUser(req.body.username);
	if (userFound) {
		console.log("register: user already exists");
		res.send({ok:false, message: 'User already exists'});
	} else {
		console.log("register: success");
		//prisma.user.create({data: user});
		const newUser = await prisma.user.create({
			data: {
				password: hash,
				username: req.body.username,
			},
		});
		res.send({ok:true, message: "User created"});
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

app.get('/audio/:fileName', (req, res) => {
	const fileName = req.params.fileName;
	const filePath = path.join('/home/ubuntu/src/media/', fileName);

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

const songPrototype = {
	title: "song title",
	artist: "song artist(s)",
	filename: "song filename"
}

async function indexMusic(mediadir) {
	let songs = [];
	try {
		const files = await fs.promises.readdir(mediadir);
		for (const file of files) {
			const filePath = path.join( mediadir, file)
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
				song.filename = file;
				songs.push(song);
			}
		}
	} catch (error) {
		console.error("media file error: ", error);
	}
	return songs;
}

const halleyLabsMix = indexMusic("/home/ubuntu/src/media/server_music/halley-labs-mix");
const progressive = indexMusic("/home/ubuntu/src/media/server_music/progressive");
const chillVibes = indexMusic("/home/ubuntu/src/media/server_music/chill-vibes");

app.get("/musicdata/halley-labs-mix", function(req, res) {
	res.json(halleyLabsMix);
});
app.get("/musicdata/progressive", function(req, res) {
	res.json(progressive);
});
app.get("/musicdata/chill-vibes", function(req, res) {
	res.json(chillVibes);
});
	
app.listen(port, () => {
	console.log("Web app listening at port 5000");
});
