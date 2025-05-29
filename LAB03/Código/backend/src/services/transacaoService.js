const { prisma } = require('../config');
const { generateCodigo } = require('../utils/codeGenerator');
const { sendEmail } = require('../utils/email');

async function enviar(alunoId, professorId, valor, mensagem) {
  return prisma.$transaction(async (tx) => {
    const prof = await tx.professor.findUnique({ where: { id: professorId }, include: { usuario: true } });
    if (prof.saldo < valor) throw new Error('Saldo insuficiente');

    const aluno = await tx.aluno.findUnique({ where: { id: alunoId }, include: { usuario: true } });

    await tx.professor.update({ where: { id: professorId }, data: { saldo: prof.saldo - valor } });
    await tx.aluno.update({ where: { id: alunoId }, data: { saldo: aluno.saldo + valor } });

    const codigo = generateCodigo('ENVIO');
    const transacao = await tx.transacao.create({
      data: {
        tipo: 'ENVIO',
        valor,
        mensagem,
        codigo,
        alunoId,
        professorId,
      },
    });

    const dataEnvio = new Date().toLocaleString('pt-BR');
    const htmlMessage = `
      <h2 style="color:#2c3e50;">🎉 Você recebeu ${valor} moedas!</h2>
      <p><strong>Aluno:</strong> ${aluno.usuario.nome}</p>
      <p><strong>Professor:</strong> ${prof.usuario.nome}</p>
      <p><strong>Motivo:</strong> ${mensagem}</p>
      <p><strong>Código da transação:</strong> <code>${codigo}</code></p>
      <p><strong>Data:</strong> ${dataEnvio}</p>
      <hr>
      <p>📌 <strong>Informações importantes:</strong></p>
      <ul>
        <li>Essas moedas podem ser usadas para resgatar vantagens disponíveis no sistema.</li>
        <li>Confira seu saldo e histórico de transações na aba de extrato.</li>
        <li>Em caso de dúvidas, entre em contato com o suporte.</li>
      </ul>
      <p>Atenciosamente, <br>Equipe de Mérito Estudantil</p>
    `;

    await sendEmail(
      aluno.usuario.email,
      '🎓 Novo crédito de moedas recebido!',
      `Você recebeu ${valor} moedas de ${prof.usuario.nome}. Código: ${codigo}`,
      htmlMessage
    );

    return transacao;
  }, { timeout: 15000 }); // Timeout configurado para 15 segundos
}

async function resgatar(alunoId, vantagemId) {
  return prisma.$transaction(async (tx) => {
    const aluno = await tx.aluno.findUnique({ where: { id: alunoId }, include: { usuario: true } });
    const vant = await tx.vantagem.findUnique({ where: { id: Number(vantagemId) }, include: { parceiro: { include: { usuario: true } } } });

    if (aluno.saldo < vant.custo) throw new Error('Saldo insuficiente');

    await tx.aluno.update({ where: { id: alunoId }, data: { saldo: aluno.saldo - vant.custo } });

    const codigo = generateCodigo('RESGATE');
    const transacao = await tx.transacao.create({
      data: {
        tipo: 'RESGATE',
        valor: vant.custo,
        mensagem: 'Resgate',
        codigo,
        alunoId,
        vantagemId: Number(vantagemId),
      },
    });

    const htmlMessageAluno = `
      <h2 style="color:#2c3e50;">🎉 Resgate realizado com sucesso!</h2>
      <p><strong>Aluno:</strong> ${aluno.usuario.nome}</p>
      <p><strong>Parceiro:</strong> ${vant.parceiro.usuario.nome}</p>
      <p><strong>Vantagem:</strong> ${vant.nome}</p>
      <p><strong>Código do resgate:</strong> <code>${codigo}</code></p>
      <p><strong>Data:</strong> ${new Date().toLocaleString('pt-BR')}</p>
      <hr>
      <p>📌 <strong>Informações importantes:</strong></p>
      <ul>
        <li>Use o código acima para validar seu resgate com o parceiro.</li>
        <li>Confira seu saldo e histórico de transações na aba de extrato.</li>
        <li>Em caso de dúvidas, entre em contato com o suporte.</li>
      </ul>
      <p>Atenciosamente, <br>Equipe de Mérito Estudantil</p>
    `;

    const htmlMessageParceiro = `
      <h2 style="color:#2c3e50;">🎉 Um resgate foi realizado!</h2>
      <p><strong>Aluno:</strong> ${aluno.usuario.nome}</p>
      <p><strong>Parceiro:</strong> ${vant.parceiro.usuario.nome}</p>
      <p><strong>Vantagem:</strong> ${vant.nome}</p>
      <p><strong>Código do resgate:</strong> <code>${codigo}</code></p>
      <p><strong>Data:</strong> ${new Date().toLocaleString('pt-BR')}</p>
      <hr>
      <p>📌 <strong>Informações importantes:</strong></p>
      <ul>
        <li>Valide o código acima com o aluno para confirmar o resgate.</li>
        <li>Em caso de dúvidas, entre em contato com o suporte.</li>
      </ul>
      <p>Atenciosamente, <br>Equipe de Mérito Estudantil</p>
    `;

    await sendEmail(aluno.usuario.email, '🎓 Resgate realizado com sucesso!', `Seu código: ${codigo}`, htmlMessageAluno);
    await sendEmail(vant.parceiro.usuario.email, '🎓 Resgate realizado!', `Código: ${codigo}`, htmlMessageParceiro);

    return transacao;
  }, { timeout: 15000 }); // Timeout configurado para 15 segundos
}

module.exports = { enviar, resgatar };