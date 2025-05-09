# Usar uma imagem do OpenJDK (Java 17)
FROM maven:3.9.3-openjdk-17 AS build

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o código fonte para dentro do container
COPY . .

# Compilar o projeto e gerar o JAR
RUN ./mvnw clean package -DskipTests

# Segunda etapa - Usar a imagem do OpenJDK para rodar o app
FROM openjdk:17-jdk-alpine
WORKDIR /app

# Copiar o JAR gerado na etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Definir a variável de ambiente para a porta (Render.com)
ENV PORT=8080

# Expor a porta 8080 para o Render
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["java", "-jar", "app.jar"]
