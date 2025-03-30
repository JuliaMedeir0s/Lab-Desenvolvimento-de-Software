const axios = require('axios');

const API_USUARIOS = 'http://localhost:3000/usuario';

const clientePadrao = {
    nome: 'João da Silva',
    rg: '1234567',
    cpf: '12345678900',
    email: 'joao@email.com',
    senha: '123456',
    tipo: 'CLIENTE',
    profissao: 'Engenheiro',
    endereco: {
        rua: 'Rua Exemplo',
        numero: '100',
        bairro: 'Centro',
        cep: '12345678',
        cidade: 'São Paulo',
        estado: 'SP'
    },
    empregos: [
        { empresa: 'Empresa A', renda: 4500 }
    ]
};

async function popularCliente() {
    try {
        const res = await axios.get(API_USUARIOS);
        const jaTemCliente = res.data.some(u => u.tipo === 'CLIENTE');

        if (jaTemCliente) {
            console.log('Cliente já existe. Seed ignorada.');
            return;
        }

        await axios.post(API_USUARIOS, clientePadrao);
        console.log('Cliente padrão inserido com sucesso!');
    } catch (error) {
        if (error.response) {
            console.error('Erro ao popular cliente:', error.response.status, error.response.data);
        } else if (error.request) {
            console.error('Erro ao popular cliente: Sem resposta da API.');
        } else {
            console.error('Erro ao popular cliente:', error.message);
        }
    }
}

module.exports = popularCliente;
