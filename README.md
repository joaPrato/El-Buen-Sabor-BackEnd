🍕 El Buen Sabor - Backend
Sistema de gestión para restaurante desarrollado con Spring Boot, que permite la administración completa de pedidos, clientes, productos e inventario.

🚀 Tecnologías Utilizadas
Java 17
Spring Boot 3.x
Spring Security (JWT Authentication)
Spring Data JPA
MySQL 8.0
Gradle 8.5
Docker & Docker Compose
Lombok
🏗️ Arquitectura
Patrón MVC (Model-View-Controller)
Arquitectura en capas: Controllers, Services, Repositories
Autenticación JWT con roles y permisos
API RESTful con endpoints seguros
Base de datos relacional con JPA/Hibernate
👥 Roles de Usuario
🔧 ADMINISTRADOR: Acceso completo al sistema
👨‍🍳 COCINERO: Gestión de ingredientes y recetas
👤 CLIENTE: Gestión de perfil y pedidos personales

📋 Funcionalidades Principales
🔐 Autenticación y Autorización
Login/Register con JWT
Control de acceso basado en roles
Middleware de autenticación personalizado
👥 Gestión de Clientes
CRUD completo de clientes
Gestión de domicilios
Ranking de mejores clientes
Modificación de datos personales
🍽️ Gestión de Productos
Catálogo de productos
Categorización por rubros
Control de disponibilidad
📦 Gestión de Inventario
Control de ingredientes
Unidades de medida
Stock y disponibilidad
🧾 Gestión de Pedidos
Creación y seguimiento de pedidos
Facturación automática
Historial completo
👨‍💼 Gestión de Empleados
Administración del personal
Asignación de roles
🛠️ Instalación y Configuración
Prerrequisitos
Docker & Docker Compose
Java 17 (solo para desarrollo local)
MySQL 8.0 (si no usas Docker)
🐳 Instalación con Docker (Recomendado)
Clonar el repositorio
bash
git clone https://github.com/joaPrato/El-Buen-Sabor-BackEnd.git
cd El-Buen-Sabor-BackEnd
Descomentar BuenSaborApplication seccion INITIALIZATION DE DATOS (solo la 1era vez que se inicializa el proyecto con docker)
Levantar los servicios
bash
docker-compose up --build
La aplicación estará disponible en:
Backend: http://localhost:8080
Base de datos: localhost:3306
💻 Instalación Local
Configurar base de datos MySQL
sql
CREATE DATABASE buensabor;
CREATE USER 'buensabor'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON buensabor.* TO 'buensabor'@'localhost';
Configurar application.properties
properties
spring.datasource.url=jdbc:mysql://localhost:3306/buensabor
spring.datasource.username=buensabor
spring.datasource.password=password
Ejecutar la aplicación
bash
./gradlew bootRun
🔧 Configuración
Variables de Entorno
bash
# Base de datos
DB_HOST=localhost
DB_PORT=3306
DB_NAME=buensabor
DB_USER=buensabor
DB_PASSWORD=password

📡 API Endpoints
🔐 Autenticación
POST /auth/login       - Iniciar sesión
POST /auth/register    - Registrar usuario
👥 Clientes
GET    /api/v1/clientes                    - Listar clientes (ADMIN)
GET    /api/v1/clientes/buscarCliente      - Buscar cliente por username
PUT    /api/v1/clientes/modificarCliente   - Modificar datos del cliente
GET    /api/v1/clientes/buscarDomiciliosCliente - Obtener domicilios
PUT    /api/v1/clientes/agregarDomicilioCliente - Agregar domicilio
🍽️ Productos
GET    /api/v1/productos          - Listar productos (ADMIN)
GET    /api/v1/rubroProductos/searchRubrosProdDisponibles - Rubros disponibles (PÚBLICO)
📦 Ingredientes
GET    /api/v1/ingredientes       - Listar ingredientes (COCINERO/ADMIN)
GET    /api/v1/rubroIngredientes  - Rubros de ingredientes (COCINERO/ADMIN)
GET    /api/v1/UnidadMedida       - Unidades de medida (COCINERO/ADMIN)
🔒 Seguridad
JWT Tokens con expiración de 1 hora
CORS configurado para frontend
Endpoints protegidos por rol
Passwords encriptados con BCrypt
Validación de entrada en todos los endpoints

📦 Docker
Desarrollo
bash
# Reconstruir y levantar
docker-compose up --build

# Solo reconstruir backend
docker-compose build --no-cache buensabor-app

# Ver logs
docker-compose logs -f buensabor-app
Producción
bash
# Reset completo (incluye base de datos)
docker-compose down -v
docker-compose up --build
