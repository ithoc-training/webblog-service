
# Shall be done through .bashrc
eval $(minikube docker-env)
minikube start

# Optional removal of old stuff
kubectl delete -n default deployment webblog-service
docker rmi --force webblog-service:0.0.1-SNAPSHOT

# Maybe package goal is sufficient
mvn clean install

#docker build --build-arg DOCKER_PROXY='docker-proxy.mecom.de/' --tag webblog-service:0.0.1-SNAPSHOT .
#docker images | grep webblog-service

# Build Docker image from and load it directly to Minikube
minikube image build -t webblog-service:0.0.1-SNAPSHOT .

kubectl apply -f kubernetes-deployment.yaml
minikube service --all &
