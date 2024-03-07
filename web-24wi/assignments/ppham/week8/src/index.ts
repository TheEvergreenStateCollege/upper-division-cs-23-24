import * as dotenv from 'dotenv';
import * as http from 'http';
dotenv.config();

import app from './server';

// Start with default port, some servers may already be listening on port 5000
const DEFAULT_PORT: number = Number(process.env.API_PORT) || 5000;
let port = DEFAULT_PORT;

const fs = require('fs');

// Wrap the Express app with an HTTP server manually 
const server = http.createServer(app);
const serverFunc = () => {
  server.listen(port, () => {
    console.log(`Server listening on port ${port}`);
    fs.writeFileSync("port.txt", String(port));
  });
};

process.on('SIGTERM', () => {
  console.log('SIGTERM signal received: closing HTTP server')
  server.close(() => {
    console.log('HTTP server closed')
    fs.unlink('./token.txt', (err: String) => {
      if (err) {
        console.error(err);
      } else {
        console.log("Removed port.txt successfully.");
      }
    })
  })
})

server.on('error', (error: NodeJS.ErrnoException) => {
  if (error.code === 'EADDRINUSE') {
    console.error(`Port ${port} is already in use. If this is not expected,`);
    console.error(`you may have run this server previously, possibly on a zombie process.`);
    console.error(`Trying the next port number.`);
    port += 1;
    setTimeout(serverFunc, 1000);
  } else {
    console.error('Error starting server:', error);
  }
  // Optional: Perform additional actions like exiting the application
});
// Kick off the chain of server listen retries with the original port
serverFunc();
