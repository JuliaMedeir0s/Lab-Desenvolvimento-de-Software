const { PrismaClient } = require('@prisma/client');
const prisma = new PrismaClient();
const bcrypt = require('bcryptjs');

async function main() {
  // Instituições
  const insts = [];
  for (let i = 1; i <= 5; i++) {
    insts.push(await prisma.instituicao.create({ data: { nome: `Instituição ${i}` } }));
  }
  // Endereços
  const ends = [];
  for (let i = 1; i <= 5; i++) {
    ends.push(await prisma.endereco.create({ data: {
      rua: `Rua ${i}`, numero: `${i}`, bairro: `Bairro ${i}`,
      cidade: 'CidadeX', uf: 'EX', complemento: '', cep: `0000${i}`
    }}));
  }
  // Parceiros
  const parceiros = [];
  for (let i = 1; i <= 5; i++) {
    const hash = await bcrypt.hash('partnerpwd', 8);
    const user = await prisma.usuario.create({ data: {
      nome: `Parceiro ${i}`, email: `parceiro${i}@ex.com`,
      senha: hash, tipoUsuario: 'PARCEIRO'
    } });
    parceiros.push(await prisma.parceiro.create({ data: { id: user.id, cnpj: `CNPJ${i}` } }));
  }
  // Vantagens
  const vantagens = [];
  for (let i = 1; i <= 5; i++) {
    vantagens.push(await prisma.vantagem.create({ data: {
      nome: `Vantagem ${i}`, descricao: 'Descrição', imagem: 'img.jpg',
      custo: 10 * i, parceiroId: parceiros[i-1].id
    } }));
  }
  // Professores
  const profs = [];
  for (let i = 1; i <= 5; i++) {
    const hash = await bcrypt.hash('profpwd', 8);
    const user = await prisma.usuario.create({ data: {
      nome: `Professor ${i}`, email: `prof${i}@ex.com`,
      senha: hash, tipoUsuario: 'PROFESSOR'
    } });
    profs.push(await prisma.professor.create({ data: {
      id: user.id, cpf: `CPF_PROF${i}`, departamento: `Dept ${i}`, instituicaoId: insts[i-1].id
    } }));
  }
  // Alunos
  const alunos = [];
  for (let i = 1; i <= 5; i++) {
    const hash = await bcrypt.hash('alunopwd', 8);
    const user = await prisma.usuario.create({ data: {
      nome: `Aluno ${i}`, email: `aluno${i}@ex.com`,
      senha: hash, tipoUsuario: 'ALUNO'
    } });
    alunos.push(await prisma.aluno.create({ data: {
      id: user.id, cpf: `CPF_ALU${i}`, rg: `RG${i}`,
      instituicaoId: insts[i-1].id, enderecoId: ends[i-1].id
    } }));
  }
}

main()
  .catch(e => { console.error(e); process.exit(1); })
  .finally(async () => { await prisma.$disconnect(); });