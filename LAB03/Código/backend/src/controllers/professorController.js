const { prisma } = require('../config');
const transacaoService = require('../services/transacaoService');

async function getMe(req, res) {
  if (req.user.tipoUsuario !== 'PROFESSOR') return res.status(403).json({ error: 'Acesso negado' });
  try {
    const prof = await prisma.professor.findUnique({
      where: { id: req.user.id },
      include: { usuario: true, instituicao: true }
    });
    return res.json(prof);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
}

async function updateMe(req, res) {
  if (req.user.tipoUsuario !== 'PROFESSOR') return res.status(403).json({ error: 'Acesso negado' });
  try {
    const updated = await prisma.professor.update({ where: { id: req.user.id }, data: req.body });
    return res.json({ message: 'Atualização bem-sucedida', updated });
  } catch (err) {
    return res.status(400).json({ error: err.message });
  }
}

async function transacoes(req, res) {
  if (req.user.tipoUsuario !== 'PROFESSOR') return res.status(403).json({ error: 'Acesso negado' });
  try {
    const transacoes = await prisma.transacao.findMany({
      where: { professorId: req.user.id },
      orderBy: { data: 'desc' }
    });
    const prof = await prisma.professor.findUnique({ where: { id: req.user.id } });
    return res.json({ saldo: prof.saldo, transacoes });
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
}

async function enviar(req, res) {
  if (req.user.tipoUsuario !== 'PROFESSOR') return res.status(403).json({ error: 'Acesso negado' });
  try {
    const alunoId = parseInt(req.params.alunoId, 10);
    const { valor, mensagem } = req.body;
    if (isNaN(alunoId)) {
      return res.status(400).json({ error: 'ID de aluno inválido.' });
    }
    const result = await transacaoService.enviar(alunoId, req.user.id, valor, mensagem);
    return res.json({ message: 'Envio concluído', transacao: result });
  } catch (err) {
    return res.status(400).json({ error: err.message });
  }
}

async function getAll(req, res) {
  try {
    const professores = await prisma.professor.findMany({
      include: { usuario: true, instituicao: true }
    });
    return res.json(professores);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
}

async function getById(req, res) {
  const { id } = req.params;
  try {
    const professor = await prisma.professor.findUnique({
      where: { id },
      include: { usuario: true, instituicao: true }
    });
    if (!professor) return res.status(404).json({ error: 'Professor não encontrado' });
    return res.json(professor);
  } catch (err) {
    return res.status(500).json({ error: err.message });
  }
}

async function listarAlunos(req, res) {
  if (req.user.tipoUsuario !== "PROFESSOR") {
    return res.status(403).json({ error: "Acesso negado" });
  }

  try {
    const professor = await prisma.professor.findUnique({
      where: { id: req.user.id },
      include: { instituicao: true },
    });

    const alunos = await prisma.aluno.findMany({
      where: { instituicaoId: professor.instituicaoId },
      include: { usuario: true },
    });

    res.json(alunos);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
}





module.exports = { getMe, updateMe, transacoes, enviar, getAll, getById, listarAlunos };