# 📌 Diagrama de Casos de Uso - Sistema de Aluguel de Carros

Este documento contém a descrição detalhada do **Diagrama de Casos de Uso** do sistema.

---

## 📌 Atores do Sistema
O sistema possui dois tipos principais de usuários:

1. **Cliente**  
   - Usuário comum que deseja alugar um carro.  
   - Pode criar, modificar e cancelar pedidos.  
   - Consulta o status dos seus pedidos.  

2. **Agente (Banco/Empresa)**  
   - Responsável por avaliar e modificar os pedidos.  
   - Aprova ou rejeita pedidos do ponto de vista financeiro.  

---

## 📌 Casos de Uso do Cliente

### **1️⃣ Realizar cadastro (`<<include>>` Fornecer dados)**
> O Cliente precisa se cadastrar antes de realizar qualquer outra ação no sistema.  
> O cadastro exige que o cliente forneça informações obrigatórias.

✅ **Dados Necessários:**
- Nome completo
- CPF
- RG
- Endereço (rua, número, bairro, cidade, estado, CEP)
- E-mail
- Telefone
- Senha de acesso

✅ **Relacionamentos:**
- `<<include>>` **Fornecer dados** (o cadastro só é concluído se o cliente fornecer todas as informações).

---

### **2️⃣ Fazer Login (`<<include>>` Validar login)**
> O Cliente precisa se autenticar para acessar o sistema e gerenciar seus pedidos.

✅ **Dados Necessários:**
- E-mail
- Senha

✅ **Relacionamentos:**
- `<<include>>` **Validar credenciais** (o sistema precisa confirmar se o e-mail e a senha são válidos).
- `<<extend>>` **Recuperar senha** (se o cliente esquecer a senha, pode solicitar a recuperação).

---

### **3️⃣ Fazer pedido de aluguel**
> O Cliente pode escolher um veículo disponível e solicitar um aluguel.

✅ **Dados Necessários:**
- Data de início e fim do aluguel
- Veículo desejado (marca, modelo, ano, placa)
- Forma de pagamento
- Endereço para retirada do veículo

---

### **4️⃣ Modificar pedido (`<<extend>>` Fazer pedido de aluguel)**
> O Cliente pode alterar um pedido antes da aprovação financeira.

✅ **Possíveis Modificações:**
- Alterar veículo alugado
- Alterar data de início/fim do aluguel
- Modificar forma de pagamento
- Alterar local de retirada

✅ **Relacionamentos:**
- `<<extend>>` **Fazer pedido de aluguel** (o cliente só pode modificar um pedido que já foi criado).

---

### **5️⃣ Cancelar Pedido**
> O Cliente pode cancelar um pedido antes da aprovação final.

---

### **6️⃣ Consultar Pedido**
> O Cliente pode verificar o andamento do seu pedido a qualquer momento.

✅ **Status Possíveis:**
- **Em análise** (aguardando aprovação financeira)
- **Aprovado** (pronto para a assinatura do contrato)
- **Rejeitado** (pedido negado pelo agente financeiro)
- **Cancelado** (pedido cancelado pelo cliente)
- **Finalizado** (contrato concluído e veículo devolvido)

---

## 📌 Casos de Uso dos Agentes

### **1️⃣ Fazer login (`<<include>>` Validar credenciais)**
> O agente precisa se autenticar para acessar o sistema e avaliar pedidos.

✅ **Dados Necessários:**
- E-mail corporativo  
- Senha  

✅ **Relacionamentos:**
- `<<include>>` **Validar credenciais** (o sistema precisa confirmar se o e-mail e a senha são válidos).  
- `<<extend>>` **Recuperar senha** (se o agente esquecer a senha, pode solicitar recuperação).  

---

### **2️⃣ Avaliar pedido de aluguel**
> O agente analisa se o cliente pode ou não alugar o veículo, considerando fatores como histórico de crédito e renda.  
> Depois da avaliação, ele deve aprovar ou rejeitar o pedido.

✅ **Dados Necessários para Avaliação:**
- Informações do Cliente (nome, CPF, renda, profissão)  
- Histórico financeiro do Cliente  
- Dados do veículo solicitado  
- Duração do aluguel  

---

### **3️⃣ Aprovar/Rejeitar pedido (`<<include>>` Avaliar pedido de aluguel)**
> Após a análise financeira, o agente pode **aprovar ou rejeitar um pedido**.

✅ **Critérios para Aprovação/Rejeição:**
- Cliente tem renda compatível?  
- O histórico financeiro do cliente é positivo?  
- Há alguma restrição no pedido?  

✅ **Relacionamentos:**
- `<<include>>` **Avaliar Pedido de Aluguel** (só é possível aprovar/rejeitar um pedido que já foi avaliado).  

---

### **4️⃣ Modificar pedido de aluguel**
> O agente pode alterar um pedido antes da aprovação final, caso seja necessário.

✅ **Possíveis Modificações:**
- Alteração no valor do aluguel  
- Alteração no prazo do contrato  
- Ajustes no veículo escolhido  

---

📌 Para mais detalhes sobre os fluxos do sistema, consulte o [Diagrama de Casos de Uso](./LAB02-Diagrama-Casos-de-Uso.png).
