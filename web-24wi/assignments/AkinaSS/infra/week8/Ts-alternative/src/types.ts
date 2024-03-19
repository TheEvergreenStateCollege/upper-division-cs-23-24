import { Request } from "express";
import { JwtPayload } from 'jsonwebtoken';

export interface ApiRequest extends Request {
  verifiedUser: string | JwtPayload
}