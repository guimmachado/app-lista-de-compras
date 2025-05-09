# Etapa de Build - Maven com OpenJDK 17
FROM maven:3.9.3-eclipse-temurin-17 AS build

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o código fonte e o Maven Wrapper (se existir)
COPY . .

# Garantir que o Maven baixe todas as dependências necessárias
RUN mvn -B -f pom.xml clean package -DskipTests

# Segunda etapa - Usar uma imagem leve do OpenJDK para rodar o app
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
