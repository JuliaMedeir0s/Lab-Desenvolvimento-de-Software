const { prisma } = require('../config');
const transacaoService = require('../services/transacaoService');

async function getMe(req, res) {
  if (req.user.tipoUsuario !== 'ALUNO') return res.status(403).json({ error: 'Acesso negado' });
  try {
    const aluno = await prisma.aluno.findUnique({
      where: { id: req.user.id },
      include: { usuario: true, instituicao: true, endereco: true }
    });
    return res.json(aluno);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
}

async function updateMe(req, res) {
  if (req.user.tipoUsuario !== 'ALUNO') return res.status(403).json({ error: 'Acesso negado' });
  try {
    const updated = await prisma.aluno.update({ where: { id: req.user.id }, data: req.body });
    return res.json({ message: 'Atualização bem-sucedida', updated });
  } catch (err) {
    return res.status(400).json({ error: err.message });
  }
}

async function extrato(req, res) {
  if (req.user.tipoUsuario !== 'ALUNO') return res.status(403).json({ error: 'Acesso negado' });
  try {
    const transacoes = await prisma.transacao.findMany({
      where: { alunoId: req.user.id },
      orderBy: { data: 'desc' }
    });
    const aluno = await prisma.aluno.findUnique({ where: { id: req.user.id } });
    return res.json({ saldo: aluno.saldo, transacoes });
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
}

async function resgatar(req, res) {
  if (req.user.tipoUsuario !== 'ALUNO') return res.status(403).json({ error: 'Acesso negado' });
  try {
    const { vantagemId } = req.params;
    const result = await transacaoService.resgatar(req.user.id, vantagemId);
    return res.json({ message: 'Resgate concluído', codigo: result.codigo });
  } catch (err) {
    return res.status(400).json({ error: err.message });
  }
}

async function getAll(req, res) {
  try {
    const alunos = await prisma.aluno.findMany({
      include: { usuario: true, instituicao: true, endereco: true }
    });
    return res.json(alunos);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
}

async function getById(req, res) {
  try {
    const aluno = await prisma.aluno.findUnique({
      where: { id: req.params.id },
      include: { usuario: true, instituicao: true, endereco: true }
    });
    if (!aluno) return res.status(404).json({ error: 'Aluno não encontrado' });
    return res.status(200).json(aluno);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
}

module.exports = { getMe, updateMe, extrato, resgatar, getAll, getById };