# Helm chart maintenance

1. Create namespace `kubectl create namespace retvrn`
2. Create or update secrets:
```bash
$> source ../.env
$> ./create-secrets.sh
```

3. Deploy with Helm

- Run `../deploy-with-helm.sh` script