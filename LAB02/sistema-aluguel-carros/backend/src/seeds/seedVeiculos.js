const axios = require('axios');

const API_URL = 'http://localhost:3000/veiculos';

const veiculos = [
    { marca: 'Toyota', modelo: 'Corolla', placa: 'ABC-1234', ano: 2022 },
    { marca: 'Honda', modelo: 'Civic', placa: 'XYZ-5678', ano: 2021 },
    { marca: 'Fiat', modelo: 'Argo', placa: 'QWE-9876', ano: 2020 },
    { marca: 'Chevrolet', modelo: 'Onix', placa: 'LKJ-3210', ano: 2023 },
    { marca: 'Volkswagen', modelo: 'T-Cross', placa: 'ZXC-4567', ano: 2024 },
    { marca: 'Renault', modelo: 'Kwid', placa: 'JKL-1122', ano: 2022 },
    { marca: 'Hyundai', modelo: 'HB20', placa: 'FGH-3344', ano: 2023 },
    { marca: 'Peugeot', modelo: '208', placa: 'POI-5566', ano: 2021 },
    { marca: 'Nissan', modelo: 'Versa', placa: 'UIO-7788', ano: 2020 },
    { marca: 'Jeep', modelo: 'Renegade', placa: 'MNB-9900', ano: 2022 }
];

async function popularVeiculos() {
    for (const veiculo of veiculos) {
        try {
            const response = await axios.post(API_URL, veiculo);
            console.log(`✅ Inserido: ${response.data.modelo} (${response.data.placa})`);
        } catch (error) {
            console.error(`❌ Erro ao inserir ${veiculo.placa}:`, error.message);
        }
    }
}

popularVeiculos();
