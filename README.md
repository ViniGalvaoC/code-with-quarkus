Claro! Aqui está o README completo em Markdown para você copiar e colar:

````md
# Aplicação de Pedidos com Quarkus, Kafka e MySQL

Esta é uma aplicação simples desenvolvida com Quarkus que expõe um endpoint para criação de pedidos. Os pedidos são enviados via mensageria (Kafka), consumidos e processados verificando o estoque do produto correspondente.

---

## Como rodar

Para iniciar a aplicação e todos os serviços dependentes (como banco de dados MySQL e Kafka), execute o script:

```bash
./run/run.sh
````

Esse script irá:

* Subir os containers necessários via Docker Compose
* Esperar os serviços ficarem disponíveis
* Iniciar a aplicação Quarkus em modo de desenvolvimento (`quarkus:dev`)

---

## Funcionamento

1. A aplicação recebe requisições de criação de pedidos por meio de um endpoint REST.
2. Cada pedido é publicado em um tópico Kafka (`pedidos`).
3. Um consumidor Kafka escuta este tópico, recebe o pedido e executa o processamento:

   * Verifica se o estoque do produto associado é suficiente.
   * Caso insuficiente, atualiza o pedido com status `FAILED`.
   * Caso suficiente, deduz o estoque e atualiza o pedido com status `FINISHED`.

---

## Pré-requisitos

* Docker e Docker Compose instalados
* Java 17+ (para rodar a aplicação Quarkus localmente, se necessário)
* Maven (para buildar a aplicação, se necessário)

---

## Estrutura

* `run/run.sh`: script para subir os containers e iniciar a aplicação.
* `docker-compose.yml`: definição dos containers (MySQL, Kafka, etc).
* Código fonte em `src/main/java` com as entidades `Pedido` e `Produto`.
* `import.sql`: script para popular o banco de dados no startup.

---

## Configurações importantes

* Kafka está configurado para escutar o tópico `pedidos`.
* Banco de dados MySQL está configurado para o schema `db_pedidos`.
* Quarkus está configurado para criar/dropar as tabelas automaticamente e carregar dados do `import.sql`.

---

## Contato

Para dúvidas ou melhorias, abra uma issue ou envie uma mensagem no meu LinkedIn.