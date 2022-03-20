FROM openjdk:8
#Comando a cima, será baixado o openjdk java 8 do Docker hub
COPY target/springbootJPAK8s-0.0.1-SNAPSHOT.jar /app.jar  
#Comando a cima, copia o jar gerado pelo mvn para dentro da imagem, e renomeia para app.jar
EXPOSE 8080/tcp
#Comando a cima, expondo a porta 8080 para acesso
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/app.jar"]
#Comando a cima, rodando o jar dentro do container depois de todas as configurações e instalações
