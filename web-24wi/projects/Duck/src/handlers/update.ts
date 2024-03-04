import { UPDATE_STATUS } from "@prisma/client";
import prisma from "./db"
import { Result } from "express-validator";

//Update all
export const getUpdates = async (req, res) => {
  const products = await prisma.product.findMany({
    where: {
      belongsToId: req.user.id
    }, include: {
      updates: true
    }
  });

  const updates = products.reduce((allUpdates: { id: String; createdAt: Date; updatedAt: Date; 
    title: String; body: String; status: UPDATE_STATUS; version: string | null; asset: string; 
    productId: string; userId: string | null; }[], product) => {
    return [...allUpdates, ...product.updates]
  }, []);

  res.json({data: updates});
};

//Update one
export const getOneUpdate = async (req, res) => {
  const update = await prisma.update.findFirst({
    where: {
      id: req.params.id
    }
  });

  res.json({data: update});
};

export const createUpdate = async (req, res) => {
  const product = await prisma.product.findUnique({
    where: {
      id: req.body.productId
    }
  })

  if (!product) {
    // does not belong to user
    return res.json({message: 'invalid'})
  }

  const update = await prisma.update.create({
    data: {
      title: req.body.title,
      body: req.body.body,
      // Assuming product.id is a string
      product: { connect: { id: String(product.id) } },
      // Add any other required properties here
    },
  });

  res.json({data: update})
}

export const theMainUpdate = async (req, res) => {
  const products = await prisma.product.findMany({
    where: {
      belongsToId: req.user.id
    },
    include: {
      updates: true
    }
  });

  const updates = products.reduce((allUpdates: { id: String; createdAt: Date; updatedAt: Date; 
    title: String; body: String; status: UPDATE_STATUS; version: string | null; asset: string; 
    productId: string; userId: string | null; }[], product) => {
    return [...allUpdates, ...product.updates]
  }, []);

  const match = updates.find(updates => updates.id === req.params.id);

  if (!match) {
    //handle unmatch case
    res.json({message: 'invalid'})
  }

  const updatedUpdate = await prisma.update.update({
    where: {
      id: req.params.id
    }, 
    data: req.body
  });

  res.json({data: updatedUpdate});
};

export const deleteUpdate = async (req, res) => {
  const products = await prisma.product.findMany({
    where: {
      belongsToId: req.user.id
    },
    include: {
      updates: true
    }
  });

  const updates = products.reduce((allUpdates: { id: String; createdAt: Date; updatedAt: Date; 
    title: String; body: String; status: UPDATE_STATUS; version: string | null; asset: string; 
    productId: string; userId: string | null; }[], product) => {
    return [...allUpdates, ...product.updates]
  }, []);

  const match = updates.find(updates => updates.id === req.params.id);

  if (!match) {
    //handle unmatch case
    res.json({message: 'invalid'})
  }

  const deleted = await prisma.update.delete({
    where: {
      id: req.params.id
    }
  });

  res.json({data: deleted});
};