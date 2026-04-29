-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION pg_database_owner;

-- DROP SEQUENCE tb_categoria_id_seq;

CREATE SEQUENCE tb_categoria_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_cidade_id_seq;

CREATE SEQUENCE tb_cidade_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_cliente_id_seq;

CREATE SEQUENCE tb_cliente_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_condicao_pagamento_id_seq;

CREATE SEQUENCE tb_condicao_pagamento_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_conta_pagar_id_seq;

CREATE SEQUENCE tb_conta_pagar_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_conta_receber_id_seq;

CREATE SEQUENCE tb_conta_receber_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_estado_id_seq;

CREATE SEQUENCE tb_estado_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_forma_pagamento_id_seq;

CREATE SEQUENCE tb_forma_pagamento_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_fornecedor_email_id_seq;

CREATE SEQUENCE tb_fornecedor_email_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_fornecedor_id_seq;

CREATE SEQUENCE tb_fornecedor_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_fornecedor_telefone_id_seq;

CREATE SEQUENCE tb_fornecedor_telefone_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_funcao_funcionario_id_seq;

CREATE SEQUENCE tb_funcao_funcionario_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_funcionario_id_seq;

CREATE SEQUENCE tb_funcionario_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_item_nota_entrada_id_seq;

CREATE SEQUENCE tb_item_nota_entrada_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_item_nota_saida_id_seq;

CREATE SEQUENCE tb_item_nota_saida_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_marca_id_seq;

CREATE SEQUENCE tb_marca_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_modalidade_nfe_id_seq;

CREATE SEQUENCE tb_modalidade_nfe_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_nota_entrada_id_seq;

CREATE SEQUENCE tb_nota_entrada_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_nota_saida_id_seq;

CREATE SEQUENCE tb_nota_saida_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_pais_id_seq;

CREATE SEQUENCE tb_pais_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_parcela_condicao_pagamento_id_seq;

CREATE SEQUENCE tb_parcela_condicao_pagamento_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_produto_id_seq;

CREATE SEQUENCE tb_produto_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_servico_id_seq;

CREATE SEQUENCE tb_servico_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_transportadora_email_id_seq;

CREATE SEQUENCE tb_transportadora_email_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_transportadora_id_seq;

CREATE SEQUENCE tb_transportadora_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_transportadora_telefone_id_seq;

CREATE SEQUENCE tb_transportadora_telefone_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_unidade_medida_id_seq;

CREATE SEQUENCE tb_unidade_medida_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE tb_veiculo_id_seq;

CREATE SEQUENCE tb_veiculo_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;-- public.tb_categoria definição

-- Drop table

-- DROP TABLE tb_categoria;

CREATE TABLE tb_categoria (
	id bigserial NOT NULL,
	categoria varchar(60) NOT NULL,
	ativo bool DEFAULT true NULL,
	data_criacao timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	data_alteracao timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	data_cadastro timestamp(6) NULL,
	nome varchar(60) NOT NULL,
	ultima_modificacao timestamp(6) NULL,
	CONSTRAINT tb_categoria_pkey PRIMARY KEY (id)
);


-- public.tb_condicao_pagamento definição

-- Drop table

-- DROP TABLE tb_condicao_pagamento;

CREATE TABLE tb_condicao_pagamento (
	id bigserial NOT NULL,
	condicao_pagamento varchar(100) NULL,
	parcelas int4 DEFAULT 1 NOT NULL,
	dias_primeira_parcela int4 DEFAULT 0 NULL,
	dias_entre_parcelas int4 DEFAULT 0 NULL,
	percentual_juros numeric(5, 2) DEFAULT 0.00 NULL,
	percentual_multa numeric(5, 2) DEFAULT 0.00 NULL,
	percentual_desconto numeric(5, 2) DEFAULT 0.00 NULL,
	ativo bool DEFAULT true NULL,
	data_cadastro timestamp DEFAULT CURRENT_TIMESTAMP NULL,
	ultima_modificacao timestamp DEFAULT CURRENT_TIMESTAMP NULL,
	a_vista bool NOT NULL,
	data_alteracao timestamp(6) NULL,
	descricao varchar(255) NULL,
	numero_parcelas int4 NOT NULL,
	usuario_alteracao varchar(100) NULL,
	usuario_cadastro varchar(100) NULL,
	CONSTRAINT tb_condicao_pagamento_pkey PRIMARY KEY (id)
);


-- public.tb_forma_pagamento definição

-- Drop table

