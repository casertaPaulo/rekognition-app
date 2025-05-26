# Usa uma imagem base do Java
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

RUN mkdir -p /root/.aws

COPY aws/credentials /root/.aws/credentials
COPY aws/config /root/.aws/config

# Copia o arquivo JAR gerado pelo Spring Boot para dentro do contêiner
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Exposição da porta que o backend usa
EXPOSE 8080

# Comando para rodar o aplicativo
CMD ["java", "-jar", "app.jar"]
