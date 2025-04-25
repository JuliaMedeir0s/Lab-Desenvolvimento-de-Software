const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');
const Usuario = require('../models/user');

const { generateToken, hashPassword, comparePassword } = require('../security/JwtUtil');
const { JWT_SECRET, JWT_EXPIRES_IN } = process.env;
const { Op } = require('sequelize');

exports.login = async (req, res, next) => {
    const { email, password } = req.body;
    try {
        const user = await Usuario.findOne({
            where: {
                [Op.or]: [
                    { email },
                    { username: email },
                ],
            },
        });
        if (!user) {
            return res.status(401).json({ message: 'Invalid credentials' });
        }
        const isPasswordValid = comparePassword(password, user.password);
        if (!isPasswordValid) {
            return res.status(401).json({ message: 'Invalid credentials' });
        }
        const token = generateToken(user);
        return res.status(200).json({ token });
    } catch (error) {
        next(error);
    }
}

exports.register = async (req, res, next) => {
    const { username, email, password } = req.body;
    try {
        const hashedPassword = hashPassword(password);
        const newUser = await Usuario.create({
            username,
            email,
            password: hashedPassword,
        });
        const token = generateToken(newUser);
        return res.status(201).json({ token });
    } catch (error) {
        next(error);
    }
}

