const { prisma, jwtSecret } = require('../config');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');

async function register(req, res) {
  try {
    const { nome, email, senha, tipoUsuario, cpf, rg, departamento, instituicaoId, cnpj, endereco } = req.body;
    const hashed = await bcrypt.hash(senha, 8);
    const user = await prisma.usuario.create({ data: { nome, email, senha: hashed, tipoUsuario } });
    console.log("Recebido no backend:", endereco);
    const enderecoCriado = endereco
      ? await prisma.endereco.create({
        data: {
          rua: endereco.rua,
          numero: endereco.numero,
          bairro: endereco.bairro,
          cidade: endereco.cidade,
          uf: endereco.uf,
          cep: endereco.cep
        }
      })
      : null;
    if (tipoUsuario === 'ALUNO') {
      await prisma.aluno.create({
        data: {
          id: user.id,
          cpf,
          rg,
          instituicaoId,
          enderecoId: enderecoCriado?.id
        }
      });
    } else if (tipoUsuario === 'PROFESSOR') {
      await prisma.professor.create({
        data: {
          id: user.id,
          cpf,
          departamento,
          instituicaoId
        }
      });
    } else if (tipoUsuario === 'PARCEIRO') {
      await prisma.parceiro.create({
        data: {
          id: user.id,
          cnpj
        }
      });
    }
    const token = jwt.sign({ userId: user.id }, jwtSecret, { expiresIn: '1h' });
    return res.status(201).json({ message: 'Registro bem-sucedido', token });
  } catch (err) {
    if (err.code === 'P2002' && err.meta?.target?.includes('email')) {
      return res.status(400).json({ error: 'E-mail já cadastrado.' });
    }
    console.error("Erro no registro:", err);
    return res.status(400).json({ error: err.message });
  }
}

async function login(req, res) {
  try {
    const { email, senha } = req.body;
    const user = await prisma.usuario.findUnique({ where: { email } });
    if (!user) return res.status(401).json({ error: 'Credenciais inválidas' });
    const valid = await bcrypt.compare(senha, user.senha);
    if (!valid) return res.status(401).json({ error: 'Credenciais inválidas' });
    const token = jwt.sign({ userId: user.id, tipo: user.tipoUsuario }, jwtSecret, { expiresIn: '1h' });
    return res.json({ message: 'Login bem-sucedido', token });
  } catch (err) {
    return res.status(500).json({ error: 'Erro no servidor' });
  }
}

module.exports = { register, login };