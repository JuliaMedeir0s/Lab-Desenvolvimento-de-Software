const nodemailer = require('nodemailer');
const dotenv = require('dotenv');
dotenv.config();
const transporter = nodemailer.createTransport({
  host: process.env.SMTP_HOST,
  port: Number(process.env.SMTP_PORT),
  auth: {
    user: process.env.SMTP_USER,
    pass: process.env.SMTP_PASS,
  },
});

transporter.verify(err => {
    if (err) console.error('❌ SMTP inválido:', err);
    else    console.log('✅ SMTP conectado com sucesso!');
  });

async function sendEmail(to, subject, text, html) {
  const mailOptions = {
    from: `"Sistema de Mérito" <${process.env.SMTP_USER}>`,
    to,
    subject,
    text,     
    html       
  };

  return transporter.sendMail(mailOptions);
}
module.exports = { sendEmail };