-- DROP TABLE tb_forma_pagamento;

CREATE TABLE tb_forma_pagamento (
	id bigserial NOT NULL,
	nome varchar(100) NOT NULL,
	descricao varchar(100) NOT NULL,
	ativo bool DEFAULT true NOT NULL,
	data_cadastro timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	ultima_modificacao timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	CONSTRAINT tb_forma_pagamento_pkey PRIMARY KEY (id)
);


-- public.tb_funcao_funcionario definição

-- Drop table

-- DROP TABLE tb_funcao_funcionario;

CREATE TABLE tb_funcao_funcionario (
	id bigserial NOT NULL,
	funcao_funcionario varchar(255) NOT NULL,
	descricao varchar(255) NULL,
	salario_base numeric(10, 2) DEFAULT 0.00 NULL,
	carga_horaria numeric(10, 2) NOT NULL,
	requer_cnh bool DEFAULT false NULL,
	observacao varchar(255) NULL,
	ativo bool DEFAULT true NULL,
	data_cadastro timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	ultima_modificacao timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	CONSTRAINT tb_funcao_funcionario_pkey PRIMARY KEY (id)
);


-- public.tb_marca definição

-- Drop table

-- DROP TABLE tb_marca;

CREATE TABLE tb_marca (
	id int8 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	ativo bool NOT NULL,
	data_cadastro timestamp(6) NULL,
	nome varchar(60) NOT NULL,
	ultima_modificacao timestamp(6) NULL,
	CONSTRAINT tb_marca_pkey PRIMARY KEY (id)
);


-- public.tb_modalidade_nfe definição

-- Drop table

-- DROP TABLE tb_modalidade_nfe;

CREATE TABLE tb_modalidade_nfe (
	id int8 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	ativo bool NOT NULL,
	codigo varchar(10) NOT NULL,
	data_cadastro timestamp(6) NULL,
	descricao varchar(100) NOT NULL,
	ultima_modificacao timestamp(6) NULL,
	CONSTRAINT tb_modalidade_nfe_pkey PRIMARY KEY (id)
);


-- public.tb_pais definição

-- Drop table

-- DROP TABLE tb_pais;

CREATE TABLE tb_pais (
	id bigserial NOT NULL,
	nome varchar(100) NOT NULL,
	sigla varchar(5) NULL,
	codigo varchar(5) NULL,
	nacionalidade varchar(100) NULL,
	ativo bool DEFAULT true NOT NULL,
	data_cadastro timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	ultima_modificacao timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	CONSTRAINT tb_pais_pkey PRIMARY KEY (id)
);


-- public.tb_unidade_medida definição

-- Drop table

-- DROP TABLE tb_unidade_medida;

CREATE TABLE tb_unidade_medida (
	id int8 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	ativo bool NOT NULL,
	data_cadastro timestamp(6) NULL,
	nome varchar(255) NOT NULL,
	sigla varchar(10) NULL,
	ultima_modificacao timestamp(6) NULL,
	CONSTRAINT tb_unidade_medida_pkey PRIMARY KEY (id)
);


-- public.tb_estado definição

-- Drop table

-- DROP TABLE tb_estado;

CREATE TABLE tb_estado (
	id bigserial NOT NULL,
	nome varchar(100) NOT NULL,
	uf varchar(2) NOT NULL,
	pais_id int8 NOT NULL,
	ativo bool DEFAULT true NOT NULL,
	data_cadastro timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	ultima_modificacao timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	CONSTRAINT tb_estado_pkey PRIMARY KEY (id),
	CONSTRAINT tb_estado_pais_id_fkey FOREIGN KEY (pais_id) REFERENCES tb_pais(id)
);


-- public.tb_parcela_condicao_pagamento definição

-- Drop table

-- DROP TABLE tb_parcela_condicao_pagamento;

CREATE TABLE tb_parcela_condicao_pagamento (
	id int8 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	data_alteracao timestamp(6) NULL,
	data_cadastro timestamp(6) NOT NULL,
	dias int4 NOT NULL,
	numero_parcela int4 NOT NULL,
	percentual numeric(5, 2) NOT NULL,
	condicao_pagamento_id int8 NOT NULL,
	forma_pagamento_id int8 NOT NULL,
	CONSTRAINT tb_parcela_condicao_pagamento_pkey PRIMARY KEY (id),
	CONSTRAINT fka5kw0xqteipehp90bj36on87k FOREIGN KEY (condicao_pagamento_id) REFERENCES tb_condicao_pagamento(id),
	CONSTRAINT fkoeugg8kas6kbxtuf0qx6936j9 FOREIGN KEY (forma_pagamento_id) REFERENCES tb_forma_pagamento(id)
);


