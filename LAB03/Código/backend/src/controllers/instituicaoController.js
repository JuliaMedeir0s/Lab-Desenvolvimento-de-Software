const { prisma } = require('../config');

async function getAllInstituicoes(req, res) {
    try {
        const instituicoes = await prisma.instituicao.findMany();
        return res.json(instituicoes);
    } catch (error) {
        return res.status(500).json({ error: error.message });
    }
}

module.exports = { getAllInstituicoes };