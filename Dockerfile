# Usar uma imagem oficial do OpenJDK (Java 17)
FROM openjdk:17-jdk-alpine

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o arquivo JAR gerado pelo Maven para dentro do container
COPY target/*.jar app.jar

# Definir a variável de ambiente para o Spring Boot usar a porta do Render
ENV PORT=8080

# Expor a porta 8080 para o Render
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["java", "-jar", "app.jar"]
