# 📌 Histórias de Usuário e Critérios de Aceitação

Este documento detalha todas as histórias de usuário do sistema, incluindo seus critérios de aceitação.

---

## 📌 Cliente

### **1️⃣ Realizar Cadastro**
✅ **História:**  
_Como cliente, eu quero me cadastrar no sistema, para que eu possa realizar pedidos de aluguel de automóveis._

✅ **Critérios de Aceitação:**
- O cliente deve fornecer **nome completo, CPF, RG, endereço, e-mail, telefone e senha**.
- O sistema deve **validar se o CPF já está cadastrado**.
- O sistema deve **enviar um e-mail de confirmação** para ativar a conta.

---

### **2️⃣ Fazer Login**
✅ **História:**  
_Como cliente, eu quero fazer login no sistema, para que eu possa acessar minha conta e gerenciar meus pedidos de aluguel._

✅ **Critérios de Aceitação:**
- O cliente deve inserir **e-mail e senha cadastrados**.
- O sistema deve **validar as credenciais** e permitir o acesso apenas se forem válidas.

---

### **3️⃣ Recuperação de Senha**
✅ **História:**  
_Como cliente, eu quero recuperar minha senha, para que eu possa acessar minha conta caso eu a esqueça._

✅ **Critérios de Aceitação:**
- O cliente deve fornecer **o e-mail cadastrado**.
- O sistema deve enviar um **link de redefinição de senha** para o e-mail informado, se ele existir no sistema.

---

### **4️⃣ Fazer Pedido de Aluguel**
✅ **História:**  
_Como cliente, eu quero alugar um automóvel, para que eu possa utilizá-lo por um período determinado._

✅ **Critérios de Aceitação:**
- O cliente deve escolher um **veículo disponível** e definir o **período do aluguel**.
- O pedido só pode ser enviado **se o cliente estiver logado**.

---

### **5️⃣ Consultar Pedido**
✅ **História:**  
_Como cliente, eu quero visualizar o status do meu pedido, para que eu possa acompanhar o andamento do processo._

✅ **Critérios de Aceitação:**
- O sistema deve exibir se o pedido está em análise, aprovado, rejeitado ou cancelado.
  
---

### **6️⃣ Modificar Pedido**
✅ **História:**  
_Como cliente, eu quero modificar meu pedido, para que eu possa corrigir ou alterar detalhes antes da aprovação._

✅ **Critérios de Aceitação:**
- O cliente só pode modificar pedidos que ainda não foram aprovados.
  
---

### **7️⃣ Cancelar Pedido**
✅ **História:**  
_Como cliente, eu quero cancelar um pedido de aluguel, para que eu possa desistir do serviço caso necessário._

✅ **Critérios de Aceitação:**
- O cliente só pode cancelar pedidos antes da aprovação final.
  
---

## 📌 Agente (Banco/Empresa)

### **1️⃣ Fazer Login**
✅ **História:**  
_Como agente, eu quero fazer login no sistema, para que eu possa avaliar e aprovar pedidos de aluguel._

✅ **Critérios de Aceitação:**
- O agente deve inserir **e-mail e senha previamente cadastrados**.
- O sistema deve validar as credenciais e conceder acesso **somente se forem corretas**.

---

### **2️⃣ Avaliar Pedido**
✅ **História:**  
_Como agente, eu quero analisar pedidos de aluguel, para que eu possa decidir se o cliente está apto para alugar um veículo._

✅ **Critérios de Aceitação:**
- O agente pode visualizar **dados do cliente, do veículo e do contrato**.

---

### **3️⃣ Aprovar/Rejeitar Pedido**
✅ **História:**  
_Como agente, eu quero aprovar ou rejeitar um pedido de aluguel, para que apenas clientes qualificados consigam alugar um veículo._

✅ **Critérios de Aceitação:**
- O agente só pode aprovar ou rejeitar pedidos **após a avaliação**.
- O sistema deve **registrar a decisão tomada**.

---

### **4️⃣ Modificar Pedido de Aluguel**
✅ **História:**  
_Como agente, eu quero modificar um pedido de aluguel, para que eu possa ajustar valores ou prazos antes da aprovação._

✅ **Critérios de Aceitação:**
- O agente pode alterar informações **antes de aprovar ou rejeitar o pedido**.
- O sistema deve **registrar as modificações realizadas**.

---

📌 **Essas histórias de usuário servem como base para o desenvolvimento e validação do sistema.**
