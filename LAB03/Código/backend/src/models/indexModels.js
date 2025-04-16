const sequelize = require('sequelize');
const database = require('../database/db');

const User = require('./user');
const Instituicao = require('./instituicao');
const Parceiro = require('./parceiro');
const Aluno = require('./aluno');
const Professor = require('./professor');
const Vantagem = require('./vantagem');
const Transacao = require('./transacao');

// 1:1 - User -> Aluno
User.hasOne(Aluno, { foreignKey: 'userId' });
Aluno.belongsTo(User, { foreignKey: 'userId' });

// 1:1 - User -> Professor
User.hasOne(Professor, { foreignKey: 'userId' });
Professor.belongsTo(User, { foreignKey: 'userId' });

// 1:1 - User -> Parceiro
User.hasOne(Parceiro, { foreignKey: 'userId' });
Parceiro.belongsTo(User, { foreignKey: 'userId' });

// N:1 - Aluno -> Instituicao
Instituicao.hasMany(Aluno, { foreignKey: 'instituicaoId' });
Aluno.belongsTo(Instituicao, { foreignKey: 'instituicaoId' });

// N:1 - Professor -> Instituicao
Instituicao.hasMany(Professor, { foreignKey: 'instituicaoId' });
Professor.belongsTo(Instituicao, { foreignKey: 'instituicaoId' });

// N:1 - Transacao -> Aluno
Aluno.hasMany(Transacao, { foreignKey: 'alunoId' });
Transacao.belongsTo(Aluno, { foreignKey: 'alunoId' });

// N:1 - Transacao -> Professor
Professor.hasMany(Transacao, { foreignKey: 'professorId' });
Transacao.belongsTo(Professor, { foreignKey: 'professorId' });

// N:1 - Transacao -> Vantagem
Vantagem.hasMany(Transacao, { foreignKey: 'vantagemId' });
Transacao.belongsTo(Vantagem, { foreignKey: 'vantagemId' });

// N:1 - Vantagem -> Parceiro
Parceiro.hasMany(Vantagem, { foreignKey: 'parceiroId' });
Vantagem.belongsTo(Parceiro, { foreignKey: 'parceiroId' });

module.exports = {
  User,
  Instituicao,
  Parceiro,
  Aluno,
  Professor,
  Vantagem,
  Transacao
};