const prisma = require('../db');
const { parse, format } = require('date-fns');


exports.createDailyWatchData = async (req, res) => {
  try {
    const parsedDate = parse(req.body.date, 'MM/dd/yyyy', new Date());
    const formattedDate = format(parsedDate, "yyyy-MM-dd'T'HH:mm:ss.SSSxxx");

    const newData = await prisma.dailyWatchData.create({
      data: {
        date: formattedDate,
        steps: req.body.steps,
        distanceMiles: req.body.distanceMiles,
        flights: req.body.flights,
        activeEnergyCals: req.body.activeEnergyCals,
        handwashingSeconds: req.body.handwashingSeconds,
        restingEnergyCals: req.body.restingEnergyCals,
        soundLevel: req.body.soundLevel,
        userId: Number(req.body.userId),
      },
    });

    res.json(newData);
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: 'Internal server error' });
  }
};
