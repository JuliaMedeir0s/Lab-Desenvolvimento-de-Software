const axios = require('axios');

const API_URL = 'http://localhost:3000/veiculo';

const veiculos = [
    {
        matricula: 'MAT-001',
        modelo: 'Corolla',
        marca: 'Toyota',
        placa: 'ABC-1234',
        ano: 2022,
        cor: 'Prata',
        valor: 90000.00
    },
    {
        matricula: 'MAT-002',
        modelo: 'Civic',
        marca: 'Honda',
        placa: 'XYZ-5678',
        ano: 2021,
        cor: 'Preto',
        valor: 95000.00
    },
    {
        matricula: 'MAT-003',
        modelo: 'Argo',
        marca: 'Fiat',
        placa: 'QWE-9876',
        ano: 2020,
        cor: 'Vermelho',
        valor: 55000.00
    },
    {
        matricula: 'MAT-004',
        modelo: 'Onix',
        marca: 'Chevrolet',
        placa: 'LKJ-3210',
        ano: 2023,
        cor: 'Branco',
        valor: 72000.00
    },
    {
        matricula: 'MAT-005',
        modelo: 'T-Cross',
        marca: 'Volkswagen',
        placa: 'ZXC-4567',
        ano: 2024,
        cor: 'Cinza',
        valor: 99000.00
    },
    {
        matricula: 'MAT-006',
        modelo: 'Kwid',
        marca: 'Renault',
        placa: 'JKL-1122',
        ano: 2022,
        cor: 'Azul',
        valor: 46000.00
    },
    {
        matricula: 'MAT-007',
        modelo: 'HB20',
        marca: 'Hyundai',
        placa: 'FGH-3344',
        ano: 2023,
        cor: 'Prata',
        valor: 70000.00
    },
    {
        matricula: 'MAT-008',
        modelo: '208',
        marca: 'Peugeot',
        placa: 'POI-5566',
        ano: 2021,
        cor: 'Preto',
        valor: 63000.00
    },
    {
        matricula: 'MAT-009',
        modelo: 'Versa',
        marca: 'Nissan',
        placa: 'UIO-7788',
        ano: 2020,
        cor: 'Branco',
        valor: 58000.00
    },
    {
        matricula: 'MAT-010',
        modelo: 'Renegade',
        marca: 'Jeep',
        placa: 'MNB-9900',
        ano: 2022,
        cor: 'Verde',
        valor: 88000.00
    }
];

async function popularVeiculos() {
    try {
        const res = await axios.get(API_URL);
        if (res.data.length > 0) {
            console.log('⚠️ Veículos já existentes. Seed ignorada.');
            return;
        }

        for (const veiculo of veiculos) {
            const response = await axios.post(API_URL, veiculo);
            console.log(`Inserido: ${response.data.modelo} (${response.data.placa})`);
        }
    } catch (error) {
        if (error.response) {
            console.error('Erro ao popular veículos:', error.response.status, error.response.data);
        } else if (error.request) {
            console.error('Erro ao popular veículos: Sem resposta da API.');
        } else {
            console.error('Erro ao popular veículos:', error.message);
        }
    }
}

module.exports = popularVeiculos;