-- public.tb_produto definição

-- Drop table

-- DROP TABLE tb_produto;

CREATE TABLE tb_produto (
	id bigserial NOT NULL,
	produto varchar(255) NOT NULL,
	codigo_barras varchar(255) NULL,
	referencia varchar(10) NULL,
	categoria_id int8 NULL,
	valor_compra numeric(10, 2) NOT NULL,
	valor_venda numeric(10, 2) NOT NULL,
	percentual_lucro numeric(10, 2) NOT NULL,
	quantidade numeric(15, 3) DEFAULT 0 NOT NULL,
	quantidade_minima numeric(15, 3) DEFAULT 1 NOT NULL,
	descricao text NULL,
	observacoes varchar(255) NULL,
	ativo bool DEFAULT true NULL,
	data_criacao timestamp DEFAULT CURRENT_TIMESTAMP NULL,
	ultima_modificacao timestamp DEFAULT CURRENT_TIMESTAMP NULL,
	nome varchar(255) NOT NULL,
	marca_id int8 NOT NULL,
	unidade_medida_id int8 NOT NULL,
	data_ultima_compra date NULL,
	valor_ultima_compra numeric(15, 4) NULL,
	data_ultima_venda date NULL,
	valor_ultima_venda numeric(15, 4) NULL,
	CONSTRAINT tb_produto_pkey PRIMARY KEY (id),
	CONSTRAINT fkjm741es56cwngwn3hsr15uqew FOREIGN KEY (unidade_medida_id) REFERENCES tb_unidade_medida(id),
	CONSTRAINT fkp8lv394pdqcgxop9xma6s3jj5 FOREIGN KEY (marca_id) REFERENCES tb_marca(id),
	CONSTRAINT tb_produto_categoria_id_fkey FOREIGN KEY (categoria_id) REFERENCES tb_categoria(id)
);


-- public.tb_servico definição

-- Drop table

-- DROP TABLE tb_servico;

CREATE TABLE tb_servico (
	id int8 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	ativo bool NOT NULL,
	codigo varchar(50) NULL,
	custo numeric(15, 2) NULL,
	data_alteracao timestamp(6) NULL,
	data_cadastro timestamp(6) NOT NULL,
	descricao varchar(500) NULL,
	observacao varchar(500) NULL,
	percentual_lucro numeric(5, 2) NULL,
	preco numeric(15, 2) NOT NULL,
	servico varchar(255) NOT NULL,
	tempo_estimado_minutos int4 NULL,
	usuario_alteracao varchar(100) NULL,
	usuario_cadastro varchar(100) NULL,
	categoria_id int8 NULL,
	unidade_medida_id int8 NULL,
	CONSTRAINT tb_servico_pkey PRIMARY KEY (id),
	CONSTRAINT uk_servico_codigo UNIQUE (codigo),
	CONSTRAINT fk1kkbhxbftc63vrdaearu3kple FOREIGN KEY (unidade_medida_id) REFERENCES tb_unidade_medida(id),
	CONSTRAINT fk6p3hjeexxj7nj2almaodwt4fq FOREIGN KEY (categoria_id) REFERENCES tb_categoria(id)
);


-- public.tb_cidade definição

-- Drop table

-- DROP TABLE tb_cidade;

CREATE TABLE tb_cidade (
	id bigserial NOT NULL,
	nome varchar(100) NOT NULL,
	codigo_ibge varchar(10) NULL,
	estado_id int8 NOT NULL,
	ativo bool DEFAULT true NOT NULL,
	data_cadastro timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	ultima_modificacao timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	CONSTRAINT tb_cidade_pkey PRIMARY KEY (id),
	CONSTRAINT tb_cidade_estado_id_fkey FOREIGN KEY (estado_id) REFERENCES tb_estado(id)
);


-- public.tb_cliente definição

-- Drop table

-- DROP TABLE tb_cliente;

