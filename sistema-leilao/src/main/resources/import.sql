--- Apagar o esquema existente, caso haja
DROP SCHEMA IF EXISTS spring;
DROP USER IF EXISTS "new_user"@"localhost";

-- Criar o esquema e usuário
CREATE SCHEMA spring;
CREATE USER 'new_user'@'localhost' IDENTIFIED BY 'pass123';
GRANT SELECT, INSERT, DELETE, UPDATE ON spring.* TO 'new_user'@'localhost';
FLUSH PRIVILEGES;
USE spring;

-- Tabela de Usuários (Clientes)
CREATE TABLE usr_usuario (
    usr_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    usr_nome VARCHAR(50) NOT NULL,
    usr_email VARCHAR(100) NOT NULL,
    usr_senha TEXT NOT NULL,  -- Alterado para TEXT para armazenar senhas hashadas
    PRIMARY KEY (usr_id),
    UNIQUE KEY uni_usuario_email (usr_email)
);

-- Tabela de Autorizações (Permissões)
CREATE TABLE aut_autorizacao (
    aut_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    aut_nome VARCHAR(20) NOT NULL,
    PRIMARY KEY (aut_id),
    UNIQUE KEY uni_aut_nome (aut_nome)
);

-- Relação entre Usuários e Autorizações (Muitos-para-Muitos)
CREATE TABLE uau_usuario_autorizacao (
    usr_id BIGINT UNSIGNED NOT NULL,
    aut_id BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (usr_id, aut_id),
    FOREIGN KEY (usr_id) REFERENCES usr_usuario (usr_id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (aut_id) REFERENCES aut_autorizacao (aut_id) ON DELETE RESTRICT ON UPDATE CASCADE
);

-- Tabela de Instituições Financeiras
CREATE TABLE ins_instituicao_financeira (
    ins_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    ins_nome VARCHAR(100) NOT NULL,
    ins_cnpj VARCHAR(20) NOT NULL,
    PRIMARY KEY (ins_id),
    UNIQUE KEY uni_cnpj (ins_cnpj)
);

-- Tabela de Leilões
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

-- Tabela de Produtos (Dispositivos de Informática e Veículos)
CREATE TABLE pro_produto (
    pro_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    pro_nome VARCHAR(100) NOT NULL,
    pro_preco_inicial DECIMAL(10, 2) NOT NULL CHECK (pro_preco_inicial > 0),  -- Adicionado restrição CHECK
    pro_tipo ENUM('INFORMATICA', 'VEICULO') NOT NULL,
    pro_vendido BOOLEAN NOT NULL DEFAULT FALSE,
    lei_id BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (pro_id),
    FOREIGN KEY (lei_id) REFERENCES lei_leilao(lei_id)
);

-- Detalhes de dispositivos de informática
CREATE TABLE inf_dispositivo (
    inf_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    inf_especificacoes TEXT NOT NULL,
    PRIMARY KEY (inf_id)
);

-- Detalhes de veículos
CREATE TABLE vei_veiculo (
    vei_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    vei_placa VARCHAR(10) NOT NULL,
    vei_modelo VARCHAR(100) NOT NULL,
    vei_fabricante VARCHAR(100) NOT NULL,
    vei_ano INT NOT NULL,
    PRIMARY KEY (vei_id)
);

-- Tabela de Lances
CREATE TABLE lan_lance (
    lan_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    lan_valor DECIMAL(10, 2) NOT NULL,
    lan_data_hora DATETIME NOT NULL,
    pro_id BIGINT UNSIGNED NOT NULL,
    usr_id BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (lan_id),
    FOREIGN KEY (pro_id) REFERENCES pro_produto(pro_id) ON DELETE CASCADE,
    FOREIGN KEY (usr_id) REFERENCES usr_usuario(usr_id) ON DELETE CASCADE
);

-- Adicionado índices
CREATE INDEX idx_usr_email ON usr_usuario (usr_email);
CREATE INDEX idx_ins_cnpj ON ins_instituicao_financeira (ins_cnpj);
CREATE INDEX idx_pro_tipo ON pro_produto (pro_tipo);

-- Inserção inicial de dados
INSERT INTO usr_usuario (usr_nome, usr_email, usr_senha) VALUES ('Nome do Usuário', 'email@dominio.com', 'senha_hashada');
