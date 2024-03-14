import { createJWT, hashPassword, comparePasswords } from "../modules/auth";
import prisma from "./db";

async function findUser(username: string) {
  const result = await prisma.user.findUnique({
    where: { username },
  });

  if (result === null) return undefined;

  return result;
};

export const createNewUser = async (req, res, next) => {
  console.log(req.body.username);
  try {
    //const salt = bcrypt.genSaltsync(10);
    //const hashes = bcrypt.hashsync(req.password, salt);
    let userExist = await findUser(req.body.username)
    if (userExist != undefined) {
      //the user already exist
      res.json({ok: false, message: "user already exist"});
    }

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
  //console.log(JSON.stringify(req.headers));
  console.log(JSON.stringify(req.body));

  const userfound = findUser(req.body.username)

  if (userfound) {
    //console.log(JSON.stringify(req.headers));
    console.log(JSON.stringify(req.body));

    const user = await prisma.user.findUnique({
      where: { username: req.body.username },
    });
  
    const isValid = await comparePasswords(req.body.password, user?.password);
  
    if (!isValid) {
      res.status(401);
      res.json({ message :"Invalid username or password"} );
      return;
    }

    // if (userfound) {
    //   //found user, check password
    //   if (bcrypt.comparesync(req.body.password, userfound.password)) {
    //     res.json({ok: true, name: userfound.name, email: userfound.email})
    //   }
    // }
    // else {
    //   //user not found
    //   res.send({ok: false, message: "wrong credential"})
    // }
  
    const token = createJWT(user);
    res.json({ token });
  }
  else {
    res.status(401);
    res.json({ message :"Invalid username or password"} );
    return;
  }
};
