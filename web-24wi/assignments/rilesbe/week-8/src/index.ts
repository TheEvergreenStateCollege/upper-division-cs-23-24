import * as dotenv from 'dotenv';
import * as http from 'http';
dotenv.config();

import app from './server';

const DEFAULT_PORT: number = Number(process.env.API_PORT) || 5000;
let port = DEFAULT_PORT;

const server = http.createServer(app);
const serverFunc = () => {
    server.listen(port, () => {
        console.log(`Server listening on port ${port}`);
    });
};

server.on('error', (error: NodeJS.ErrnoException) => {
    if (error.code === 'EADDRINUSE') {
        console.error(`Port ${port} is already in use. If this is not expected,`);
        console.error(`you may have run this server previously, possibly on a zombie process.`);
        console.error(`Trying the next port number.`);
        port += 1;
        setTimeout(serverFunc, 1000);
    }else {
        console.error('Error starting server:', error);
    }
});

serverFunc();