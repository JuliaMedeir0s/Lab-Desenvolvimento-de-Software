const { prisma } = require('../config');

async function getMe(req, res) {
  if (req.user.tipoUsuario !== 'PARCEIRO') return res.status(403).json({ error: 'Acesso negado' });
  try {
    const p = await prisma.parceiro.findUnique({
      where: { id: req.user.id },
      include: { usuario: true, vantagens: true }
    });
    return res.json(p);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
}

async function updateMe(req, res) {
  if (req.user.tipoUsuario !== 'PARCEIRO') return res.status(403).json({ error: 'Acesso negado' });
  try {
    const updated = await prisma.parceiro.update({ where: { id: req.user.id }, data: req.body });
    return res.json({ message: 'Atualização bem-sucedida', updated });
  } catch (err) {
    return res.status(400).json({ error: err.message });
  }
}

async function getAll(req, res) {
  try {
    const parceiros = await prisma.parceiro.findMany({
      include: { usuario: true, vantagens: true }
    });
    return res.json(parceiros);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
}

async function getById(req, res) {
  const { id } = req.params;
  try {
    const parceiro = await prisma.parceiro.findUnique({
      where: { id },
      include: { usuario: true, vantagens: true }
    });
    if (!parceiro) return res.status(404).json({ error: 'Parceiro não encontrado' });
    return res.json(parceiro);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
}

module.exports = { getMe, updateMe, getAll, getById };