import { Router } from 'express';
import { body, oneOf, validationResult } from 'express-validator';
import { createCity, getOneCity, getCities } from './handlers/uscity';
//import { handleInputErrors } from './modules/middleware.ts';

const router = Router();

/**
 * USCities
 */
router.get('/uscities', getCities);
router.get('/uscity/:name', getOneCity);
router.post('/uscity',
  body('name').isString(),
  body('latitude').isNumeric(),
  body('longitude').isNumeric(),
  body('population').isNumeric(),
  body('belongsToId').isNumeric(),
  createCity,
);

export default router;
