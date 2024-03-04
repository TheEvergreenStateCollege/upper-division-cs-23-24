import * as http from "http";
import * as dotenv from "dotenv";
dotenv.config();

import app from "./server";
import { error } from "console";

// Start with the default port, some servers may already be listening on port 5000
const DEFAULT_PORT: number = Number(process.env.API_PORT) || 5000;
let port = DEFAULT_PORT;

// Wrap the express app with an HTTP server manually
const server = http.createServer(app);
const serverFunc = () => {
  server.listen(port, () => {
    console.log(`server listening on port ${port}`);
  });
};

server.on('error', (error: NodeJS.ErrnoException) => {
  if (error.code === 'EADDRINUSE') {
    console.error(`Port ${port} is already in use. If this is not expected, `);
    console.error(`you may have run the server previously, possibly on a zombie process.`);
    console.error(`Trying the next port number`);
    port += 1;
    setTimeout(serverFunc, 1000);
  }
  else {
    console.error('Error starting server:', error);
  }
  // Optional: Perform additional actions like exiting the application
});
// Kick off the chain of server listening retries with the original port
serverFunc();

// Command to run the server
// node ./dist/index.js