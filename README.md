# Technical_Challenge_Furia

Esse projeto consiste num desafio técnico para a vaga de assistente de Engenharia de Software na FURIA.
A ideia do desafio era testar a habilidade dos participantes na resolução de problemas, como aprendem e aplicam seus conhecimentos.

## Desafio escolhido - Experiência Conversacional

1. Objetivo: Desenvolver um caso de uso conversacional relacionado à FURIA (Telegram, web chat ou mobile chat);

2. Proposta: Crie um chat para os fãs do time de CS da FURIA. O Chat deve conter tudo que você, como fã, gostaria de ver para acompanhar e interagir com o time.

3. Entrega: 

- Landing page funcional ou aplicação integrada com o chat;
- Apresente em um vídeo de +- 3 minutos como o fã pode interagir e acompanhar o time;
- Documentação completa e clara no GitHub.

## Como solucionei o desafio proposto

Decidi escolher o desafio da experiência conversacional por achar a ideia interessante. E eu sabia que conseguiria aplicar meus conhecimentos, e inovar na hora de criar o projeto. 
No caso, decidi criar uma aplicação web integrada com o chat; utilizando MVC e criando uma API RESTful.

### Funcionalidades do sistema:

- Registro e Login de usuários no sistema;
- Criação/edição/visualização de chats e mensagens;
- Interação com a IA provida pelo Ollama;
- Informações diretas e precisas obtidas por APIs externas;
- Endpoints que só podem ser acessados após o usuário fazer login, tornando a API mais segura.

### Tecnologias utilizadas: 

- Java;
- Spring Web;
- Spring Security;
- Json Web Token;
- Spring AI;
- Spring Cloud;
- Docker;
- Postman;
- Ollama;
- PostgreSQL.

## Como rodar o projeto

Para rodar o projeto na sua máquina, você vai precisar seguir algumas etapas. Algumas delas mais tediosas, outras menos. 
Mas vou deixar o passo a passo explicando, para não ter nenhum erro na hora.

### Ferramentas que devem ser instaladas

Primeiramente, você deve instalar o Java na sua máquina. No caso, utilizei a seguinte versão: 
```"java version "21.0.5" 2024-10-15 LTS".```
Recomendo instalar essa versão para não dar nenhum problema de compatibilidade com o sistema!

Depois disso, teremos que fazer a instalação do Docker para rodar os containers. E essa parte é muito importante, pois é lá que está o banco de dados!
A versão que recomendo é a: ```Docker version 28.0.4, build b8034c0```

Ufa! Estamos quase lá. Mas ainda é preciso que você faça mais uma instalação. Dessa vez, do Ollama. O Ollama é o que utilizei para ter acesso a algum modelo de IA, 
e criar um ambiente de conversa com o usuário. A versão do Ollama que foi utilizada nesse projeto foi a: ```ollama version is 0.6.6```

### Clonando o projeto na sua IDE favorita

Faça a clonagem do projeto através do seguinte comando:

```bash
git clone https://github.com/Invokedzz/Technical_Challenge_Furia
```

### Instalando as dependências no seu pom.xml

Para instalar as dependências do projeto é fácil. Se você já tiver o Maven (gerenciador de dependências) instalado, pode executar o seguinte comando no seu terminal:

PS: você precisa estar na mesma pasta que o projeto para conseguir executar esse comando pelo terminal!

```bash
mvn clean install
```

Se por algum acaso, você não tiver o Maven instalado, pode executar esse comando em seu terminal:

```bash
./mvnw install
```

Depois da instalação das dependências, pronto! Você já tem o projeto *quase* pronto.

### Criando os containers e instalando um modelo de IA no Ollama

Com as dependências do projeto devidamente prontas, precisamos do Docker para termos acesso ao nosso banco de dados. E o processo é bem simples. 
Na pasta do projeto, execute o seguinte comando para criar os containers em sua máquina:

```bash
docker compose up --build
```

Após executar esse comando, perfeito! Você já tem as dependências do projeto e agora o banco de dados operando! 
Agora é necessário fazer a instalação do modelo de IA. Para fazer a instalação do modelo de IA, execute o seguinte comando em seu terminal:

```bash
ollama run phi3:3.8b-instruct
```

O tempo de instalação de um modelo de IA pode variar, mas costuma demorar. Mas após o download, o projeto estará pronto para ser executado.

### Executando o projeto em sua máquina

A melhor hora chegou. Depois de muitos downloads, e tempo de espera, podemos finalmente executar o projeto em nossa máquina.
Vou deixar aqui duas formas para você estar executando o projeto

Se você tiver o Maven instalado em sua máquina:

```bash
mvn spring-boot:run
```

Se você não tiver o Maven instalado, execute este comando aqui:

```bash
./mvnw spring-boot:run
```

Agora o projeto estará rodando em sua máquina! Você pode acessar os endpoints dele via Postman, Insomnia ou através do Swagger. Lembrando que nesse projeto, estou utilizando o portão 9090 para o acesso via localhost.

## Acessando a documentação do projeto com o Swagger

Se por algum acaso, você não tiver o Postman ou Insomnia, te encorajo a acessar a documentação do projeto no Swagger. Nela coloquei os principais endpoints e alguns DTOS.
Para acessar a documentação do Swagger, primeiramente inicie o projeto, e depois acesse a seguinte URL:

```bash
http://localhost:9090/swagger-ui/index.html#/
```

Depois disso, na própria aba de pesquisa do Swagger, escreva: ```/openapi.yaml``` dessa forma você acessa o arquivo com a documentação. 

## Agradecimentos

Esse projeto foi algo super interessante de fazer. Nele pude fortalecer ainda mais as minhas habilidades, e treinar os meus prazos de entrega. 
Agradeço à toda equipe da FURIA por ter trazido uma ideia tão interessante em forma de desafio técnico. Vocês são demais! 

Também agradeço a minha família e a Deus, por me fortalecerem todos os dias e por confiarem no meu trabalho e dedicação.
