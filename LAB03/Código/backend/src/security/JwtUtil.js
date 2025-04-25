const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');

function generateToken(user) {
    return jwt.sign({ id: user.id }, process.env.JWT_SECRET, {
        expiresIn: process.env.JWT_EXPIRES_IN,
    });
}

function verifyToken(token) {
    try {
        return jwt.verify(token, process.env.JWT_SECRET);
    } catch (error) {
        return null;
    }
}

function hashPassword(password) {
    return bcrypt.hashSync(password, 8);
}

function comparePassword(password, hash) {
    return bcrypt.compareSync(password, hash);
}

module.exports = {
    generateToken,
    verifyToken,
    hashPassword,
    comparePassword,
};
