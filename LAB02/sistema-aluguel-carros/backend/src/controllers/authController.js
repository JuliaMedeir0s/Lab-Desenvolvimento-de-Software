const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');
const Usuario = require('../models/usuario');

exports.login = async (req, res, next) => {
    const { email, senha } = req.body;

    try {
        const usuario = await Usuario.findOne({ where: { email } });

        if (!usuario) {
            return res.status(401).json({ error: 'Usuário inválido' });
        }

        const senhaValida = await bcrypt.compare(senha, usuario.senha);

        if (!senhaValida) {
            return res.status(401).json({ error: 'Senha inválida' });
        }

        const token = jwt.sign({ id: usuario.id }, process.env.JWT_SECRET, {
            expiresIn: process.env.JWT_EXPIRES_IN,
        });

        res.json({ token });
    }
    catch (error) {
        res.status(500).json({ error: 'Erro ao fazer login' });
    }
};
