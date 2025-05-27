const { prisma } = require('../config');

async function listAll(req, res) {
  try {
    const t = await prisma.transacao.findMany({ orderBy: { data: 'desc' } });
    return res.json(t);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
}

module.exports = { listAll };