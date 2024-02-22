import {Router} from 'express'
import { body, oneOf, validationResult } from 'express-validator'
import { handleInputErrors } from './modules/middleware'
import { createProduct, deletProduct, getProduct, getProducts } from './handlers/product'
import { createUpdate, deleteUpdate, getUpdate, getUpdates, updateUpdate } from './handlers/update'

const router = Router()

//product
router.get('/product', getProducts)

router.get('/product/:id', getProduct)
router.put('/product/:id', body('name'),handleInputErrors, (req, res) => {
    
})
router.post('/product',body('name').isString(), handleInputErrors,createProduct)
router.delete('/product/:id', deletProduct)

//update
router.get('/update', getUpdates)
router.get('/update/:id', getUpdate)
router.put('/update/:id', 
    body('title').optional,
    body('body').optional, 
    body('status').isIn(['IN_PROGRESS', 'SHIPPED', 'DEPRECATED']).optional(),
    body('version').optional,
    updateUpdate
)
router.post('/update', 
    body('title').exists().isString(),
    body('body').exists().isString(),
    body('productId').exists().isString(),
    createUpdate
  )
router.delete('/update/:id', deleteUpdate)

// updatepoint
router.get('/updatepoint', () => {})
router.get('/updatepoint/:id', () => {})
router.put('/updatepoint/:id',
    body('name').optional().isString(), 
    body('description').optional().isString(),
    () => {} 
)
router.post('/updatepoint', 
    body('name').isString(), 
    body('description').isString(),
    body('updateId').exists().isString(),
    () => {}
)
router.delete('/updatepoint/:id', () => {})

router.use((err, req,res, next) => {
    console.log(err)
    res.json({message: 'in router handler'})
})

export default router