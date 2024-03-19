import prisma from "../db";

//get one product
export const getOneUpdate = async (req, res) => {
  const update = await prisma.update.findUnique({
    where: {
      id: req.params.id
    },
  });
  res.json({ data: update });
};

// Get all products
export const getUpdates = async (req, res) => {
    const products = await prisma.product.findMany({
        where: {
            belongsToId: req.user.id
        },
        include: {
            Update: true
        }
    })  

    // No schema for this method, a schema is a better way to handle this at scale
    const updates = products.reduce((allUpdates, product) => {
        return [...allUpdates, ...product.Update]
    }, [])
  res.json({ data: updates});
};


// create product
export const createUpdate = async (req, res) => {
  const product = await prisma.product.create({
    data: {
      name: req.body.name,
      belongsToId: req.user.id,
    },
  });
  res.json({ data: product });
};

// Update product
export const updateUpdate = async (req, res) => {
  const updated = await prisma.product.update({
    where: {
      id: req.params.id,
      belongsToId: req.user.id,
    },
    data: {
      name: req.body.name,
    },
  });
  res.json({ data: updated });
};

// Delete product
export const deleteUpdate = async (req, res) => {
  const deleted = await prisma.product.delete({
    where: {
      id: req.params.id,
      belongsToId: req.user.id,
    },
  });
  res.json({ data: deleted });
};