CREATE TABLE tb_cliente (
	id int8 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	apelido varchar(60) NULL,
	ativo bool NOT NULL,
	bairro varchar(50) NULL,
	cep varchar(9) NULL,
	complemento varchar(100) NULL,
	cpf_cnpj varchar(14) NULL,
	data_alteracao timestamp(6) NULL,
	data_criacao timestamp(6) NULL,
	data_nascimento date NULL,
	email varchar(100) NULL,
	endereco varchar(200) NULL,
	estado_civil varchar(255) NULL,
	limite_credito numeric(10, 2) NULL,
	nome varchar(100) NOT NULL,
	numero varchar(5) NULL,
	observacao varchar(255) NULL,
	rg_inscricao_estadual varchar(14) NULL,
	sexo varchar(1) NULL,
	telefone varchar(20) NULL,
	tipo int4 NULL,
	cidade_id int8 NULL,
	nacionalidade_id int8 NULL,
	condicao_pagamento_id int8 NULL,
	CONSTRAINT tb_cliente_pkey PRIMARY KEY (id),
	CONSTRAINT uk_cliente_cpf_cnpj UNIQUE (cpf_cnpj),
	CONSTRAINT fkm2i8euogs376m2mrfivlm8buo FOREIGN KEY (condicao_pagamento_id) REFERENCES tb_condicao_pagamento(id),
	CONSTRAINT fkmn89sktuuou6odgbvhnsjbpiv FOREIGN KEY (cidade_id) REFERENCES tb_cidade(id),
	CONSTRAINT fkn2xd3hq0o82mpa95wu42v087u FOREIGN KEY (nacionalidade_id) REFERENCES tb_pais(id)
);


-- public.tb_funcionario definição

-- Drop table

-- DROP TABLE tb_funcionario;

CREATE TABLE tb_funcionario (
	id bigserial NOT NULL,
	funcionario varchar(255) NOT NULL,
	apelido varchar(255) NULL,
	cpf_cnpj varchar(14) NULL,
	rg_inscricao_estadual varchar(14) NULL,
	data_nascimento date NOT NULL,
	sexo varchar(1) NOT NULL,
	estado_civil varchar(1) NOT NULL,
	telefone varchar(20) NOT NULL,
	email varchar(255) NOT NULL,
	endereco varchar(200) NOT NULL,
	numero varchar(20) NOT NULL,
	complemento varchar(100) NULL,
	bairro varchar(100) NOT NULL,
	cep varchar(8) NOT NULL,
	cidade_id int8 NOT NULL,
	nacionalidade_id int8 NOT NULL,
	funcao_funcionario_id int8 NOT NULL,
	cnh varchar(20) NULL,
	data_validade_cnh date NULL,
	salario numeric(15, 2) NOT NULL,
	data_admissao date NOT NULL,
	data_demissao date NULL,
	observacao varchar(500) NULL,
	ativo bool DEFAULT true NULL,
	data_criacao timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	data_alteracao timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	categoria_cnh varchar(5) NULL,
	cpf varchar(11) NOT NULL,
	data_cadastro timestamp(6) NOT NULL,
	logradouro varchar(255) NULL,
	matricula varchar(20) NULL,
	rg varchar(20) NULL,
	usuario_alteracao varchar(100) NULL,
	usuario_cadastro varchar(100) NULL,
	validade_cnh date NULL,
	CONSTRAINT tb_funcionario_pkey PRIMARY KEY (id),
	CONSTRAINT uk_funcionario_cpf UNIQUE (cpf),
	CONSTRAINT uk_funcionario_matricula UNIQUE (matricula),
	CONSTRAINT tb_funcionario_cidade_id_fkey FOREIGN KEY (cidade_id) REFERENCES tb_cidade(id),
	CONSTRAINT tb_funcionario_funcao_funcionario_id_fkey FOREIGN KEY (funcao_funcionario_id) REFERENCES tb_funcao_funcionario(id),
	CONSTRAINT tb_funcionario_nacionalidade_id_fkey FOREIGN KEY (nacionalidade_id) REFERENCES tb_pais(id)
);


-- public.tb_transportadora definição

-- Drop table

-- DROP TABLE tb_transportadora;

