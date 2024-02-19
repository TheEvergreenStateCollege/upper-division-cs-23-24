const express = require("express");
const fs = require('fs');
const path = require('path');
const musicMetadata = require('music-metadata');

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

app.get("/users", async (req, res) => {
	const allUsers = await prisma.user.findMany();
	res.json(allUsers);
});

app.post("/user", async (req, res) => {
	const newUser = await prisma.user.create({
		data: {
			username: req.body.username,
			password: '',
		},
	});
	console.log("created new user");
});

/*Static website and music streaming*/

app.get("/", function(req, res) {
        res.sendFile(path.join(__dirname, 'index.html'));
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

/* Make a list of available music when server starts*/
const mediaDir = "/home/ubuntu/src/media"
async function indexMusic() {
	try {
		const files = await fs.promises.readdir(mediaDir);
		for (const file of files) {
			const filePath = path.join( mediaDir, file)
			const stat = await fs.promises.stat(filePath);
			if (stat.isFile()) {
				console.log(file);
			} else if (stat.isDirectory()) {

			}
		}
	} catch (error) {
		console.error("media file error: ", e);
	}
}
indexMusic();
	
app.listen(port, () => {
	console.log("Web app listening at port 5000");
});
