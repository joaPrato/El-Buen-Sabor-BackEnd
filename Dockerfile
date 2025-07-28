# =============================================================================
# ETAPA 1: BUILD (Compilar la aplicación)
# =============================================================================

# 📦 Usamos una imagen que YA TIENE Java + Gradle instalados
FROM gradle:8.5-jdk17 AS build

# 📁 WORKDIR = "cambiar a esta carpeta" (como 'cd /app' en terminal)
WORKDIR /app

# 📋 Copiamos TODOS los archivos del proyecto a la carpeta /app del contenedor
COPY . .

# 🔨 Compilamos la aplicación usando Gradle
# - clean: borra compilaciones anteriores
# - build: compila y empaqueta en un JAR
# - -x test: salta los tests para que sea más rápido
RUN gradle clean build -x test

# =============================================================================
# ETAPA 2: RUNTIME (Ejecutar la aplicación)
# =============================================================================

# 📦 Nueva imagen más liviana, solo con Java (sin Gradle)
FROM openjdk:17-jdk-slim

# 🏥 Instalamos curl para verificar si la app está funcionando
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# 👤 Creamos un usuario que NO sea root (más seguro)
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

# 📁 Cambiamos a la carpeta /app
WORKDIR /app

# 📥 Copiamos el JAR desde la ETAPA 1 (build) a esta etapa
# --from=build: toma archivos de la etapa anterior
# /app/build/libs/*.jar: cualquier JAR en esa carpeta
# app.jar: lo renombramos a "app.jar"
COPY --from=build /app/build/libs/*.jar app.jar

# 🚪 Le decimos a Docker que nuestra app usa el puerto 8080
EXPOSE 8080

# 🧠 Variables para la memoria de Java
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# 🏥 HEALTHCHECK: Docker verificará cada 30s si la app está funcionando
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# 🚀 ENTRYPOINT: el comando que ejecuta Docker al iniciar el contenedor
# sh -c: ejecuta el comando en una shell
# java $JAVA_OPTS -jar app.jar: inicia la aplicación Java
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]