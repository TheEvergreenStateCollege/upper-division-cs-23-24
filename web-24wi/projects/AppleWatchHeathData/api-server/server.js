
const router = require('./router');
const express = require("express");
const { parse, format } = require('date-fns');
const app = express();
const port = 5000;
const path = require("path");
const protect = require('../api-server/modules/auth'); // Assuming protect middleware is defined in a separate file
const { createNewUser, signin } = require('./handlers/user');
const http= require('http');
const morgan = require('morgan');
const cors = require('cors');
const dailyWatchDataController = require('./handlers/dailyWatchData');

app.use(cors())
app.use(morgan('dev'))
app.use(express.json())
app.use(express.urlencoded({extended: true}))

app.use(router);

app.post('/signup', createNewUser)
app.post('/signin', signin)



app.get("/", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  res.sendFile(path.resolve("public/index.html"));
});


app.get("/user", async (req, res ) => {
  const allUsers = await prisma.user.findmany();
  res.json(allUsers);
});

/*app.post("/user", async (req, res) => {
  const newUser = await prisma.user.create({
    data: {
      username: req.body.username,
      password: '',
    },
  });
  console.log("created");
});
*/
app.post("/daily-watch-data", dailyWatchDataController.createDailyWatchData);


app.get("/daily-watch-data", async (req, res) => {

const allData = await prisma.dailyWatchData.findMany();
res.json(allData);

});


const server = http.createServer(app); // Replace with your Express app creation
const serverFunc = () => {
  server.listen(port, () => {
    console.log(`Server listening on port ${port}`);
  });
};

server.on('error', (error) => {
  if (error.code === 'EADDRINUSE') {
    console.error(`Port ${port} is already in use. Trying the next port number.`);
    port += 1;
    setTimeout(serverFunc, 1000);
  } else {
    console.error('Error starting server:', error);
  }
  // Optional: Perform additional actions like exiting the application
});
// Kick off the chain of server listen retries with the original port
serverFunc();