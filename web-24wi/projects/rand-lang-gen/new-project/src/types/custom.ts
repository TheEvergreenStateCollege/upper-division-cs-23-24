// Custom type merging from 
// https://blog.logrocket.com/extend-express-request-object-typescript/
import { Request } from 'express';
import { JwtPayload } from 'jsonwebtoken';

// We want to merge this type declaration with Prisma's User as well
export type User = {
  authenticationToken: string | JwtPayload
  id: Number
}