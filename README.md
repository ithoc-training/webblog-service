

# Deployment

## Local
**Recipe**:
```
docker rmi webblog-service:0.0.1-SNAPSHOT
eval $(minikube docker-env)
docker build --tag webblog-service:0.0.1-SNAPSHOT .
kubectl create -f kubernetes.yaml
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

```

## Minikube
**Use the Docker daemon in Minikube**
```
eval $(minikube docker-env)
```
Rebuild the Docker image and create or update deployment by Kubernetes *kubectl*.

```
minikube dashboard &

minikube start
minikube stop

```
