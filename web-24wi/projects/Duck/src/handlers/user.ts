import { createJWT, hashPassword, comparePasswords } from "../modules/auth";
import prisma from "./db";

export const createNewUser = async (req, res, next) => {
  try {
  const hash = await hashPassword(req.body.password);

  const user = await prisma.user.create({
    data: {
      username: req.body.username,
      password: hash,
    },
  });

  console.log("User created:", user);
  const token = createJWT(user);
  res.status(201).json({ token });
  }
  catch (e) {
    e.type = 'input';
    console.error("Error creating user:", e);
    next(e);
  }
};

export const signin = async (req, res) => {
    const user = await prisma.user.findUnique({
      where: { username: req.body.username },
    });
  
    const isValid = await comparePasswords(req.body.password, user?.password);
  
    if (!isValid) {
      res.status(401);
      res.send("Invalid username or password");
      return;
    }
  
    const token = createJWT(user);
    res.json({ token });
  };