CREATE TABLE tb_transportadora (
	id bigserial NOT NULL,
	razao_social varchar(150) NOT NULL,
	nome_fantasia varchar(100) NULL,
	cnpj varchar(18) NULL,
	tipo bpchar(1) DEFAULT 'J'::bpchar NULL,
	rg_ie varchar(20) NULL,
	email varchar(100) NULL,
	telefone varchar(20) NULL,
	endereco varchar(200) NULL,
	numero varchar(20) NULL,
	complemento varchar(100) NULL,
	bairro varchar(100) NULL,
	cep varchar(8) NULL,
	cidade_id int8 NULL,
	condicao_pagamento_id int8 NULL,
	observacao varchar(500) NULL,
	ativo bool DEFAULT true NULL,
	data_cadastro timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	ultima_modificacao timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	antt varchar(20) NULL,
	apelido varchar(255) NULL,
	cpf_cnpj varchar(14) NULL,
	data_alteracao timestamp(6) NULL,
	logradouro varchar(255) NULL,
	transportadora varchar(255) NOT NULL,
	usuario_alteracao varchar(100) NULL,
	usuario_cadastro varchar(100) NULL,
	nacionalidade_id int8 NULL,
	CONSTRAINT tb_transportadora_pkey PRIMARY KEY (id),
	CONSTRAINT uk_transportadora_cpf_cnpj UNIQUE (cpf_cnpj),
	CONSTRAINT fk6xlevljunh6f52yt9eqr0cqj3 FOREIGN KEY (nacionalidade_id) REFERENCES tb_pais(id),
	CONSTRAINT tb_transportadora_cidade_id_fkey FOREIGN KEY (cidade_id) REFERENCES tb_cidade(id),
	CONSTRAINT tb_transportadora_condicao_pagamento_id_fkey FOREIGN KEY (condicao_pagamento_id) REFERENCES tb_condicao_pagamento(id)
);


-- public.tb_transportadora_email definição

-- Drop table

-- DROP TABLE tb_transportadora_email;

CREATE TABLE tb_transportadora_email (
	id int8 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	ativo bool NOT NULL,
	data_alteracao timestamp(6) NULL,
	data_cadastro timestamp(6) NOT NULL,
	email varchar(255) NOT NULL,
	principal bool NOT NULL,
	tipo varchar(50) NULL,
	transportadora_id int8 NOT NULL,
	CONSTRAINT tb_transportadora_email_pkey PRIMARY KEY (id),
	CONSTRAINT fk24jb55a7fli5yilcmjjfqwm9n FOREIGN KEY (transportadora_id) REFERENCES tb_transportadora(id)
);


-- public.tb_transportadora_telefone definição

-- Drop table

-- DROP TABLE tb_transportadora_telefone;

CREATE TABLE tb_transportadora_telefone (
	id int8 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	ativo bool NOT NULL,
	data_alteracao timestamp(6) NULL,
	data_cadastro timestamp(6) NOT NULL,
	principal bool NOT NULL,
	telefone varchar(20) NOT NULL,
	tipo varchar(50) NULL,
	transportadora_id int8 NOT NULL,
	CONSTRAINT tb_transportadora_telefone_pkey PRIMARY KEY (id),
	CONSTRAINT fk8ivo601uog1rgm458kvfxn3cf FOREIGN KEY (transportadora_id) REFERENCES tb_transportadora(id)
);


-- public.tb_veiculo definição

-- Drop table

-- DROP TABLE tb_veiculo;

CREATE TABLE tb_veiculo (
	id bigserial NOT NULL,
	placa varchar(10) NOT NULL,
	modelo varchar(50) NULL,
	marca varchar(50) NULL,
	ano int4 NULL,
	capacidade numeric(10, 2) NULL,
	ativo bool DEFAULT true NULL,
	data_cadastro timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	ultima_modificacao timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	transportadora_id int8 NULL,
	CONSTRAINT tb_veiculo_pkey PRIMARY KEY (id),
	CONSTRAINT fkbtqacwbc7jg6ojlk4tymcn8s2 FOREIGN KEY (transportadora_id) REFERENCES tb_transportadora(id)
);


-- public.tb_fornecedor definição

-- Drop table

-- DROP TABLE tb_fornecedor;

