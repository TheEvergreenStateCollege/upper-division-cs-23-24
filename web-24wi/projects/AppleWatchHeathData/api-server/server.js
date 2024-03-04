
const router = require('./router');
const express = require("express");
const { parse, format } = require('date-fns');
const app = express();
const port = 5000;
const path = require("path");
const protect = require('../api-server/modules/auth'); // Assuming protect middleware is defined in a separate file
const { createNewUser, signin } = require('./handlers/user');



app.post('/user', createNewUser)
app.post('/signin', signin)


/**
 * app.[method]([route], [route handler])
 */


app.get("/", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  res.sendFile(path.resolve("public/index.html"));
});



/*
// Return search hit given :hit  URL route parameters
app.get("/search-hit/:hit", (req, res) => {
  // sending back an HTML file that a browser can render on the screen.
  res.sendFile(path.resolve(`pages/search-hit-${req.params.hit}.html`));
});
*/

// http://tor.arcology.builders:5000

//Sevres a loist of data to a static page in final project directory


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

app.get("/daily-watch-data", async (req, res) => {

const allData = await prisma.dailyWatchData.findMany();
res.json(allData);

});



app.post("/daily-watch-data", async ( req, res) => {

  const parsedDate = parse(req.body.date, 'MM/dd/yyyy', new Date());
  const formattedDate = format(parsedDate, "yyyy-MM-dd'T'HH:mm:ss.SSSxxx");
  console.log(formattedDate);

  const newData = await prisma.dailyWatchData.create({
    data: {
      date: formattedDate,           
      steps: req.body.steps,              
      distanceMiles : req.body.distanceMiles ,     
      flights : req.body.flights,           
      activeEnergyCals: req.body.activeEnergyCals,  
      handwashingSeconds: req.body.handwashingSeconds, 
      restingEnergyCals: req.body.restingEnergyCals,
      soundLevel: req.body.soundLevel,  
      userId: Number(req.body.userId),  
    },
  });
  res.json(req.body);
});

// creates and starts a server for our API on a defined port
app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});