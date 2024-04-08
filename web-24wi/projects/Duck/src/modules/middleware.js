import { body, validationResult } from "express-validator";

export const handleInputErrors = (req, res, next) => {
    const errors = validationResult(req);
    //console.log(errors);

  if (!errors.isEmpty()) {
    res.status(400);
    res.json({ errors: errors.array() });
  } else {
    next();
  }
};

export const status = [body('IN_PROGRESS'), body('LIVE'), body('DEPRECATED'), body('SHIPPED'), body('ARCHIVED')];