# Literalura 📚

Este proyecto fue desarrollado como parte del programa **ONE - Oracle Next Education**.  
El objetivo es practicar el uso de **Java**, **Spring Boot**, **JPA/Hibernate** y **PostgreSQL**, implementando un sistema que gestiona libros y autores.

---

## 📖 Descripción del proyecto
El sistema permite:
- Registrar libros y autores en la base de datos.
- Consultar información de libros por título, idioma, o número de descargas.
- Mostrar los autores vivos en un año determinado.
- Guardar la información obtenida desde la API externa **Gutendex** en una base de datos PostgreSQL.

---

## 🛠️ Tecnologías utilizadas
- Java 21
- Spring Boot
- Maven
- JPA / Hibernate
- PostgreSQL
- API externa: [Gutendex](https://gutendex.com/)

---

## ⚙️ Configuración del proyecto
1. Clonar este repositorio:
   ```bash
   git clone <url-del-repo>
Crear la base de datos en PostgreSQL:

sql
Copiar
Editar
CREATE DATABASE literalura;
Configurar las credenciales en src/main/resources/application.properties:

properties
Copiar
Editar
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=postgres
spring.datasource.password=sql
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
▶️ Ejecución
Para ejecutar el proyecto:

bash
Copiar
Editar
mvn spring-boot:run
o ejecutar la clase DemoApplication desde tu IDE.

📂 Estructura del proyecto
css
Copiar
Editar
src
 └── main
     ├── java/com/example/demo
     │    ├── gutendex
     │    ├── modelo
     │    ├── repository
     │    └── services
     │    
     └── resources
          └── application.properties