#!/bin/bash

# Função de limpeza automática ao encerrar o script
function cleanup {
  echo "🧹 Encerrando containers com Docker Compose..."
  docker compose down
}
trap cleanup EXIT

echo "🔄 Subindo containers com Docker Compose..."
docker compose up -d

echo "⏳ Aguardando serviços iniciarem..."
sleep 5

echo "🚀 Iniciando Quarkus em modo de desenvolvimento..."
./mvnw quarkus:dev
