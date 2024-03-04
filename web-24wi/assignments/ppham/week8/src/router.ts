import { Router } from 'express';
import { body, oneOf, validationResult } from 'express-validator';
import { createCity, getOneCity, getCities } from './handlers/uscity';
//import { handleInputErrors } from './modules/middleware.ts';

const path = require('path');

const router = Router();

const cityValidators = [
  body('name').isString(),
  body('latitude').isNumeric(),
  body('longitude').isNumeric(),
  body('population').isNumeric(),
  body('authorId').isNumeric(),  
]

/**
 * USCities
 */
router.get('/uscities', getCities);
router.get('/uscity/:name', getOneCity);
router.post('/uscity', cityValidators,
  createCity,
);

router.get('/ssh-key', (req, res, next) => {
  res.sendFile(path.resolve("id_ecdsa.pub"));
})

export default router;
