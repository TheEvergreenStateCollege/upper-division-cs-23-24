import express from 'express';
import router from './router';
import morgan from 'morgan';
import cors from 'cors';

const app = express();
const PORT = process.env.PORT || 3000;

app.use(cors())
app.use(morgan('dev'))
app.use(express.json());
app.use(express.urlencoded({extended: true}))

app.get('/', (req, res) => {
    console.log('Hello from express')
    res.status(200)
    res.json({ message: 'hello' });
});

app.use('/api', router);

app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});

export default app;
