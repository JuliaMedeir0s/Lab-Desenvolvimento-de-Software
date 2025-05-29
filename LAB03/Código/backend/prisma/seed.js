const { PrismaClient } = require('@prisma/client');
const prisma = new PrismaClient();
const bcrypt = require('bcryptjs');

async function main() {
  // Instituições
  await prisma.instituicao.createMany({
    data: [
      { nome: 'PUC Minas' },
      { nome: 'UFMG' },
      { nome: 'CEFET-MG' },
      { nome: 'UNA' },
      { nome: 'FUMEC' },
    ],
    skipDuplicates: true, // Evita erro se já existir
  });
  console.log('Instituições criadas com sucesso!');

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
  const professores = [];
  for (let i = 1; i <= 5; i++) {
    const senhaHash = await bcrypt.hash('profpwd', 8);

    const usuario = await prisma.usuario.create({
      data: {
        nome: `Professor ${i}`,
        email: `prof${i}@ex.com`,
        senha: senhaHash,
        tipoUsuario: 'PROFESSOR'
      }
    });

    const professor = await prisma.professor.create({
      data: {
        id: usuario.id,
        cpf: `000.000.000-0${i}`,
        departamento: `Departamento ${i}`,
        instituicaoId: insts[i % insts.length].id, 
        saldo: 1000  
      }
    });

    professores.push(professor);
  }
  console.log('Professores criados com sucesso!');

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