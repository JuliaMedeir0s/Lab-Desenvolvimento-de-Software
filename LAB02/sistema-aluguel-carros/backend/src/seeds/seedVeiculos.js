const Veiculo = require('../models/veiculo'); // Importa o modelo Sequelize de Veículo

const veiculos = [
    {
        matricula: 'MAT-001',
        modelo: 'Corolla',
        marca: 'Toyota',
        placa: 'ABC-1234',
        ano: 2022,
        cor: 'Prata',
        valor: 90000.0
    },
    {
        matricula: 'MAT-002',
        modelo: 'Civic',
        marca: 'Honda',
        placa: 'XYZ-5678',
        ano: 2021,
        cor: 'Preto',
        valor: 95000.0
    },
    {
        matricula: 'MAT-003',
        modelo: 'Argo',
        marca: 'Fiat',
        placa: 'QWE-9876',
        ano: 2020,
        cor: 'Vermelho',
        valor: 55000.0
    },
    {
        matricula: 'MAT-004',
        modelo: 'Onix',
        marca: 'Chevrolet',
        placa: 'LKJ-3210',
        ano: 2023,
        cor: 'Branco',
        valor: 72000.0
    },
    {
        matricula: 'MAT-005',
        modelo: 'T-Cross',
        marca: 'Volkswagen',
        placa: 'ZXC-4567',
        ano: 2024,
        cor: 'Cinza',
        valor: 99000.0
    },
    {
        matricula: 'MAT-006',
        modelo: 'Kwid',
        marca: 'Renault',
        placa: 'JKL-1122',
        ano: 2022,
        cor: 'Azul',
        valor: 46000.0
    },
    {
        matricula: 'MAT-007',
        modelo: 'HB20',
        marca: 'Hyundai',
        placa: 'FGH-3344',
        ano: 2023,
        cor: 'Prata',
        valor: 70000.0
    },
    {
        matricula: 'MAT-008',
        modelo: '208',
        marca: 'Peugeot',
        placa: 'POI-5566',
        ano: 2021,
        cor: 'Preto',
        valor: 63000.0
    },
    {
        matricula: 'MAT-009',
        modelo: 'Versa',
        marca: 'Nissan',
        placa: 'UIO-7788',
        ano: 2020,
        cor: 'Branco',
        valor: 58000.0
    },
    {
        matricula: 'MAT-010',
        modelo: 'Renegade',
        marca: 'Jeep',
        placa: 'MNB-9900',
        ano: 2022,
        cor: 'Verde',
        valor: 88000.0
    }
];

async function popularVeiculos() {
    try {
        // Verifica se já existem veículos no banco
        const veiculosExistentes = await Veiculo.findAll();
        if (veiculosExistentes.length > 0) {
            console.log('Veículos já existentes. Seed ignorada.');
            return;
        }

        // Insere os veículos diretamente no banco
        await Veiculo.bulkCreate(veiculos);
        console.log('Veículos inseridos com sucesso!');
    } catch (error) {
        console.error('Erro ao popular veículos:', error.message);
    }
}

module.exports = popularVeiculos;