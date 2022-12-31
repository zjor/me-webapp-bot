#!/bin/bash

NS=me-webapp-bot
APP=me-webapp-bot
VERSION=$(git rev-parse --short HEAD)

set -x

helm upgrade --namespace ${NS} --install ${APP} --set version=${VERSION} ./ops/me-webapp-bot