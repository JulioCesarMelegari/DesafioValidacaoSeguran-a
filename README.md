# Projeto Spring Boot com Segurança, Validação e Autenticação

Este projeto demonstra uma API REST construída com **Spring Boot**, implementando boas práticas de **segurança**, **validação de dados** e **autenticação/autorização**.

---

## 1. Estrutura do Projeto

- **Spring Boot** – Framework principal para desenvolvimento rápido.
- **Spring Security** – Configuração de autenticação e autorização.
- **Bean Validation (Jakarta Validation)** – Validação de dados de entrada.
- **JWT (JSON Web Token)** – Mecanismo de autenticação baseado em tokens.

---

## 2. Segurança

### Regras de Controle de Acesso
- Rotas `GET` de **cidades** e **eventos** são públicas.
- Apenas usuários com perfil **CLIENT** ou **ADMIN** podem criar novos **eventos** (`POST`).
- Apenas usuários **ADMIN** podem acessar as demais operações.


# Desafio Validação e Segurança
Implemente as funcionalidades necessárias para que os testes do projeto abaixo passem (assista o vídeo explicativo e baixe o projeto no conteúdo anexo ao vídeo).

Este é um sistema de eventos e cidades com uma relação N-1 entre eles:
<img width="807" height="300" alt="image" src="https://github.com/user-attachments/assets/56161e16-de47-4111-b7b8-2c8ff5b1b37b" />

# Regras de Controle de Acesso
- Somente rotas de leitura (`GET`) de **eventos** e **cidades** são públicas (não requerem login).
- Usuários com perfil **CLIENT** e/ou **ADMIN** podem **inserir novos eventos** (`POST`).
- Os demais acessos são permitidos apenas a usuários **ADMIN**.

# Regras de Validação

## City
- **Nome** não pode ser vazio.

## Event
- **Nome** não pode ser vazio.  
- **Data** não pode ser passada.  
- **Cidade** não pode ser nula.

# Critérios da API

### Eventos (`/events`)
- **POST /events**
  - 401 Unauthorized → usuário não logado  
  - 201 Created → CLIENT logado e dados corretos  
  - 201 Created → ADMIN logado e dados corretos  
  - 422 Unprocessable Entity → ADMIN logado e nome em branco  
  - 422 Unprocessable Entity → ADMIN logado e data no passado  
  - 422 Unprocessable Entity → ADMIN logado e cidade nula  

- **GET /events**
  - 200 Ok → retorna página de recursos  

### Cidades (`/cities`)
- **POST /cities**
  - 401 Unauthorized → usuário não logado  
  - 403 Forbidden → CLIENT logado  
  - 201 Created → ADMIN logado e dados corretos  
  - 422 Unprocessable Entity → ADMIN logado e nome em branco  

- **GET /cities**
  - 200 Ok → retorna todos recursos ordenados por nome  

---

# Competências Desenvolvidas
- Desenvolvimento TDD de API Rest com Java e Spring Boot  
- Implementação de segurança com Spring Security e OAuth2  
- Controle de acesso por rotas e perfis de usuário  
- Validação de dados com Bean Validation  


