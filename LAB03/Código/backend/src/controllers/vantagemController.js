const { prisma } = require('../config');

async function listVantagens(req, res) {
  try {
    const v = await prisma.vantagem.findMany();
    return res.json(v);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
}

async function myVantagens(req, res) {
  if (req.user.tipoUsuario !== 'PARCEIRO') return res.status(403).json({ error: 'Acesso negado' });
  try {
    const v = await prisma.vantagem.findMany({ where: { parceiroId: req.user.id } });
    return res.json(v);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
}

async function createVantagem(req, res) {
  if (req.user.tipoUsuario !== 'PARCEIRO') return res.status(403).json({ error: 'Acesso negado' });
  try {
    const data = req.body;
    const v = await prisma.vantagem.create({ data: { ...data, parceiroId: req.user.id } });
    return res.json({ message: 'Vantagem criada', v });
  } catch (err) {
    return res.status(400).json({ error: err.message });
  }
}

async function updateVantagem(req, res) {
  if (req.user.tipoUsuario !== 'PARCEIRO') return res.status(403).json({ error: 'Acesso negado' });
  try {
    const { id } = req.params;
    const v = await prisma.vantagem.update({ where: { id: Number(id) }, data: req.body });
    return res.json({ message: 'Vantagem atualizada', v });
  } catch (err) {
    return res.status(400).json({ error: err.message });
  }
}

async function deleteVantagem(req, res) {
  if (req.user.tipoUsuario !== 'PARCEIRO') return res.status(403).json({ error: 'Acesso negado' });
  try {
    const { id } = req.params;
    await prisma.vantagem.delete({ where: { id: Number(id) } });
    return res.status(204).send();
  } catch (err) {
    return res.status(400).json({ error: err.message });
  }
}

module.exports = { listVantagens, myVantagens, createVantagem, updateVantagem, deleteVantagem };