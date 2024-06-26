# Catálogo de Produtos

![GitHub repo size](https://img.shields.io/github/repo-size/Cartaloop/catalog-authentication?style=for-the-badge)
![GitHub language count](https://img.shields.io/github/languages/count/Cartaloop/catalog-authentication?style=for-the-badge)
![GitHub forks](https://img.shields.io/github/forks/Cartaloop/catalog-authentication?style=for-the-badge)
![Bitbucket open issues](https://img.shields.io/bitbucket/issues/Cartaloop/catalog-authentication?style=for-the-badge)
![Bitbucket open pull requests](https://img.shields.io/bitbucket/pr-raw/Cartaloop/catalog-authentication?style=for-the-badge)

> ## Sobre o projeto
> Esse projeto trata-se de uma web API de um catálogo de produtos com autenticação e autorização de usuários para certos endpoint, onde existem usuários administradores que possuem acessos a requisições de gerenciamento dos produtos, enquanto que os usuários comuns(não autorizados) apenas podem solicitar requisições do tipo GET específicas ao servidor.
> ### Tecnologias
> - Java 21
> - Maven 4.0.0
> - Springboot 3.3.0
> - Spring Security
> - JWT
> - PostgreSQL
> - Flyway
> - Swagger
> - Docker
> - Lombok

### Ajustes e melhorias

O projeto ainda está em desenvolvimento e as próximas atualizações serão voltadas nas seguintes tarefas:

- [x] Criação da classe de domínio Product
- [x] Implementação do repositório de Product
- [x] Criação da classe Service
- [x] Criação e implementação do controller de Product
- [x] Implementação de DTO
- [x] Implementação do Swagger
- [X] Tratamento de erros da camada de Product
- [X] Implementação da camada de usuário
- [X] Implementação da autenticação com JWT
- [ ] Testes Unitários com JUnit e Mockito
- [ ] Dockerizando a aplicação
- [ ] Front-end da aplicação

### A fazer no próximo dia
- Testes unitários com JUnit




## Utilizando esse projeto
### 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:

- Você instalou `Java 21 / Maven 4.0.0`
- Você tem uma máquina `Linux`.

### Clone do repositório
Acesse o diretório onde deseja salvar o projeto e execute o comando:
```bash
git clone https://github.com/lucas-rech/catalog-authentication.git
```

### Dependências do Maven
Após clonado o projeto, acesse-o e carregue as dependências do Maven no arquivo`pom.xml`. 


## Documentação Swagger
Para acessar a documentação do projeto no Swagger, acesse através da URL:
`
localhost:8080/swagger-ui/index.html
`


