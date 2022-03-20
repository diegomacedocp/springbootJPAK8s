# spring-boot-jpa-k8s

Esta demonstração simples de um aplicativo Web Spring Boot que em produção se conecta ao Postgres dentro de um cluster Kubernetes e em desenvolvimento no Spring Tools se conecta ao banco de dados H2.

## Implantando o aplicativo Spring Boot e o Postgres no Kubernetes
1. Implantando o postgres com volume persistente (PersistentVolumeClaim)

   ```
   kubectl create -f specs/postgres.yml   
   ```

2. Criando um mapa de configuração com o nome hostname-config do Postgres e atribuindo o ip do clusterI

   ```
   kubectl create configmap hostname-config --from-literal=postgres_host=$(kubectl get svc postgres -o jsonpath="{.spec.clusterIP}")   
   ```

3. Compilando a aplicação Spring Boot JPA K8s

   ```
   ./mvnw -DskipTests package
   ```

4. Criando imagem Docker e enviando para o Docker Hub OBS: Deve estar logando no Docker Hub 

   ```
   docker build -t <your Docker Hub account>/spring-boot-postgres-on-k8s:v1 .
   
   docker push <your Docker Hub account>/spring-boot-postgres-on-k8s:v1
   ```   
   
   Ex:
   
   ```
   docker build -t diegomacedocp/spring-boot-jpa-k8s:v1 .
   
   docker push diegomacedocp/spring-boot-jpa-k8s:v1
   ```

5. Tire o código comentando e altere `<sua conta do Docker Hub>` para sua conta do Docker Hub no arquivo `specs/spring-boot-app.yml`, para fazer o deplou de sua aplicação em sua conta do Docker Hub

   ```
   kubectl create -f specs/spring-boot-app.yml
   ```

6. Crie um load balancer para seu aplicativo expondo na porta 8080 a aplicação

   ```
   kubectl expose deployment spring-boot-jpa-k8s --type=LoadBalancer --port=8080
   ```
   

7. Obtenha o endereço IP externo do serviço, então o aplicativo estará acessível em `http://<Endereço ip>:8080`

   ```
   kubectl get [svc ou service] spring-boot-jpa-k8s
   ```
   
   OBS: Se estiver usando o minikube usar:

   ```
   minikube service spring-boot-jpa-k8s --url
   ```   
   
   
   > **Observação:** pode levar alguns minutos para que o load balance seja criado

8. Escalando quantas replicas você quer replicar na sua aplicação

   ```
   kubectl scale deployment spring-boot-jpa-k8s --replicas=3
   ```

## Atualizando o aplicativo
1. Atualize a imagem que os pods com os contêineres em sua implantação estão usando

   ```
   kubectl set image deployment/spring-boot-jpa-k8s spring-boot-jpa-k8s=<your Docker Hub account>/spring-boot-postgres-on-k8s:v2
   ```
   
   Ex:
   
   ```
   kubectl set image deployment/spring-boot-jpa-k8s spring-boot-jpa-k8s=diegomacedocp/spring-boot-jpa-k8s:v2
   ```
   
## Desfazendo atualização do aplicativo
1. Desfazendo para imagem anterior

   ```
   kubectl rollout undo deployment/spring-boot-jpa-k8s
   ```

## Verificando mudanças
1. Vendo historico de mudanças

   ```
   kubectl rollout history deployment/spring-boot-jpa-k8s
   ```
   
## Para deletar o que você fez
1. Excluindo os deployments do app, via yml criado

   ```
   kubectl delete -f specs/spring-boot-app.yml
   ```

2. Excluindo os serviços do app

   ```
   kubectl delete [svc ou service] spring-boot-jpa-k8s
   ```

3. Excluindo o ConfigMap com o nome hostname-config

   ```
   kubectl delete [cm ou configmap] hostname-config
   ```

4. Excluindo todas as configurações do Postgres via yml criada

   ```
   kubectl delete -f specs/postgres.yml
   ```
