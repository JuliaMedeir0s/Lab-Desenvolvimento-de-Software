# Student Merit System Backend

Este projeto implementa um sistema de mérito estudantil usando moeda virtual, onde **professores** distribuem moedas a **alunos**, que podem trocá-las por **vantagens** oferecidas por **parceiros**.

---

## 📋 Pré-requisitos

* Node.js ≥ 16
* PostgreSQL em execução
* Conta de e-mail SMTP válida (Gmail, Outlook, etc.)

---

## 🚀 Instalação e Execução

```bash
# 1. Clone o repositório
git clone <url>
cd backend

# 2. Variáveis de ambiente
cp .env.example .env
# Edite .env com suas credenciais:
# DATABASE_URL, JWT_SECRET, SMTP_HOST, SMTP_PORT, SMTP_USER, SMTP_PASS

# 3. Instale dependências
pm install

# 4. Gere o Prisma Client e migre o banco
npx prisma generate
npx prisma migrate dev --name init

# 5. Popule dados iniciais
npm run seed

# 6. Inicie em modo dev
npm run dev

# 7. Visualize o Banco de Dados
npx prisma studio 

```

A API ficará disponível em `http://localhost:3000`.

---

## 🗂️ Estrutura de Pastas

```text
backend/
├── .env.example
├── package.json
├── prisma/
│   ├── schema.prisma      # modelos e relações
│   └── seed.js            # popula 5 registros de cada
└── src/
    ├── index.js           # ponto de entrada Express
    ├── config/
    │   └── index.js       # Prisma Client + dotenv
    ├── middlewares/
    │   └── authMiddleware.js
    ├── utils/
    │   ├── codeGenerator.js  # gera códigos ENV-xxxx e RES-xxxx
    │   └── email.js          # envia e-mails via SMTP
    ├── controllers/        # lógica HTTP
    ├── services/           # regras de negócio (transação atômica)
    └── routes/             # definição de rotas REST
```

---

## 🔐 Autenticação (JWT)

* Todas as rotas de `/alunos`, `/professores`, `/parceiros`, `/vantagens/me`, `/transacoes` exigem cabeçalho:

```
Authorization: Bearer <TOKEN>
```

* O token é obtido em **POST /auth/login** ou **/register**, com validade de 1 hora.

---

## 📡 Rotas da API

### 1. Autenticação

| Método | Rota             | Descrição                    |
| ------ | ---------------- | ---------------------------- |
| POST   | `/auth/register` | Cria usuário e retorna token |
| POST   | `/auth/login`    | Autentica e retorna token    |

**Exemplo**: Registrar aluno

```bash
curl -X POST http://localhost:3000/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "nome":"Aluno 1",
    "email":"aluno1@ex.com",
    "senha":"senha123",
    "tipoUsuario":"ALUNO",
    "cpf":"12345678900",
    "rg":"MG1234567",
    "instituicaoId":1,
    "enderecoId":1
  }'
```

---

### 2. Alunos

| Método | Rota                              | Descrição                            |
| ------ | --------------------------------- | ------------------------------------ |
| GET    | `/alunos/me`                      | Dados do aluno logado + Usuário      |
| PUT    | `/alunos/me`                      | Atualiza perfil do aluno             |
| GET    | `/alunos/me/extrato`              | Saldo e todas as transações do aluno |
| POST   | `/alunos/me/resgatar/:vantagemId` | Resgata uma vantagem (valida saldo)  |

**Exemplo**: Extrato do aluno

```bash
curl http://localhost:3000/alunos/me/extrato \
  -H "Authorization: Bearer $TOKEN"
```

---

### 3. Professores

| Método | Rota                              | Descrição                              |
| ------ | --------------------------------- | -------------------------------------- |
| GET    | `/professores/me`                 | Dados do professor + Usuário           |
| PUT    | `/professores/me`                 | Atualiza perfil do professor           |
| GET    | `/professores/me/transacoes`      | Saldo e envios de moedas               |
| POST   | `/professores/me/enviar/:alunoId` | Envia moedas a um aluno (valida saldo) |

**Exemplo**: Enviar moedas

```bash
curl -X POST http://localhost:3000/professores/me/enviar/2 \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{ "valor":100, "mensagem":"Bom desempenho" }'
```

---

### 4. Parceiros

| Método | Rota            | Descrição                   |
| ------ | --------------- | --------------------------- |
| GET    | `/parceiros/me` | Dados do parceiro + Usuário |
| PUT    | `/parceiros/me` | Atualiza perfil do parceiro |

---

### 5. Vantagens

| Método | Rota             | Descrição                     |
| ------ | ---------------- | ----------------------------- |
| GET    | `/vantagens`     | Lista todas as vantagens      |
| GET    | `/vantagens/me`  | Vantagens do parceiro logado  |
| POST   | `/vantagens/me`  | Cria nova vantagem (parceiro) |
| PUT    | `/vantagens/:id` | Atualiza vantagem (parceiro)  |

**Exemplo**: Criar vantagem

```bash
curl -X POST http://localhost:3000/vantagens/me \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "nome":"Desconto 10%",
    "descricao":"Menos 10% na cantina",
    "imagem":"http://img.ex/1.jpg",
    "custo":50
  }'
```

---

### 6. Transações (Admin)

| Método | Rota          | Descrição                 |
| ------ | ------------- | ------------------------- |
| GET    | `/transacoes` | Lista todas as transações |

---

## 🤖 Lógica de Negócio

1. **Envio de moedas** (`Professor` → `Aluno`):

   * Valida saldo do professor.
   * Atualiza saldos (`professor.saldo -= valor`, `aluno.saldo += valor`).
   * Gera código `ENV-XXXXXX` usando `codeGenerator`.
   * Persiste `Transacao` dentro de `prisma.$transaction`.
   * Envia e-mail ao aluno com código e mensagem.

2. **Resgate de vantagem** (`Aluno` → `Vantagem`):

   * Valida saldo do aluno (não negativo).
   * Atualiza `aluno.saldo -= vantagem.custo`.
   * Gera código `RES-XXXXXX`.
   * Persiste `Transacao` atômica.
   * Envia cupom por e-mail ao aluno e notificação ao parceiro.

3. **Saldo e extrato**:

   * Sempre consistente após cada transação.
   * Rotas `/extrato` e `/transacoes` retornam histórico completo.

4. **Segurança**:

   * Todas rotas protegidas usam `authMiddleware` (JWT).
   * Erros retornam JSON `{ error: mensagem }`.
   * Sucessos retornam `{ message: texto }` ou dados solicitados.


---

