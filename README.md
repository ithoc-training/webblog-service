

# Deployment

## Scripting
```
eval $(minikube docker-env)
```

## Local
**Recipe**:
```
mvn clean package
mvn spring-boot:run -Dspring-boot.run.arguments="--DB_USERNAME=username --DB_PASSWORD=password"

docker rmi webblog-service:0.0.1-SNAPSHOT
docker build --tag webblog-service:0.0.1-SNAPSHOT .
docker build --no-cache --tag webblog-service:0.0.1-SNAPSHOT .

kubectl create -f kubernetes-deployment.yaml
```

## Docker
```
docker build --tag webblog-service:0.0.1-SNAPSHOT .
docker images | grep webblog-service

docker run -p 8080:8080 --name webblog-service --env SPRING_PROFILES_ACTIVE='default' -d webblog-service:0.0.1-SNAPSHOT

docker logs webblog-service

docker stop webblog-service
docker rm webblog-service
```

## Environment
```
export SPRING_PROFILES_ACTIVE=dev

```


## Kubernetes
```
kubectl create deployment webblog-service --image=webblog-service:0.0.1-SNAPSHOT
kubectl delete -n default deployment webblog-service

kubectl apply -f kubernetes-deployment.yaml
kubectl rollout restart deployment webblog-service

kubectl create -f kubernetes.yaml
kubectl get pods
kubectl describe pod webblog-service
kubectl describe service webblog-service

kubectl apply -f webblog-config.yaml
kubectl delete -n default configmap webblog-config
kubectl create configmap webblog-config --from-env-file=webblog-config.properties
kubectl apply -f webblog-secrets.yaml

```

## Minikube
**Use the Docker daemon in Minikube**
```
eval $(minikube docker-env)
```
Rebuild the Docker image and create or update deployment by Kubernetes *kubectl*.

```
minikube dashboard &

minikube service --all

minikube start
minikube stop

```
