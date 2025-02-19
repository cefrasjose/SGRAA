# 🐾 SGRAA - Sistema de Gestão para Adoção de Animais Domésticos  

## 📌 Descrição do Projeto  
O **SGRAA** é um sistema desenvolvido para facilitar a gestão de adoção de animais domésticos. Ele permite o cadastro de animais, voluntários, pretendentes à adoção, resgates e doações, garantindo um processo seguro e organizado.  

O sistema utiliza **Spring Boot** para o backend, com **autenticação baseada em sessão e banco de dados** via **Spring Security**.  

---

## ⚙️ Tecnologias Utilizadas  

- **Java 23**  
- **Spring Boot 3.x**  
- **Spring Security (Autenticação baseada em sessão)**  
- **Spring Data JPA (Hibernate)**  
- **MySQL**  
- **Maven**  
- **Lombok**  
- **Postman (para testes de API)**  

---

## 🏗 Configuração do Projeto  

### 🔹 1️⃣ Configurar o Banco de Dados  
1. Instale o MySQL e crie o banco de dados:  
   ```sql
   CREATE DATABASE sgraa;
   ```
2. Atualize o arquivo `application.properties` com suas credenciais:  
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/sgraa?useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=SUA_SENHA_AQUI
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

### 🔹 2️⃣ Compilar e Rodar o Projeto  
1. No terminal, navegue até a pasta do projeto e execute:  
   ```bash
   mvn clean install
   ```
2. Inicie a aplicação:  
   ```bash
   mvn spring-boot:run
   ```
3. O backend estará disponível em:  
   ```
   http://localhost:8080
   ```

---

## 🔐 Autenticação e Segurança  
O sistema utiliza **Spring Security** com **sessão e banco de dados**.  
Os usuários podem ter os seguintes papéis:  
- **ADMIN**: Acesso total ao sistema.  
- **VOLUNTARIO**: Acesso a funcionalidades específicas.  

### 🔹 Endpoints de Autenticação  
1. **Registrar um usuário**  
   ```http
   POST http://localhost:8080/api/auth/register
   ```
   ```json
   {
       "nome": "Admin",
       "email": "admin@email.com",
       "senha": "123456"
   }
   ```
2. **Login (Gerenciado pelo Spring Security)**  
   ```http
   GET http://localhost:8080/api/auth/me
   ```
   - Retorna os dados do usuário logado.

---

## 📌 Principais Endpoints da API  

### 🐶 Animais  
- `POST /api/animais` → Cadastrar um novo animal.  
- `GET /api/animais` → Listar todos os animais.  

### 💁‍♂️ Voluntários  
- `POST /api/voluntarios` → Cadastrar um voluntário.  
- `GET /api/voluntarios` → Listar voluntários.  

### 🏡 Pretendentes à Adoção  
- `POST /api/pretendentes` → Cadastrar um pretendente.  
- `GET /api/pretendentes` → Listar pretendentes.  

### 📜 Adoções  
- `POST /api/adocoes` → Registrar uma adoção.  
- `GET /api/adocoes` → Listar adoções.  

### 🎁 Doações  
- `POST /api/doacoes` → Registrar uma doação.  
- `GET /api/doacoes` → Listar doações.  

### 🚑 Resgates  
- `POST /api/resgates` → Registrar um resgate.  
- `GET /api/resgates` → Listar resgates.  

---

## 🛠 Próximos Passos  
- ✅ Implementar melhorias nas **regras de negócio**.  
- ✅ Criar **tratamento de erros e logs**.  
- 🔜 Desenvolver **testes automatizados**.  

---

## 💡 Contribuição  
Se deseja contribuir com o projeto, siga estes passos:  
1. Faça um **fork** do repositório.  
2. Crie uma **branch** com a nova funcionalidade:  
   ```bash
   git checkout -b minha-feature
   ```
3. Faça o **commit** das alterações:  
   ```bash
   git commit -m "Minha nova feature"
   ```
4. Envie para o repositório remoto:  
   ```bash
   git push origin minha-feature
   ```
5. Crie um **Pull Request**.  
