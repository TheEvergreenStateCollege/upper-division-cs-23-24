import { validationResult } from "express-validator";

export const handleInputErrors = (req, res) => {
    const errors = validationResult(req);

    if(!errors.isEmpty()) {
        res.status(400);
        res.json({errors: errors.array() });
    }
}
