import { Router } from "express";
import { body, validationResult } from "express-validator"
import { createWord, deleteWord, getOneWord, getWords } from './handlers/words';
import { getAllUsers } from './handlers/users';
import { create } from "domain";
const path = require('path');
const router = Router();


const wordValidators = [
  body('name').isString(),
  body('define').isString(),
  body('language').isString(),
  body('authorId').isNumeric(),
]
const deleteValidator = [
  body('id').isNumeric()
]

router.get('/word', getWords);
router.get('/word/:name', getOneWord);
router.post('/word', ...wordValidators,
  createWord
);

router.delete('/word', ...deleteValidator,
  deleteWord
);

export default router;