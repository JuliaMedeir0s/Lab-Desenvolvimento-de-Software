const jwt = require('jsonwebtoken');
const { jwtSecret, prisma } = require('../config');
async function authMiddleware(req, res, next) {
  const authHeader = req.headers.authorization;
  if (!authHeader) return res.status(401).json({ error: 'Token não fornecido.' });
  const [, token] = authHeader.split(' ');
  try {
    const { userId } = jwt.verify(token, jwtSecret);
    const user = await prisma.usuario.findUnique({ where: { id: userId } });
    if (!user) return res.status(401).json({ error: 'Usuário inválido.' });
    req.user = user;
    next();
  } catch (err) {
    return res.status(401).json({ error: 'Token inválido.' });
  }
}
module.exports = authMiddleware;