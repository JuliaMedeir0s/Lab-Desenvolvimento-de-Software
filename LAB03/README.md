# 💰 Sistema de Moeda Estudantil
![Projeto Status](https://img.shields.io/badge/status-em%desenvolvimento-yellow) 

Sistema para incentivar o reconhecimento do mérito estudantil por meio de uma moeda virtual. Essa moeda pode ser distribuída por professores e trocada pelos alunos por produtos e serviços em empresas parceiras.

---

## 📌 Visão Geral

O sistema possui três tipos de usuários: alunos, professores e empresas parceiras. Cada grupo possui funcionalidades específicas, com autenticação via JWT e integração com banco de dados PostgreSQL.

---

## 🛠️ Tecnologias Utilizadas

- **Arquitetura**: MVC  
- **Backend**: Node.js + Express  
- **Banco de Dados**: PostgreSQL  
- **ORM**: Sequelize  
- **Frontend**: React + TypeScript  
- **Autenticação**: JWT  
- **Email**: Nodemailer + Gmail SMTP 

---

## 📂 Estrutura do Projeto 
```
sistema-moeda-estudantil/
│
├── backend/
│   ├── src/
│   │   ├── controllers/
│   │   ├── models/
│   │   ├── services/
│   │   ├── routes/
│   │   ├── middlewares/
│   │   └── config/
│   ├── .env.example
│   └── package.json
│
├── frontend/
│   ├── public/
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   └── services/
│   └── package.json
│
├── docs/
│   ├── diagramas/
│   │   ├── casos-de-uso.png
│   │   ├── classes.png
│   │   ├── componentes.png
│   │   ├── modelo-er.png
│   │   └── logico.png
│   └── README.md
│
├── README.md
└── .gitignore
```

---

## 📌 Histórias de Usuário

### **Aluno**
- ✅ Como aluno, quero me cadastrar para participar do sistema e receber moedas.
- ✅ Como aluno, quero ver meu extrato para saber quantas moedas recebi e usei.
- ✅ Como aluno, quero trocar moedas por vantagens como produtos e descontos.
- ✅ Como aluno, quero receber um e-mail com um código ao resgatar uma vantagem.

### **Professor**
- ✅ Como professor, quero fazer login para acessar o sistema.
- ✅ Como professor, quero enviar moedas aos alunos com uma justificativa.
- ✅ Como professor, quero ver meu extrato para acompanhar meu saldo e envios.

### **Empresa Parceira**
- ✅ Como empresa parceira, quero me cadastrar para oferecer vantagens aos alunos.
- ✅ Como empresa parceira, quero cadastrar produtos com nome, imagem, descrição e custo.
- ✅ Como empresa parceira, quero receber e-mail com o código quando uma vantagem for usada.

---

## 📊 Diagrama de Casos de Uso

![Diagrama de Casos de Uso](Docs/Diagramas/casos-uso.png)  
📌 **Para detalhes sobre os casos de uso**:  
🔗 [Descrição do Diagrama de Casos de Uso](Docs/casos-de-uso.md)

---

## 📊 Diagrama de Classes

![Diagrama de Classes](Docs/Diagramas/classes-v2.png)  
📌 **Para detalhes sobre as classes**:  
🔗 [Detalhamento do Diagrama de Classes](Docs/classes.md)

---

## 📊 Diagrama de Componentes

![Diagrama de Componentes](Docs/Diagramas/componentes.png)
---

## 📌 Modelo ER

![Diagrama Entidade Relacionamento](Docs/Diagramas/modelo-er.png)
---
