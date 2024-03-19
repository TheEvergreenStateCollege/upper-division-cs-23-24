import prisma from "./db";
import { Product } from "@prisma/client";

//Update all products in database
export const getProducts = async (req, res) => {
  //const products = await prisma.product.findMany();
  const products: Product[] = await prisma.product.findMany();

  const updates = products.reduce((allProducts) => {
    return [...allProducts]
  }, []);

  res.json({data: updates});
};

//Update one product in database
export const getOneProduct = async (req, res) => {
  const update = await prisma.product.findFirst({
    where: {
      id: req.params.id
    }
  });

  res.json({data: update});
};

export const createProduct = async (req, res, next) => {
  try {
    const product = await prisma.product.create({
      data: {
        image: req.body.image,
        name: req.body.name,
        description: req.body.description,
        // Assuming product.id is a string
        // product: { connect: { id: String(product.id) } },
      }
    });
  
    res.json({data: product});
  }
  catch (e) {
    next(e);
  }
}

export const updateProduct = async (req, res) => {
  const products = await prisma.product.findUnique({
    where: {
      id: req.params.id
    },
  });

  //const match = updates.find(updates => updates.id === req.params.id);
  
  if (products === null) {
    //handle unmatch case
    res.json({message: 'invalid product'});
  }

  const update = await prisma.product.update({
    where: {
      id: req.params.id
    }, 
    data: {
      name: req.body.name,
      description: req.body.description
    }
  });

  res.json({data: update});
};

export const deleteProduct = async (req, res) => {
  const products = await prisma.product.findUnique({
    where: {
      id: req.params.id
    },
  });

  //const match = updates.find(updates => updates.id === req.params.id);
  
  if (products === null) {
    //handle unmatch case
    res.json({message: 'invalid product'});
  }

  const deleted = await prisma.product.delete({
    where: {
      id: req.params.id
    }
  });

  res.json({data: deleted});
};