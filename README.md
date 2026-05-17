# EasyBroker Integration API

![CI Pipeline](https://github.com/toledo96/EasyBroker-Integration/actions/workflows/ci.yml/badge.svg)

Proyecto en Spring Boot 3.5.x que consume la API de EasyBroker para obtener propiedades, agentes, contactos y desarrollos. Incluye pruebas unitarias y de integración, documentación con Swagger y pipeline CI/CD en GitHub Actions.

## ⚡ Concurrencia con CompletableFuture
Este proyecto utiliza `CompletableFuture` para realizar llamadas concurrentes a la API de EasyBroker.  
De esta forma se obtienen datos de propiedades, agentes y contactos en paralelo, mejorando el rendimiento y reduciendo tiempos de respuesta.

Ejemplo simplificado:

```
        CompletableFuture<List<PropertyDTO>> propertiesFuture =
                CompletableFuture.supplyAsync(() -> client.getProperties(1));

        CompletableFuture<List<ContactDTO>>  contactsFuture =
                CompletableFuture.supplyAsync(() -> client.getContacts());

        CompletableFuture<LocationDTO> locationFuture =
                CompletableFuture.supplyAsync(() -> client.getLocations());

        CompletableFuture.allOf(propertiesFuture,contactsFuture,locationFuture);

        CombinedResponse response = new CombinedResponse();
        response.setProperties(propertiesFuture.join());
        response.setContacts(contactsFuture.join());
        response.setLocation(locationFuture.join());

        return response;
```

## 🚀 Tecnologías y características
- Java 17
- Spring Boot 3.5.x
- Spring WebClient para consumo de API externa (EasyBroker)
- JPA/Hibernate + PostgreSQL
- Swagger/OpenAPI para documentación
- JUnit + Mockito + MockMvc para pruebas
- H2 en memoria para tests
- **Uso de CompletableFuture para llamadas concurrentes a la API de EasyBroker**

## ⚙️ Instalación local

Clonar el repositorio:

    git clone https://github.com/toledo96/EasyBroker-Integration.git
    cd EasyBroker-Integration

Configurar variables de entorno:

    DB_URL=jdbc:postgresql://localhost:5432/easybroker
    DB_USERNAME=postgres
    DB_PASSWORD=admin
    EASY_URL=https://api.easybroker.com/v1
    EASY_KEY=tu_api_key

Ejecutar:
    
    ./mvnw spring-boot:run

## 📌 Endpoints principales

- GET /api/v1/properties → Lista de propiedades desde EasyBroker

- GET /api/v1/agents → Lista de agentes

- GET /api/v1/contacts → Lista de contactos

- GET /api/v1/developments → Lista de desarrollos

- GET /api/v1/locations → Ubicaciones

## 🔄 CI/CD
Este proyecto incluye un pipeline en GitHub Actions que:
- Compila el proyecto
- Ejecuta los tests con perfil `test`
- Genera reporte de cobertura con Jacoco


## 🧪 Tests

Ejecutar:

    ./mvnw test -Dspring.profiles.active=test

##  📖 Documentación

    http://localhost:8080/swagger-ui.html



## 📜 Licencia
Este proyecto está bajo la licencia MIT.







