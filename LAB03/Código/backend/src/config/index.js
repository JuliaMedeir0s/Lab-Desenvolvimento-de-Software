const { PrismaClient } = require('@prisma/client');
const dotenv = require('dotenv');
dotenv.config();
const prisma = new PrismaClient();
module.exports = { prisma, jwtSecret: process.env.JWT_SECRET };