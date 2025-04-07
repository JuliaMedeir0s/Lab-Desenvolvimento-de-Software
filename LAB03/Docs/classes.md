# 📄Detalhamento do Diagrama de Classes

Este documento descreve, de forma textual, as classes principais do sistema de moeda estudantil, seus atributos relevantes e seus relacionamentos, com o objetivo de complementar o entendimento do diagrama de classes.

---

## 📌 Usuario (abstract)

Representa a estrutura comum a todos os usuários do sistema (alunos, professores e empresas parceiras).

- Atributos principais: nome, email, senha, tipo de usuário.
- Não pode ser instanciada diretamente.
- Serve como superclasse para `Aluno`, `Professor` e `Parceiro`.

---

## 📌 Aluno

Representa os estudantes que participam do sistema, recebendo moedas de professores e trocando por vantagens.

- Atributos principais: CPF, RG, endereço, instituição.
- Relacionamentos:
  - Pertence a uma `Instituicao`.
  - Recebe moedas por meio de `Transacoes` enviadas por professores.
  - Realiza resgates de Vantagens através de `Transacoes`.

 ---

## 📌 Professor

 Representa os docentes da instituição que distribuem moedas aos alunos como forma de reconhecimento.

 - Atributos principais: CPF, departamento, instituição.
 - Relacionamentos:
   - Pertence a uma `Instituicao`.
   - Envia `Transacoes` (do tipo "envio") para `Alunos`.

---

## 📌 Parceiro

Representa empresas cadastradas no sistema que oferecem vantagens (produtos ou serviços) aos alunos.

- Atributos principais: CNPJ.
- Relacionamentos:
  - Oferece várias `Vantagens`.
  - Recebe notificações de resgates realizados por `Alunos`.

---

## 📌 Vantagem

Representa um benefício que o aluno pode resgatar utilizando suas moedas acumuladas.

- Atributos principais: nome, descrição, imagem, custo.
- Relacionamentos:
  - Pertence a uma `EmpresaParceira`.
  - Pode ser resgatada por vários `Alunos`, por meio de `Transacoes`.

---

## 📌 Transacao

Registra todas as movimentações de moedas no sistema, sejam elas envios (professor → aluno) ou resgates (aluno → vantagem).

- Atributos principais: tipo (envio ou resgate), valor, mensagem, código, data.
- Relacionamentos:
- Está sempre associada a um `Aluno`.
- Pode estar associada a um `Professor` (no caso de envio).
- Pode estar associada a uma `Vantagem` (no caso de resgate).

---

## 📌 Instituicao

Representa a instituição de ensino à qual alunos e professores estão vinculados.

- Atributos principais: nome.
- Relacionamentos:
  - Possui vários `Alunos`.
  - Possui vários `Professores`.

---
