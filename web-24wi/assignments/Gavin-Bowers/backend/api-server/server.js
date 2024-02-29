const express = require('express');
const fs = require('fs');
const path = require('path');
const musicMetadata = require('music-metadata');
const bcrypt = require('bcrypt');

const { PrismaClient } = require('@prisma/client');
const { parsed } = require('dotenv').config();

const app = express();
app.use(express.static("static"));
app.use(express.json());

const port = 5000;

console.log(parsed['DATABASE_URL']);
console.log(process.env['DATABASE_URL']);

const prisma = new PrismaClient();

/* API SERVER - Handles authentication*/

// app.get("/users", async (req, res) => {
// 	const allUsers = await prisma.user.findMany();
// 	res.json(allUsers);
// });

// app.post("/user", async (req, res) => {
// 	const newUser = await prisma.user.create({
// 		data: {
// 			username: req.body.username,
// 			password: '',
// 		},
// 	});
// 	console.log("created new user");
// });

//New authentication system
app.post("/auth/login", (req, res) => {
	const user = findUser(req.body.email);
	if (user) {
		if (bcrypt.compareSync(req.body.password, user.password)) {
			res.send({ok: true, email: user.email});
		} else {
			res.send({ok: false, message: 'Data is invalid'});
		}
	} else {
		res.send({ok: false, message: 'User does not exist'});
	}
});

function findUser(email) {
	const matchingUsers = prisma.user.findMany({
		where: {
			email: {
				equals: email,
			},
		}
	});
	if (matchingUsers.length == 0) {
		return undefined;
	} else {
		return matchingUsers[0];
	}
}

app.post("/auth/register", (req, res) => {
	var salt = bcrypt.genSaltSync(10);
	var hash = bcrypt.hashSync(req.body.password, salt);
	const user = {
		email: req.body.email,
		password: hash
	};
	const userFound = findUser(req.body.email);
	if (userFound) {
		res.send({ok:false, message: 'User already exists'});
	} else {
		prisma.user.create({data: user});
		res.send({ok:true});
	}
});


/*Static website and music streaming*/

app.get("/", function(req, res) {
        res.sendFile(path.join(__dirname, 'index.html'));
});

app.get("/map", function(req, res) {
	res.sendFile(path.join(__dirname, '/static/pages/maps.html'));
});

app.get('/audio/:fileName', (req, res) => {
	res.set('Access-Control-Allow-Origin', '*'); //Allows for local testing
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

/* Make a list of available music when server starts*/
const mediaDir = "/home/ubuntu/src/media"
var musicList = [];

const songPrototype = {
	title: "song title",
	artist: "song artist(s)",
	filename: "song filename"
}

async function indexMusic() {
	try {
		const files = await fs.promises.readdir(mediaDir);
		for (const file of files) {
			const filePath = path.join( mediaDir, file)
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

				console.log(JSON.stringify(song));
				musicList.push(song);
			}
		}
	} catch (error) {
		console.error("media file error: ", e);
	}
}

indexMusic();

app.get("/musicdata", function(req, res) {
	res.set('Access-Control-Allow-Origin', '*'); //Allows for local testing
	res.json(musicList);
});
	
app.listen(port, () => {
	console.log("Web app listening at port 5000");
});
