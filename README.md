Sistema de Leilão Eletrônico
Este projeto é um sistema de leilão eletrônico que permite a gestão de leilões de dispositivos de informática e veículos, com funcionalidades de cadastro de produtos, clientes, lances e consulta de leilões. O sistema foi desenvolvido utilizando Java 17, com o framework Quarkus, JPA com Hibernate, Lombok e banco de dados H2.

Requisitos do Projeto
Funcionalidades principais:
Cadastro de produtos: dispositivos de informática (notebooks, monitores, hubs, switches, roteadores) e veículos (carros, motocicletas, caminhões).

Gestão de leilões: criar, atualizar, listar e remover leilões.

Gestão de clientes: cadastro de clientes e controle de permissões.

Gestão de lances: permitir que clientes deem lances em produtos e mantenham histórico de lances.

Associação de produtos a leilões: produtos são vinculados a leilões, e caso não sejam vendidos, podem ser reassociados a outro leilão.

Exportação de leilões: exportar detalhes de leilões em um formato de arquivo .DET contendo informações do leilão, produtos, clientes e histórico de lances.

Principais Pacotes:
controller: Controladores REST que expõem os endpoints da API.

entity: Classes que representam as entidades do banco de dados, como Leilao, Produto, Cliente, etc.

repository: Interfaces que utilizam JPA para realizar operações de CRUD no banco de dados.

service: Contém a lógica de negócios do sistema, como validações e regras de lances.

Configuração do Projeto
Dependências:
O projeto utiliza o Maven para gerenciar as dependências. As principais dependências estão no arquivo pom.xml:

Quarkus: Framework principal do projeto.

Hibernate ORM com Panache: Utilizado para facilitar a integração com o banco de dados via JPA.

Lombok: Para automatizar a criação de getters, setters e construtores.

H2: Banco de dados em memória utilizado.

Passos para configurar o projeto:
Clone o repositório:

bash
git clone https://github.com/gabmota8/LPII.git
cd sistema-leilao
Configuração do application.properties: No arquivo src/main/resources/application.properties, configure as credenciais do banco de dados H2:

properties
quarkus.datasource.db-kind=h2
quarkus.datasource.jdbc.url=jdbc:h2:mem:leilao;DB_CLOSE_DELAY=-1
quarkus.datasource.username=sa
quarkus.datasource.password=
quarkus.hibernate-orm.database.generation=drop-and-create  # Para desenvolvimento
Iniciar o projeto: Rode o seguinte comando para iniciar o servidor Quarkus no modo de desenvolvimento:

bash
./mvnw compile quarkus:dev
Endpoints Disponíveis
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

Documentação da API
A documentação completa da API pode ser acessada via Swagger em: http://localhost:8080/q/swagger-ui

Detalhes do Banco de Dados (H2)
O banco de dados utilizado é o H2. Abaixo estão as principais tabelas e suas descrições:

Tabelas Principais:
usr_usuario: Armazena informações dos clientes do sistema (nome, email, senha).

aut_autorizacao: Armazena os papéis de autorização (por exemplo, ROLE_ADMIN).

lei_leilao: Armazena os dados dos leilões, como endereço, datas de visitação e ocorrência, status.

pro_produto: Armazena os produtos que serão leiloados (dispositivos de informática e veículos).

lan_lance: Armazena os lances feitos pelos clientes nos produtos.

ins_instituicao_financeira: Armazena as instituições financeiras responsáveis pelos pagamentos dos leilões.
