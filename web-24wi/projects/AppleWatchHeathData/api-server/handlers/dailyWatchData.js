const prisma = require('../db');
const { parse, format } = require('date-fns');


exports.createDailyWatchData = async (req, res) => {
  try {
    console.log(req.body.date);
    const parsedDate = parse(req.body.date, 'MM/dd/yyyy', new Date());
    const formattedDate = format(parsedDate, "yyyy-MM-dd'T'HH:mm:ss.SSSxxx");

    const newData = await prisma.dailyWatchData.create({
      data: {
        date: formattedDate,
        steps: Number(req.body.steps),
        distanceMiles: req.body.distanceMiles,
        flights: Number(req.body.flights),
        activeEnergyCals: Number(req.body.activeEnergyCals),
        handwashingSeconds: Number(req.body.handwashingSeconds),
        restingEnergyCals: Number(req.body.restingEnergyCals),
        soundLevel: Number(req.body.soundLevel),
        user:{
          "connect": {
            "id":  Number(req.body.userId),
          }
        } 
      },
    });

    res.json(newData);
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: 'Internal server error' });
  }
};