CREATE TABLE tb_fornecedor (
	id bigserial NOT NULL,
	fornecedor varchar(255) NOT NULL,
	apelido varchar(255) NULL,
	cpf_cnpj varchar(14) NULL,
	rg_inscricao_estadual varchar(14) NULL,
	tipo int4 NOT NULL,
	email varchar(255) NOT NULL,
	telefone varchar(255) NOT NULL,
	endereco varchar(255) NULL,
	numero varchar(20) NULL,
	complemento varchar(100) NULL,
	bairro varchar(100) NULL,
	cep varchar(8) NULL,
	cidade_id int8 NULL,
	nacionalidade_id int8 NULL,
	condicao_pagamento_id int8 NULL,
	limite_credito numeric(15, 2) DEFAULT 0.00 NOT NULL,
	observacoes varchar(255) NULL,
	ativo bool DEFAULT true NOT NULL,
	data_criacao timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	data_alteracao timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	data_cadastro timestamp(6) NOT NULL,
	data_nascimento_abertura date NULL,
	logradouro varchar(255) NULL,
	observacao varchar(500) NULL,
	rg_ie varchar(20) NULL,
	usuario_alteracao varchar(100) NULL,
	usuario_cadastro varchar(100) NULL,
	transportadora_id int8 NULL,
	CONSTRAINT tb_fornecedor_pkey PRIMARY KEY (id),
	CONSTRAINT uk_fornecedor_cpf_cnpj UNIQUE (cpf_cnpj),
	CONSTRAINT fk8f8lfobh91c2b84fj1n2nm8kk FOREIGN KEY (transportadora_id) REFERENCES tb_transportadora(id),
	CONSTRAINT tb_fornecedor_cidade_id_fkey FOREIGN KEY (cidade_id) REFERENCES tb_cidade(id),
	CONSTRAINT tb_fornecedor_condicao_pagamento_id_fkey FOREIGN KEY (condicao_pagamento_id) REFERENCES tb_condicao_pagamento(id),
	CONSTRAINT tb_fornecedor_nacionalidade_id_fkey FOREIGN KEY (nacionalidade_id) REFERENCES tb_pais(id)
);


-- public.tb_fornecedor_email definição

-- Drop table

-- DROP TABLE tb_fornecedor_email;

CREATE TABLE tb_fornecedor_email (
	id int8 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	ativo bool NOT NULL,
	data_alteracao timestamp(6) NULL,
	data_cadastro timestamp(6) NOT NULL,
	email varchar(255) NOT NULL,
	principal bool NOT NULL,
	tipo varchar(50) NULL,
	fornecedor_id int8 NOT NULL,
	CONSTRAINT tb_fornecedor_email_pkey PRIMARY KEY (id),
	CONSTRAINT fkcfn4nnyyqpb2svwdnks3rf297 FOREIGN KEY (fornecedor_id) REFERENCES tb_fornecedor(id)
);


-- public.tb_fornecedor_telefone definição

-- Drop table

-- DROP TABLE tb_fornecedor_telefone;

CREATE TABLE tb_fornecedor_telefone (
	id int8 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	ativo bool NOT NULL,
	data_alteracao timestamp(6) NULL,
	data_cadastro timestamp(6) NOT NULL,
	principal bool NOT NULL,
	telefone varchar(20) NOT NULL,
	tipo varchar(50) NULL,
	fornecedor_id int8 NOT NULL,
	CONSTRAINT tb_fornecedor_telefone_pkey PRIMARY KEY (id),
	CONSTRAINT fklxqbrfwp36g7ri1xhu7nm0hsh FOREIGN KEY (fornecedor_id) REFERENCES tb_fornecedor(id)
);


-- public.tb_nota_entrada definição

-- Drop table

-- DROP TABLE tb_nota_entrada;

CREATE TABLE tb_nota_entrada (
	id int8 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	chave_acesso varchar(44) NULL,
	data_alteracao timestamp(6) NULL,
	data_cadastro timestamp(6) NOT NULL,
	data_cancelamento timestamp(6) NULL,
	data_confirmacao timestamp(6) NULL,
	data_emissao date NOT NULL,
	data_entrada date NOT NULL,
	numero varchar(20) NOT NULL,
	observacao varchar(500) NULL,
	serie varchar(10) NOT NULL,
	status varchar(20) NOT NULL,
	usuario_alteracao varchar(100) NULL,
	usuario_cadastro varchar(100) NULL,
	valor_desconto numeric(15, 2) NULL,
	valor_frete numeric(15, 2) NULL,
	valor_icms numeric(15, 2) NULL,
	valor_ipi numeric(15, 2) NULL,
	valor_outras_despesas numeric(15, 2) NULL,
	valor_produtos numeric(15, 2) NULL,
	valor_seguro numeric(15, 2) NULL,
	valor_total numeric(15, 2) NULL,
	condicao_pagamento_id int8 NULL,
	fornecedor_id int8 NOT NULL,
	modalidade_nfe_id int8 NULL,
	transportadora_id int8 NULL,
	CONSTRAINT tb_nota_entrada_pkey PRIMARY KEY (id),
	CONSTRAINT uk_nota_entrada_num_serie_forn UNIQUE (numero, serie, fornecedor_id),
	CONSTRAINT fk3tkeg1v008ch594bf9tp1jhwp FOREIGN KEY (fornecedor_id) REFERENCES tb_fornecedor(id),
	CONSTRAINT fk9d2o867dljm7w3oj6mp47skjs FOREIGN KEY (condicao_pagamento_id) REFERENCES tb_condicao_pagamento(id),
	CONSTRAINT fkm7o9weo8vvqh3jhw43bpkmsvm FOREIGN KEY (modalidade_nfe_id) REFERENCES tb_modalidade_nfe(id),
	CONSTRAINT fkqkxcqihjsbk9dh0dw1po4n9ap FOREIGN KEY (transportadora_id) REFERENCES tb_transportadora(id)
);


