import jwt, { JwtPayload } from "jsonwebtoken";
import * as bcrypt from "bcrypt";

// import { Response, NextFunction } from "express";
// import { User } from "@prisma/client";

// import { ApiRequest } from "../types";

export const comparePasswords = async (password, hash) => {
  return await bcrypt.compare(password, hash);
};

export const hashPassword = (password) => {
  return bcrypt.hash(password, 5);
};

export const createJWT = (user) => {
  const token = jwt.sign(
    { id: user.id, username: user.username },
    process.env.JWT_SECRET
  );
  return token;
};

export const protect = (req, res, next) => {
  const bearer = req.headers.authorization;

  if (!bearer) {
    res.status(401);
    res.send("Not authorized");
    return;
  }

  const [, token] = bearer.split(" ");
  if (!token) {
    console.log("here for the token");
    res.status(401);
    res.send("Not authorized");
    return;
  }

  try {
    const payload = jwt.verify(token, process.env.JWT_SECRET);
    req.user = payload;
    console.log(payload);
    next();
    return;
  } catch (e) {
    console.error(e);
    res.status(401);
    res.send("Not authorized");
    return;
  }

  // try {
  //   const verifiedUser: String | JwtPayload = jwt.verify(token, process.env.JWT_SECRET);
  //   req.verifiedUser = verifiedUser;
  //   console.log(verifiedUser);
  //   next();
  //   return;
  // } catch (e) {
  //   console.error(e);
  //   res.status(401);
  //   res.send("Not authorized");
  //   return;
  // }
};
