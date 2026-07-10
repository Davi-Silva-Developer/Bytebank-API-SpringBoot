
# ETAPA 1: Compilação (Build)

#  imagem oficial do Java 21 com o JDK para compilar o código
FROM eclipse-temurin:21-jdk-jammy AS build

# Define a pasta de trabalho dentro do contêiner
WORKDIR /app

# Copia todos os arquivos do projeto para dentro do contêiner
COPY . .

# Executa o Maven para compilar o projeto e gerar o .jar (pulando os testes para acelerar)
RUN ./mvnw clean package -DskipTests



#Execução (Run)

#imagem apenas com o JRE (sem ferramentas de compilação)
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copia o arquivo .jar gerado  para esta nova imagem limpa
COPY --from=build /app/target/*.jar app.jar

# Informa que a aplicação roda na porta 8080
EXPOSE 8080

# Comando para iniciar a API
ENTRYPOINT ["java", "-jar", "app.jar"]