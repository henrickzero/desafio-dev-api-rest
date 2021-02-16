
### Desafio
Entrega dos artefatos gerados para o Desafio DOCK

### Planejamento e Execucao 
task 100 - Kick Off;
task 200 - Analise e especificação;
  Regras:

    Pessoas:
    - CPF unico

    Contas
    - Permitir cadastrar varias contas para um CPF
    - Tipo Conta: 0=CONTA CORRENTE, 1=CONTA POUPANCA, 2=CONTA SALARIO;
    - Nao permitir saque sem saldo;
    - Nao permitir saque se exceder o limite diario;
    - Nao permitir saque caso a conta esteja bloqueada;

    Transaccoes
    - Tipo Transacao: 0=DEPOSITO, 1=SAQUE;

### Definicao da arquitetura
    task 300 - Definição da arquitetura; JAVA + SPRINGBOOT + JPA + MYSQL + DOCKER
    - A arquitetura escolhida permite ter uma aplicação rodando em produção rapidamente, pois já oferece a maioria dos recursos prontos para utilização, encurtando o comprimento do código e seguindo as melhores práticas de design com configurações já otimizadas. Assim, reduzindo o tempo de desenvolvimento e criando toda estrutura do aplicativo com menos ou quase nenhuma configuração. Em resumo, ela faz com que você perca mais tempo programando do que configurando o ambiente de trabalho.

### Implementacao
    task 400 - Configuração do ambiente;

    task 500 - Script de BANCO;
    Arquivo: CreateDatabase.sql

    CREATE TABLE `pessoas` (
      `ID_PESSOA` INT NOT NULL AUTO_INCREMENT COMMENT 'ID da Pessoa',
      `NM_PESSOA` VARCHAR(50) NOT NULL COMMENT 'Nome Pessoa',
      `NU_CPF` VARCHAR(11) NOT NULL COMMENT 'Numero CPF',
      `DT_NASCIMENTO` DATE NOT NULL COMMENT 'Data Nascimento',
      PRIMARY KEY (`ID_PESSOA`)
    ) ;

    ALTER TABLE `pessoas` 
    ADD CONSTRAINT UNIQUE_PESSOAS_CPF
    UNIQUE (NU_CPF);


    CREATE TABLE `contas` (
      `ID_CONTA` INT NOT NULL AUTO_INCREMENT COMMENT 'ID da Conta',
      `ID_PESSOA` INT NOT NULL COMMENT 'ID Pessoa',
      `VL_SALDO` REAL NOT NULL DEFAULT '0' COMMENT 'Saldo',
      `VL_LIMITE_SAQUE_DIARIO` REAL NOT NULL DEFAULT '0' COMMENT 'Limite Saque Diario',
      `IC_TIPO_CONTA` INT NOT NULL COMMENT 'Tipo Conta',
      `IS_FLAG_ATIVO` BOOLEAN NOT NULL COMMENT 'Flag Ativo',
      `DT_CRIACAO` DATE NOT NULL COMMENT 'Data Criacao',
      PRIMARY KEY (`ID_CONTA`),
      FOREIGN KEY (ID_PESSOA) REFERENCES pessoas(ID_PESSOA)
    );


    ALTER TABLE `contas` 
    ADD CONSTRAINT CHECK_CONTAS_TIPO_CONTA
    CHECK (0 OR 1 OR 2);


    CREATE TABLE `transacoes` (
      `ID_TRANSACAO` INT NOT NULL AUTO_INCREMENT COMMENT 'ID Transacao',
      `ID_CONTA` INT NOT NULL COMMENT 'ID Conta',
      `VL_VALOR` REAL NOT NULL COMMENT 'Valor',
      `IC_TIPO` INT NOT NULL COMMENT 'Tipo Transacao (0:deposito,1:saque)',
      `DT_TRANSACAO` DATE NOT NULL COMMENT 'Data Transacao',
      PRIMARY KEY (`ID_TRANSACAO`),
      FOREIGN KEY (ID_CONTA) REFERENCES contas(ID_CONTA)
    ) ;

    ALTER TABLE `transacoes` 
    ADD CONSTRAINT CHECK_TRANSACOES_TIPO
    CHECK (0 OR 1);

### Implementacao codigo (JAVA)
      task 500 - Criar Entidades(Business);
      task 501 - Criar DAOs (Dados);
      task 502 - Criar camada Rules (Business);
      task 503 - Criar camada Web Services (Controler);

### Implementacao testes de código (JUnit)
      task 600 - Criar teste unitario - Entidades(Business);
      task 601 - Criar teste unitario - DAOs (Dados);
      task 602 - Criar teste unitario - camada Rules (Business);
      task 603 - Criar teste unitario - camada Web Services (Controler);
  

### Implementacao testes de desempenho (JMeter)
      task 600 - Criar teste desempenho - Entidades(Business);
      task 601 - Criar teste desempenho - DAOs (Dados);
      task 602 - Criar teste desempenho - camada Rules (Business);
      task 603 - Criar teste desempenho - camada Web Services (Controler);

### Implementacao Documentacao (SWAGGER)
  URL: http://localhost:8080/swagger-ui.html
  Observacao: Documentacao gerada automaticamente, apos executar o projeto basta acessar a URL indicada.

### Instruções
      1. Faça o clone do repositorio;
        https://github.com/henrickzero/desafio-dev-api-rest.git

      2. Execute um MAVEN install, atraves do eclipse ou do proprio MVN;
      3. Abra o terminal, acesse a pasta do projeto e execute o comando abaixo;
        docker-compose up 
      4. Aguarde a inicializacao do servicos myapp-mysql e myapp-main; 
      5. Acesse a URL abaixo para testar a API.
        URL: http://localhost:8080/swagger-ui.html


### Contatos
      Luiz Henrique Ribeiro da Silva;
      Telefone: 21 97131-5602;
      Email: henrickzero@gmail.com