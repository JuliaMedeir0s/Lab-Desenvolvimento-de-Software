# 🚗 Sistema de Aluguel de Carros  

![Projeto Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)  

## 📖 Descrição  

O **Sistema de Aluguel de Carros** é um projeto acadêmico desenvolvido para a disciplina de **Laboratório de Desenvolvimento de Software** no curso de **Engenharia de Software**.  

O sistema permite que **clientes** realizem cadastros e gerenciem pedidos de aluguel de automóveis. Além disso, **agentes (empresas e bancos)** podem avaliar e modificar os pedidos. O sistema também integra um fluxo de aprovação financeira antes da confirmação do contrato de aluguel.  

Este projeto utiliza a **arquitetura MVC** e é desenvolvido em **Java com Spring Boot**, seguindo um processo iterativo de desenvolvimento, incluindo modelagem UML e implementação em sprints.  

---

## 🛠️ Tecnologias Utilizadas  

O projeto utiliza as seguintes tecnologias e ferramentas:  

- **Linguagem**: Java  
- **Framework Backend**: Spring Boot  
- **Padrão Arquitetural**: MVC  
- **Banco de Dados**:   
- **Ferramentas de Versionamento**: Git & GitHub  
- **Documentação**: Markdown & UML  

---

## 🎯 Arquitetura  

O sistema segue a **arquitetura MVC (Model-View-Controller)**:  

- **Model (M)**: Representa os dados e regras de negócio.  
- **View (V)**: Interface com o usuário para entrada e visualização de dados.  
- **Controller (C)**: Camada intermediária que recebe requisições, processa e envia dados para a **View** ou interage com o **Model**.  

A comunicação entre os componentes ocorre através de **REST APIs** e a persistência é feita em um banco de dados .  

---

## 📂 Estrutura do Projeto  
```
📦 sistema-aluguel-carros  
 ├ 📂 docs             
 │ ├     
 │ ├     
 ├ 📂 backend             
 │ ├ 📂 src/main/java/com/aluguelcarros  
 │ │ ├ 📂 controllers    
 │ │ ├ 📂 services       
 │ │ ├ 📂 models        
 │ │ └ 📂 repositories   
 │ ├ 📝 pom.xml           
 │ └ 📝 application.properties 
 ├ 📂 frontend           
 ├ 📂 test               
 ├ 📝 README.md         
 ├ 📝 .gitignore             
```
---

## 📌 Histórias de Usuário  

As seguintes histórias de usuário definem o escopo do sistema:  

  

---

## 🎨 Diagrama de Casos de Uso  

![Diagrama de Casos de Uso](docs/diagramas/casos_de_uso.png)  

---

## 📌 Diagrama de Classes  

![Diagrama de Classes](docs/diagramas/diagrama_classes.png)  

---

## 📌 Diagrama de Pacotes  

![Diagrama de Pacotes](docs/diagramas/diagrama_pacotes.png)  

---

## 🏆 Equipe  

- **Gustavo Delfino** 
- **Júlia Medeiros** 
- **Rafael Caetano** 

---

## 🚀 Como Executar o Projeto  

### 📌 **Pré-requisitos**  
Antes de rodar o projeto, certifique-se de ter instalado:   

### 📌 **Passos para execução**  
