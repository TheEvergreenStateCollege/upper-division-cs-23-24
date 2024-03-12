import express, { Request, Response, NextFunction } from 'express';
import router from './router';
import morgan from 'morgan';
import cors from 'cors';
import { protect } from './modules/auth';
import { createNewUser, signin } from './handlers/users' 
import * as path from 'path';

const app = express();

app.use(cors());
app.use(morgan('dev'));
app.use(express.static("public"));
app.use(express.json());
app.use(express.urlencoded({extended: true}));

app.get('/', async (req: Request, res: Response, next: NextFunction) => {
  res.sendFile(path.resolve("public/index.html"));
});

app.use('/api', protect, router);

app.use(async (err: any, req: Request, res: Response, next: NextFunction) => {
  console.log(err);
  res.json({message: `had an error: ${err.message}`});
});

app.post('/signup', createNewUser);
app.post('/signin', signin);

export default app;
