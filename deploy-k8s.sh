#!/bin/bash

# Função para aplicar arquivos YAML
apply_k8s_resources(){
  local dir=$1

  # Verifica se o arquivo deployment.yaml existe e aplica
  if [ -f "$dir/deploy/deployment.yaml" ]; then
      kubectl apply -f "$dir/deploy/deployment.yaml"
    fi

    # Verifica se o arquivo service.yaml existe e aplica
    if [ -f "$dir/deploy/service.yaml" ]; then
      kubectl apply -f "$dir/deploy/service.yaml"
    fi

    # Verifica se o arquivo configmap.yaml existe e aplica (apenas para shopping-api)
    if [ -f "$dir/deploy/configmap.yaml" ]; then
      kubectl apply -f "$dir/deploy/configmap.yaml"
    fi
}

# Lista os diretórios dos microsserviços
services=("user-api" "product-api" "shopping-api")

# Itera sobre cada serviço e aplica os recursos Kubernetes
for service in "${services[@]}"; do
  apply_k8s_resources $service
done