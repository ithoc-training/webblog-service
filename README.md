# Building

Clone the repo
```
git clone git@github.com:ithoc-training/webblog-service.git
```

Generate stubs
```
mvn clean compile
```

Run the app
```
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

Create a database
```
docker build -t webblog-db -f Dockerfile-postgres .
docker run --name webblog-db -p 5432:5432 -e POSTGRES_PASSWORD=postgres -d webblog-db
```


# Deployment

## Docker
```
docker build --tag webblog-service:0.0.1-SNAPSHOT .
docker build --no-cache --tag webblog-service:0.0.1-SNAPSHOT .

docker build --build-arg DOCKER_PROXY='' --tag webblog-service:0.0.1-SNAPSHOT .
docker build --build-arg DOCKER_PROXY='docker-proxy.mecom.de/' --tag webblog-service:0.0.1-SNAPSHOT .


docker images | grep webblog-service

docker run -p 8080:8080 --name webblog-service --env SPRING_PROFILES_ACTIVE='default' --env DB_USERNAME='username' --env DB_PASSWORD='password' -d webblog-service:0.0.1-SNAPSHOT

docker logs webblog-service

docker stop webblog-service
docker rm webblog-service
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
kubectl describe service webblog-api

kubectl apply -f webblog-config.yaml
kubectl delete -n default configmap webblog-config
kubectl create configmap webblog-config --from-env-file=webblog-config.properties
kubectl apply -f webblog-secrets.yaml

kubectl create -f kubernetes-service.yaml

```

## Minikube
**Use the Docker daemon in Minikube**
```
eval $(minikube docker-env)
minikube docker-env

minikube dashboard &

minikube service --all

minikube start
minikube stop

```
