#!/bin/bash

source .env

NAMESPACE=me-webapp-bot

kubectl delete secret environment -n ${NAMESPACE}

kubectl create secret generic environment \
  --from-literal=TELEGRAM_WEBAPP_URL=${TELEGRAM_WEBAPP_URL} \
  --from-literal=TELEGRAM_BOT_TOKEN=${TELEGRAM_BOT_TOKEN} \
  --from-literal=TELEGRAM_BOT_NAME=${TELEGRAM_BOT_NAME} \
  -n ${NAMESPACE}