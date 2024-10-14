Sistema de Leilão Eletrônico
Este projeto é um sistema de leilão eletrônico que permite a gestão de leilões de dispositivos de informática e veículos, com funcionalidades de cadastro de produtos, clientes, lances e consulta de leilões. O sistema foi desenvolvido utilizando Java com o framework Quarkus, JPA com Hibernate, Lombok e banco de dados MySQL.

Requisitos do Projeto
Funcionalidades principais:
Cadastro de produtos: dispositivos de informática (notebooks, monitores, hubs, switches, roteadores) e veículos (carros, motocicletas, caminhões).
Gestão de leilões: criar, atualizar, listar e remover leilões.
Gestão de clientes: cadastro de clientes e controle de permissões.
Gestão de lances: permitir que clientes deem lances em produtos e mantenham histórico de lances.
Associação de produtos a leilões: produtos são vinculados a leilões, e caso não sejam vendidos, podem ser reassociados a outro leilão.
Exportação de leilões: exportar detalhes de leilões em um formato de arquivo .DET contendo informações do leilão, produtos, clientes e histórico de lances.
Estrutura do Projeto
A estrutura de diretórios segue o padrão Quarkus, com as classes organizadas de forma modular:

bash
Copiar código
sistema-leilao/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/
│   │   │       └── gov/
│   │   │           └── receita/
│   │   │               └── leilaoeletronico/
│   │   │                   ├── controller/     # Controladores REST
│   │   │                   ├── entity/         # Entidades JPA
│   │   │                   ├── repository/     # Repositórios JPA (Camada de acesso ao banco)
│   │   │                   └── service/        # Lógica de negócio
│   │   └── resources/
│   │       ├── application.properties          # Configurações da aplicação
│   │       └── META-INF/                       # Arquivos de configuração ORM (JPA)
├── pom.xml                                     # Arquivo de configuração do Maven
└── README.md                                   # Documentação do projeto
Principais Pacotes:
controller: Controladores REST que expõem os endpoints da API.
model: Classes que representam as entidades do banco de dados, como Leilao, Produto, Cliente, etc.
repository: Interfaces que utilizam JPA para realizar operações de CRUD no banco de dados.
service: Contém a lógica de negócios do sistema, como validações e regras de lances.
Configuração do Projeto
Dependências:
O projeto utiliza o Maven para gerenciar as dependências. As principais dependências estão no arquivo pom.xml:

Quarkus: Framework principal do projeto.
Hibernate ORM com Panache: Utilizado para facilitar a integração com o banco de dados via JPA.
Lombok: Para automatizar a criação de getters, setters e construtores.
MySQL: Banco de dados relacional utilizado.
JUnit 5 e REST Assured: Utilizados para testes automáticos.
Passos para configurar o projeto:
Clone o repositório:

bash
Copiar código
git clone https://github.com/gabmota8/LPII.git
cd sistema-leilao
Configure o MySQL:

Crie um banco de dados MySQL e um usuário com permissões adequadas. Você pode usar o seguinte script SQL como base:
sql
Copiar código
CREATE DATABASE leilao;
CREATE USER 'leilao_user'@'localhost' IDENTIFIED BY 'senha123';
GRANT ALL PRIVILEGES ON leilao.* TO 'leilao_user'@'localhost';
FLUSH PRIVILEGES;
Configuração do application.properties: No arquivo src/main/resources/application.properties, configure as credenciais do banco de dados:

properties
Copiar código
quarkus.datasource.db-kind=mysql
quarkus.datasource.jdbc.url=jdbc:mysql://localhost:3306/leilao
quarkus.datasource.username=root
quarkus.datasource.password=luanam

quarkus.hibernate-orm.database.generation=drop-and-create  # Para desenvolvimento
Iniciar o projeto: Rode o seguinte comando para iniciar o servidor Quarkus no modo de desenvolvimento:

bash
Copiar código
./mvnw compile quarkus:dev
Endpoints Disponíveis
Abaixo estão os principais endpoints REST que o sistema disponibiliza:

Endpoints de Produtos:
GET /produtos: Lista todos os produtos.
GET /produtos/{id}: Retorna um produto específico.
POST /produtos: Cria um novo produto.
PUT /produtos/{id}: Atualiza um produto existente.
DELETE /produtos/{id}: Remove um produto.
Endpoints de Leilões:
GET /leiloes: Lista todos os leilões.
GET /leiloes/{id}: Retorna os detalhes de um leilão.
POST /leiloes: Cria um novo leilão.
PUT /leiloes/{id}: Atualiza um leilão existente.
DELETE /leiloes/{id}: Remove um leilão.
Endpoints de Lances:
POST /lances: Registra um lance em um produto.
GET /lances/produto/{produtoId}: Retorna o histórico de lances de um produto.
Documentação da API:
A documentação completa da API pode ser acessada via Swagger em:
http://localhost:8080/q/swagger-ui

Como realizar os testes
O projeto utiliza JUnit 5 e REST Assured para realizar testes automáticos. Para rodar os testes, use o seguinte comando:

bash
Copiar código
./mvnw test
Testes Unitários: Validações da lógica de negócio no nível dos serviços.
Testes de Integração: Verificam o funcionamento correto dos endpoints REST.
Detalhes do Banco de Dados (MySQL)
O banco de dados utilizado é o MySQL. Abaixo estão as principais tabelas e suas descrições:

Tabelas Principais:
usr_usuario: Armazena informações dos clientes do sistema (nome, email, senha).
aut_autorizacao: Armazena os papéis de autorização (por exemplo, ROLE_ADMIN).
lei_leilao: Armazena os dados dos leilões, como endereço, datas de visitação e ocorrência, status.
pro_produto: Armazena os produtos que serão leiloados (dispositivos de informática e veículos).
lan_lance: Armazena os lances feitos pelos clientes nos produtos.
ins_instituicao_financeira: Armazena as instituições financeiras responsáveis pelos pagamentos dos leilões.
Exemplo de Estrutura da Tabela lei_leilao:
sql
Copiar código
CREATE TABLE lei_leilao (
    lei_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    lei_endereco VARCHAR(200) NOT NULL,
    lei_cidade VARCHAR(100) NOT NULL,
    lei_estado VARCHAR(2) NOT NULL,
    lei_data_visita DATETIME NOT NULL,
    lei_data_leilao DATETIME NOT NULL,
    lei_status ENUM('EM_ABERTO', 'EM_ANDAMENTO', 'FINALIZADO') NOT NULL,
    ins_id BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (lei_id),
    FOREIGN KEY (ins_id) REFERENCES ins_instituicao_financeira(ins_id)
);
Scripts SQL de Criação e Inserção:
O projeto contém um arquivo SQL inicial para criar as tabelas e inserir dados de teste. Certifique-se de rodar os scripts após configurar o banco de dados.
