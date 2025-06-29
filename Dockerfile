# Usa una imagen con Maven y JDK
FROM maven:3.9.4-eclipse-temurin-17 as build
WORKDIR /app

# Copia todo el proyecto y construye el JAR
COPY . .
RUN mvn clean package -DskipTests

# Segunda etapa: ejecuta la app
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
