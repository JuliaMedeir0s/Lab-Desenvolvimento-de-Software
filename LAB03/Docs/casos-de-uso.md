# 📄 Casos de Uso

Este documento descreve os principais casos de uso do Sistema de Moeda Estudantil.

---

## 📌 Atores do Sistema

- 👤 **Aluno**
- 👨‍🏫 **Professor**
- 🏢 **Empresa Parceira**

---

## 📌 Casos de Uso por Ator

### 👤 Aluno

| Código | Caso de Uso                          | Descrição                                                                |
|--------|--------------------------------------|--------------------------------------------------------------------------|
| UC01   | Realizar cadastro                    | Aluno informa seus dados pessoais e acadêmicos para se registrar.        |
| UC02   | Fazer login                          | Aluno acessa o sistema com e-mail e senha.                               |
| UC03   | Visualizar extrato                   | Aluno consulta as moedas recebidas e trocadas.                           |
| UC04   | Resgatar vantagem                    | Aluno escolhe uma vantagem e troca moedas por ela.                       |
| UC05   | Receber e-mail de resgate            | Aluno recebe um e-mail com o código da vantagem resgatada.               |

---

### 👨‍🏫 Professor

| Código | Caso de Uso                   | Descrição                                                                 |
|--------|-------------------------------|---------------------------------------------------------------------------|
| UC06   | Fazer login                   | Professor acessa o sistema com e-mail e senha.                            |
| UC07   | Enviar moedas                 | Professor envia moedas para um aluno com uma justificativa obrigatória.   |
| UC08   | Visualizar extrato            | Professor consulta histórico de moedas enviadas e saldo disponível.       |

---

### 🏢 Empresa Parceira

| Código | Caso de Uso                  | Descrição                                                                 |
|--------|------------------------------|---------------------------------------------------------------------------|
| UC09   | Realizar cadastro            | Empresa se registra no sistema para oferecer vantagens.                   |
| UC10   | Fazer login                  | Empresa acessa o sistema com e-mail e senha.                              |
| UC11   | Cadastrar vantagem           | Empresa registra uma vantagem com nome, descrição, imagem e custo.        |
| UC12   | Receber e-mail de resgate    | Empresa recebe um e-mail com os dados do aluno e código da vantagem.      |

---

### 🔐 Sistema (ações automáticas)

| Código | Caso de Uso                        | Descrição                                                                   |
|--------|------------------------------------|-----------------------------------------------------------------------------|
| UC13   | Enviar e-mail de recebimento       | Sistema envia e-mail ao aluno ao receber moedas de um professor.            |
| UC14   | Enviar e-mail com código de resgate| Sistema envia e-mail ao aluno e à empresa com o código de confirmação.      |

*_Os casos UC13 e UC14 são executados automaticamente pelo sistema em eventos específicos, como o envio de moedas e o resgate de vantagens._*

---
