import { createServer } from 'http';
import dotenv from 'dotenv';

const path = "../.env"
dotenv.config({ path });

const server = createServer((req, res) => {
    if (req.url === '/' && req.method === 'GET') {
        res.writeHead(200, { 'Content-Type': 'application/json' });
        res.write(JSON.stringify({ message: 'hello' }));
        res.end();
        return;
    }

    res.writeHead(404, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({ message: 'Not Found' }));
});

const PORT = 3000;

server.listen(PORT, () => {
    console.log(`Listening on port ${PORT}`);
});