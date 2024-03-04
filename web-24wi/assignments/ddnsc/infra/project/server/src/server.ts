import express from 'express';
import router from './routes/router';
import morgan from 'morgan';
import cors from 'cors';
import { protect } from './auth/auth';
import { createNewUser, signIn } from './handlers/user';
import { handleInputErrors} from "./middleware/middleware";

const app = express();

app.use(cors());
app.use(morgan('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// app.get('/', (req, res, next) => {
//     // setTimeout(() => {
//     //     next(new Error('hello'));
//     // }, 1);
// });

// Separate routes for signing in and creating a new user
app.post('/register', handleInputErrors, createNewUser);
app.post('/signIn', handleInputErrors, signIn);

// Apply global middleware after defining routes
app.use('/api', protect, router);

app.use(express.static('public'));

app.use((err: Error, req, res, next) => {
    console.error(err);
    res.status(500).json({ error: err.message, stack: err.stack });
    next();
});

export default app;
