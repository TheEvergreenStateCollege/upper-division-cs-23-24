import prisma from "./db";

//Get all products from wishlist
export const getCarts = async (req, res) => {
  const user = await prisma.user.findUnique({
    where: {
      id: req.user.id
    }, include: {
      wishlist: true
    }
  });

  res.json({data: user?.wishlist});
};

//Get one product from wishlist
export const getOneCart = async (req, res) => {
  const id = req.params.id

  const product = await prisma.wishlist.findFirst({
    where: {
      productId: id,
      belongsToId: req.user.id
    }
  });

  res.json({data: product});
};

//Create new wishlist
export const createCart = async (req, res, next) => {
  try {
    const wishlist = await prisma.wishlist.create({
    data: {
      name: req.body.name,
      body: req.body.body,
      productId: req.product.id,
      belongsToId: req.user.id
    }
  });

  res.json({data: wishlist});
  }
  catch (e) {
    next(e);
  }
};

export const updateCart = async (req, res) => {
  const updated = await prisma.wishlist.update({
    where: {
      id: req.params.id, 
      belongsToId: req.user.id
      //id_belongsToId: {id: req.params.id, belongsToId: req.user.id}
    }, data: {
      name: req.body.name,
      body: req.body.body
    }
  });

  res.json({data: updated});
};

export const deleteCart = async (req, res) => {
  const deleted = await prisma.wishlist.delete({
    where: {
      id: req.params.id, 
      belongsToId: req.user.id
      //id_belongsToId: {id: req.params.id, belongsToId: req.user.id}
    } 
  });

  res.json({data: deleted});
};