-- public.tb_nota_saida definição

-- Drop table

-- DROP TABLE tb_nota_saida;

CREATE TABLE tb_nota_saida (
	id int8 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	chave_acesso varchar(44) NULL,
	data_alteracao timestamp(6) NULL,
	data_cadastro timestamp(6) NOT NULL,
	data_cancelamento timestamp(6) NULL,
	data_confirmacao timestamp(6) NULL,
	data_emissao date NOT NULL,
	data_saida date NOT NULL,
	numero varchar(20) NOT NULL,
	observacao varchar(500) NULL,
	serie varchar(10) NOT NULL,
	status varchar(20) NOT NULL,
	usuario_alteracao varchar(100) NULL,
	usuario_cadastro varchar(100) NULL,
	valor_desconto numeric(15, 2) NULL,
	valor_frete numeric(15, 2) NULL,
	valor_icms numeric(15, 2) NULL,
	valor_ipi numeric(15, 2) NULL,
	valor_outras_despesas numeric(15, 2) NULL,
	valor_produtos numeric(15, 2) NULL,
	valor_seguro numeric(15, 2) NULL,
	valor_total numeric(15, 2) NULL,
	cliente_id int8 NOT NULL,
	condicao_pagamento_id int8 NULL,
	modalidade_nfe_id int8 NULL,
	transportadora_id int8 NULL,
	CONSTRAINT tb_nota_saida_pkey PRIMARY KEY (id),
	CONSTRAINT uk_nota_saida_num_serie UNIQUE (numero, serie),
	CONSTRAINT fk3ypdmovsfjnglpqf6yan1we07 FOREIGN KEY (condicao_pagamento_id) REFERENCES tb_condicao_pagamento(id),
	CONSTRAINT fk5kijcipf5p07fkolaiw9akapk FOREIGN KEY (cliente_id) REFERENCES tb_cliente(id),
	CONSTRAINT fkk969ll75pj5h32qeibl8cyaxm FOREIGN KEY (modalidade_nfe_id) REFERENCES tb_modalidade_nfe(id),
	CONSTRAINT fkq2wjb12gwmi44h3oxq4cuap3u FOREIGN KEY (transportadora_id) REFERENCES tb_transportadora(id)
);


-- public.tb_transportadora_veiculo definição

-- Drop table

-- DROP TABLE tb_transportadora_veiculo;

CREATE TABLE tb_transportadora_veiculo (
	transportadora_id int8 NOT NULL,
	veiculo_id int8 NOT NULL,
	CONSTRAINT tb_transportadora_veiculo_pkey PRIMARY KEY (transportadora_id, veiculo_id),
	CONSTRAINT tb_transportadora_veiculo_transportadora_id_fkey FOREIGN KEY (transportadora_id) REFERENCES tb_transportadora(id),
	CONSTRAINT tb_transportadora_veiculo_veiculo_id_fkey FOREIGN KEY (veiculo_id) REFERENCES tb_veiculo(id)
);


-- public.tb_conta_pagar definição

-- Drop table

-- DROP TABLE tb_conta_pagar;

