import prisma from "../db";
import { createJWT, hashPassword, comparePasswords } from "../modules/auth";

export const createNewUser = async (req, res) => {
  try {
  const user = await prisma.user.create({
    data: {
      username: req.body.username,
      password: await hashPassword(req.body.password),
    },
  });

  const token = createJWT(user);
  res.json({ token });
} catch (error) {
  console.error("Error creating user:", error);
  res.status(500).json({ message: "Try a different username" });
}
};

export const signin = async (req, res) => {
  try {
    const user = await prisma.user.findUnique({
      where: {
        username: req.body.username,
      },
    });

    if (!user) {
      res.status(401).json({ message: "Invalid username or password" });
      return;
    }

    const isValid = await comparePasswords(req.body.password, user.password);

    if (!isValid) {
      res.status(401).json({ message: "Invalid username or password" });
      return;
    }

    const token = createJWT(user);
    res.json({ token });
  } catch (error) {
    console.error("Error signing in:", error);
    res.status(500).json({ message: "Internal server error" });
  }
};
