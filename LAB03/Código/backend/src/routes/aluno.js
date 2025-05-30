const express = require('express');
const auth = require('../middlewares/authMiddleware');
const { getMe, updateMe, extrato, resgatar, getAll, getById } = require('../controllers/alunoController');
const router = express.Router();
router.use(auth);
router.get('/me', getMe);
router.put('/me', updateMe);
router.get('/me/extrato', extrato);
router.post('/me/resgatar/:vantagemId', resgatar);
router.get('/', getAll);
router.get('/:id', getById);
module.exports = router;