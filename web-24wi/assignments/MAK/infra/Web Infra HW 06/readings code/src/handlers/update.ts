import prisma from "../db"

//getOneUpdate--------------------------------------------------
export const getOneUpdate = async (req, res) => {
  const update = await prisma.update.findFirst({
    where: {
      id: req.params.id
    }
  })

  res.json({data: update})
}

//getUpdates--------------------------------------------------
export const getUpdates = async (req, res) => {
  const product = await prisma.product.findMany({
    where: {
      belongsToId: req.user.id
    },
    include: {
      updates: true
    }
  })

  const updates = product.reduce((allUpdates, product) => { //not the right why to do this
    return [...allUpdates, ...product.updates]
  }, [])

  res.json({data: updates})
}

//createUpdate--------------------------------------------------
export const createUpdate = async (req, res) => {
  //const{productId, ...rest} = res.body
  const product = await prisma.product.findUnique({
    where: {
      id: req.body.productId
    }
  })

  if (!product) {
    //user don't not own product
    return res.json({message: 'you do not own this'})
  }

  const update = await prisma.update.create({
    data: {
      title: req.body.title,
      body: req.body.body,
      product: {connect: {id: product.id}}
    }
  })

  res.json({data: update})
}

//updateUpdate--------------------------------------------------
export const updateUpdate = async (req, res) => {
  const products = await prisma.product.findMany({
    where: {
      belongsToId: req.user.id,
    },
    include: {
      updates: true
    }
  })

  const updates = products.reduce((allUpdates, product) => { //not the right why to do this
    return [...allUpdates, ...product.updates]
  }, [])

  const match = updates.find(update => update.id == req.params.id)

  if (!match){
    //handle this
    return res.json({message: 'no'})
  }

  //if match
  const updateUpdate = await prisma.update.update({
    where: {
      id: req.params.id
    },
    data: req.body
  })

  res.json({data: updateUpdate})
}

//deleteUpdate--------------------------------------------------
export const deleteUpdate = async (req, res) => {
  const products = await prisma.product.findMany({
    where: {
      belongsToId: req.user.id,
    },
    include: {
      updates: true
    }
  })

  const updates = products.reduce((allUpdates, product) => { //not the right why to do this
    return [...allUpdates, ...product.updates]
  }, [])

  const match = updates.find(update => update.id == req.params.id)

  if (!match){
    //handle this
    return res.json({message: 'no'})
  }

  const deleted = await prisma.update.delete({
    where: {
      id: req.params.id
    }
  })

  res.json({data: deleted})
}