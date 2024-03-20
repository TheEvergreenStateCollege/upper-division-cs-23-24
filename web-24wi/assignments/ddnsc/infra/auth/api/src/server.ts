import express from 'express';
import cors from 'cors';
import router from './router';
import morgan from 'morgan';
import { protect } from './modules/auth';
import { createNewUser, signin } from './handlers/user';

const app = express();

app.use(cors({
    origin: 'http://localhost:3000'
}));

app.use(morgan('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.get('/', (req, res, next) => {
    setTimeout(() => {
        next(new Error('hello'));
    }, 1);
});

app.use('/api', protect, router);

app.post('/register', createNewUser);
app.post('/login', signin);

app.use((err: { message: any }, req: any, res: { json: (arg0: { message: string }) => void }, next: any) => {
    console.log(err);
    res.json({ message: `had an error: ${err.message}` });
});

const PORT = process.env.PORT || 5000;
app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});

export {app};