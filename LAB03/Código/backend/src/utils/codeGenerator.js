const crypto = require('crypto');
function generateCodigo(tipo) {
  const prefix = tipo === 'ENVIO' ? 'ENV' : 'RES';
  const unique = crypto.randomBytes(4).toString('hex').toUpperCase();
  return `${prefix}-${unique}`;
}
module.exports = { generateCodigo };