CREATE TABLE tb_conta_pagar (
	id int8 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	data_alteracao timestamp(6) NULL,
	data_cadastro timestamp(6) NOT NULL,
	data_emissao date NOT NULL,
	data_pagamento date NULL,
	data_vencimento date NOT NULL,
	descricao varchar(255) NULL,
	numero_documento varchar(50) NULL,
	numero_parcela int4 NOT NULL,
	observacao varchar(500) NULL,
	status varchar(20) NOT NULL,
	total_parcelas int4 NOT NULL,
	usuario_alteracao varchar(100) NULL,
	usuario_cadastro varchar(100) NULL,
	valor_desconto numeric(15, 2) NULL,
	valor_juros numeric(15, 2) NULL,
	valor_multa numeric(15, 2) NULL,
	valor_original numeric(15, 2) NOT NULL,
	valor_pago numeric(15, 2) NULL,
	forma_pagamento_id int8 NULL,
	fornecedor_id int8 NULL,
	nota_entrada_id int8 NULL,
	CONSTRAINT tb_conta_pagar_pkey PRIMARY KEY (id),
	CONSTRAINT fkd1es8x2kmftnxpu9k1l59himm FOREIGN KEY (nota_entrada_id) REFERENCES tb_nota_entrada(id),
	CONSTRAINT fkdtbc77np8lv9841g7bf026sn5 FOREIGN KEY (fornecedor_id) REFERENCES tb_fornecedor(id),
	CONSTRAINT fkgljekym7rkjqre6g8sddk1e0x FOREIGN KEY (forma_pagamento_id) REFERENCES tb_forma_pagamento(id)
);


-- public.tb_conta_receber definição

-- Drop table

-- DROP TABLE tb_conta_receber;

CREATE TABLE tb_conta_receber (
	id int8 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	data_alteracao timestamp(6) NULL,
	data_cadastro timestamp(6) NOT NULL,
	data_emissao date NOT NULL,
	data_recebimento date NULL,
	data_vencimento date NOT NULL,
	descricao varchar(255) NULL,
	numero_documento varchar(50) NULL,
	numero_parcela int4 NOT NULL,
	observacao varchar(500) NULL,
	status varchar(20) NOT NULL,
	total_parcelas int4 NOT NULL,
	usuario_alteracao varchar(100) NULL,
	usuario_cadastro varchar(100) NULL,
	valor_desconto numeric(15, 2) NULL,
	valor_juros numeric(15, 2) NULL,
	valor_multa numeric(15, 2) NULL,
	valor_original numeric(15, 2) NOT NULL,
	valor_recebido numeric(15, 2) NULL,
	cliente_id int8 NULL,
	forma_pagamento_id int8 NULL,
	nota_saida_id int8 NULL,
	CONSTRAINT tb_conta_receber_pkey PRIMARY KEY (id),
	CONSTRAINT fk2cm2cm2ghevhcibrpepiqo9rf FOREIGN KEY (forma_pagamento_id) REFERENCES tb_forma_pagamento(id),
	CONSTRAINT fkav5ksyc7mqd9w77p21grpno22 FOREIGN KEY (cliente_id) REFERENCES tb_cliente(id),
	CONSTRAINT fkbtm160gwc7x4rgnt7vu5cwat9 FOREIGN KEY (nota_saida_id) REFERENCES tb_nota_saida(id)
);


-- public.tb_item_nota_entrada definição

-- Drop table

-- DROP TABLE tb_item_nota_entrada;

CREATE TABLE tb_item_nota_entrada (
	id int8 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	observacao varchar(255) NULL,
	quantidade numeric(15, 3) NOT NULL,
	valor_desconto numeric(15, 2) NULL,
	valor_total numeric(15, 2) NULL,
	valor_unitario numeric(15, 4) NOT NULL,
	nota_entrada_id int8 NOT NULL,
	produto_id int8 NOT NULL,
	CONSTRAINT tb_item_nota_entrada_pkey PRIMARY KEY (id),
	CONSTRAINT fklifm6pf2ip3gnrof36s037rvm FOREIGN KEY (nota_entrada_id) REFERENCES tb_nota_entrada(id),
	CONSTRAINT fklwy12j6ier4pga9br1dk052st FOREIGN KEY (produto_id) REFERENCES tb_produto(id)
);


-- public.tb_item_nota_saida definição

-- Drop table

-- DROP TABLE tb_item_nota_saida;

CREATE TABLE tb_item_nota_saida (
	id int8 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	observacao varchar(255) NULL,
	quantidade numeric(15, 3) NOT NULL,
	valor_desconto numeric(15, 2) NULL,
	valor_total numeric(15, 2) NULL,
	valor_unitario numeric(15, 4) NOT NULL,
	nota_saida_id int8 NOT NULL,
	produto_id int8 NOT NULL,
	CONSTRAINT tb_item_nota_saida_pkey PRIMARY KEY (id),
	CONSTRAINT fkfq6htk11coveukgad8b9wp12x FOREIGN KEY (nota_saida_id) REFERENCES tb_nota_saida(id),
	CONSTRAINT fkkqd8nl5y6fcswudponnbci96o FOREIGN KEY (produto_id) REFERENCES tb_produto(id)
);
