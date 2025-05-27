const express = require('express');
const auth = require('../middlewares/authMiddleware');
const { listAll } = require('../controllers/transacaoController');
const router = express.Router();
router.use(auth);
router.get('/', listAll);
module.exports = router;