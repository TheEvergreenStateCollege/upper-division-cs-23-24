import { User } from "../custom";
import { Request } from "express";

declare global {
  namespace Express {
    export interface Request {
      user?: User;
    }
  }
}

// to make the file a module and avoid the TypeScript error
export {
  Request
}