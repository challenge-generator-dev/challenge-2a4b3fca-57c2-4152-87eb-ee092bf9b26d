# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener el proyecto completo y arrancable. Guarda los archivos que te devuelva en la
carpeta del repo, respetando exactamente las rutas.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/config/DynamoDBLocalTestClient.java` — La pieza p1 (Configuración de cliente de DynamoDB para pruebas locales, fase f1) vive aqui: este archivo es su unico dueno (blueprint v2).
- `src/main/java/tasks/PersistOrderTask.java` — La pieza p2 (Implementación de tarea Screenplay para persistencia de pedidos, fase f2) vive aqui: este archivo es su unico dueno (blueprint v2).
- `src/main/java/questions/PersistOrderQuestion.java` — La pieza p3 (Implementación de pregunta Screenplay para verificación de persistencia, fase f2) vive aqui: este archivo es su unico dueno (blueprint v2).
- `src/main/java/tasks/RetrieveOrderTask.java` — La pieza p4 (Implementación de tarea Screenplay para recuperación de pedidos, fase f3) vive aqui: este archivo es su unico dueno (blueprint v2).

## Mapa de componentes

El repo tiene una carpeta por componente. Cada uno se instala y verifica dentro de la suya.

| Componente | Carpeta | Rol | Capa | Track | Tecnologias |
|---|---|---|---|---|---|
| `app` | (raiz) | practice, chapter | testing | Automatizador | - |
| `infra-local` | (raiz) | support | infra | Compose local | DynamoDB |

## Orden de arranque

1. `docker compose up -d` (en la raiz) — levanta la infraestructura local.
2. `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package` — componente `app` (practice, chapter).

## Herramientas

- **Java**: Tecnologia pedida para este reto: forma parte del stack con el que se resuelve el ejercicio.
- **Java Selenium**: Tecnologia del perfil del candidato: el reto se resuelve con ella.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/test/java/steps/XSteps.java` — `Actor.attemptsTo`: Se invoca `attemptsTo` sobre `Actor`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package && docker compose config -q
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Calidad de Software, Especialidad Automatizador, Tecnología Java Selenium, Advanced L2

### Brecha de conocimiento
Automatizar pruebas de integracion sobre una tabla DynamoDB

### Misión / candidato
Cubrir el flujo de persistencia de pedidos

### Reto
- Tema: Automatizacion de pruebas de integracion contra DynamoDB
- Seniority: advanced-l2
- Tipo: practical
- Título: Automatización de Pruebas de Integración en DynamoDB
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Configuración del Entorno de Pruebas — objetivo: Configurar el entorno de pruebas con DynamoDB y Serenity BDD 4. — entregable (NO resolver): Entorno de pruebas configurado con acceso a DynamoDB y Serenity BDD 4.
- Fase 2: Desarrollo de Pruebas de Escritura — objetivo: Crear pruebas automatizadas para validar la escritura de pedidos en DynamoDB. — entregable (NO resolver): Pruebas automatizadas para la escritura de pedidos en DynamoDB.
- Fase 3: Desarrollo de Pruebas de Lectura — objetivo: Crear pruebas automatizadas para validar la lectura de pedidos en DynamoDB. — entregable (NO resolver): Pruebas automatizadas para la lectura de pedidos en DynamoDB.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (entrega de archivos).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Entrega de archivos
Entregá cada archivo con su ruta completa, respetando exactamente la estructura de rutas
indicada por los marcadores: un bloque por archivo, precedido por su ruta. No generes ZIPs
ni archivos adjuntos.
La entrega debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: Entregá los archivos completos al finalizar. No preguntes si el usuario
quiere recibirlos. Simplemente entregalos; No debes desplegar en el chat el resumen de lo que arreglaste, solo entregá los archivos.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del último archivo entregado
No preguntes si el usuario quiere los archivos: simplemente entregalos siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: src/test/java/steps/XSteps.java ===
package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Actor;
import model.Order;
import adapters.DynamoDBOrderRepository;
import config.DynamoDBLocalTestClient;
import ports.OrderRepository;
import questions.PersistOrderQuestion;
import tasks.PersistOrderTask;
import tasks.RetrieveOrderTask;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Step definitions de los escenarios de persistencia y lectura de pedidos.
 * Cada paso delega en una Task o Question de Screenplay: los pasos no
 * contienen logica de acceso a DynamoDB.
 */
public class XSteps {

    private static final String TABLE_NAME = "orders";

    private Actor actor;
    private Order order;
    private String orderId;
    private RetrieveOrderTask retrieveTask;

    @Given("the actor {word} wants to persist an order")
    public void the_actor_wants_to_persist_an_order(String actorName) {
        actor = Actor.named(actorName);
    }

    @Given("the order has the following details:")
    public void the_order_has_the_following_details(Map<String, String> orderDetails) {
        // Firma real de Order: (String, String, List<OrderItem>, OrderStatus, String, String).
        // Los pedidos de prueba arrancan sin items y con el primer estado del enum.
        orderId = orderDetails.get("orderId");
        order = new Order(
            orderId,
            orderDetails.get("customerId"),
            List.of(),
            Order.OrderStatus.values()[0],
            orderDetails.getOrDefault("customerName", ""),
            orderDetails.getOrDefault("currency", "")
        );
    }

    @When("the actor persists the order")
    public void the_actor_persists_the_order() {
        OrderRepository repository = repository();
        actor.attemptsTo(new PersistOrderTask(order, repository));
    }

    @Then("the order should be persisted successfully")
    public void the_order_should_be_persisted_successfully() {
        Boolean persisted = new PersistOrderQuestion(orderId, repository()).answeredBy(actor);
        assertThat(persisted).isTrue();
    }

    @When("the actor retrieves the order by id {word}")
    public void the_actor_retrieves_the_order_by_id(String idToRetrieve) {
        retrieveTask = new RetrieveOrderTask(idToRetrieve, repository());
        actor.attemptsTo(retrieveTask);
    }

    @Then("the retrieved order should match the original order")
    public void the_retrieved_order_should_match_the_original_order() {
        assertThat(retrieveTask).isNotNull();
        assertThat(retrieveTask.getRetrievedOrder()).isEqualTo(order);
    }

    private OrderRepository repository() {
        return new DynamoDBOrderRepository(
            DynamoDBLocalTestClient.getClient().getDynamoDbClient(),
            TABLE_NAME
        );
    }
}

// === ARCHIVO: src/test/java/runners/RunCucumberTest.java ===
package runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

/**
 * Punto de entrada de Cucumber sobre JUnit Platform.
 * Ejecuta los .feature de src/test/resources/features y enlaza
 * los step definitions del paquete steps.
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "steps")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
public class RunCucumberTest {
}

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.13</version>
        <relativePath/>
    </parent>

    <groupId>com.example</groupId>
    <artifactId>reto</artifactId>
    <version>1.0.0</version>
    <name>reto</name>

    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>8.1.0</version>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit-platform-engine</artifactId>
            <version>8.1.0</version>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-core</artifactId>
            <version>4.3.4</version>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-cucumber</artifactId>
            <version>4.3.4</version>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-junit5</artifactId>
            <version>4.3.4</version>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-screenplay</artifactId>
            <version>4.3.4</version>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-screenplay-webdriver</artifactId>
            <version>4.3.4</version>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>localstack</artifactId>
        </dependency>
        <dependency>
            <groupId>software.amazon.awssdk</groupId>
            <artifactId>dynamodb</artifactId>
            <version>2.59.9</version>
        </dependency>
        <dependency>
            <groupId>org.junit.platform</groupId>
            <artifactId>junit-platform-suite-api</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/model/Order.java ===
package model;

import java.util.List;
import java.util.Objects;

public record Order(
    String orderId,
    String customerName,
    List<OrderItem> items,
    OrderStatus status,
    String createdAt,
    String updatedAt) {

    public Order {
        Objects.requireNonNull(orderId, "Order ID cannot be null");
        Objects.requireNonNull(customerName, "Customer name cannot be null");
        Objects.requireNonNull(items, "Items list cannot be null");
        Objects.requireNonNull(status, "Order status cannot be null");
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }
    }

    public double calculateTotal() {
        return items.stream()
                .mapToDouble(OrderItem::getPrice)
                .sum();
    }

    public enum OrderStatus {
        PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED
    }

    public static class OrderItem {
        private final String productId;
        private final String productName;
        private final int quantity;
        private final double price;

        public OrderItem(String productId, String productName, int quantity, double price) {
            this.productId = Objects.requireNonNull(productId, "Product ID cannot be null");
            this.productName = Objects.requireNonNull(productName, "Product name cannot be null");
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be positive");
            }
            if (price <= 0) {
                throw new IllegalArgumentException("Price must be positive");
            }
            this.quantity = quantity;
            this.price = price;
        }

        public String getProductId() {
            return productId;
        }

        public String getProductName() {
            return productName;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getPrice() {
            return price * quantity;
        }
    }
}

// === ARCHIVO: src/main/java/ports/OrderRepository.java ===
package ports;

import model.Order;
import java.util.Optional;

public interface OrderRepository {
    void save(Order order);
    Optional<Order> findById(String orderId);
    void delete(String orderId);
}

// === ARCHIVO: src/main/java/adapters/DynamoDBOrderRepository.java ===
package adapters;

import model.Order;
import ports.OrderRepository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DynamoDBOrderRepository implements OrderRepository {
    private final DynamoDbClient dynamoDbClient;
    private final String tableName;

    public DynamoDBOrderRepository(DynamoDbClient dynamoDbClient, String tableName) {
        this.dynamoDbClient = dynamoDbClient;
        this.tableName = tableName;
    }

    @Override
    public void save(Order order) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("orderId", AttributeValue.builder().s(order.orderId()).build());
        item.put("customerName", AttributeValue.builder().s(order.customerName()).build());
        item.put("status", AttributeValue.builder().s(order.status().name()).build());
        item.put("createdAt", AttributeValue.builder().s(order.createdAt()).build());
        item.put("updatedAt", AttributeValue.builder().s(order.updatedAt()).build());

        String itemsList = order.items().stream()
                .map(itemObj -> String.format("%s|%s|%d|%.2f",
                        itemObj.getProductId(),
                        itemObj.getProductName(),
                        itemObj.getQuantity(),
                        itemObj.getPrice()))
                .collect(Collectors.joining(","));

        item.put("items", AttributeValue.builder().s(itemsList).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();

        try {
            dynamoDbClient.putItem(request);
        } catch (DynamoDbException e) {
            throw new RuntimeException("Failed to save order to DynamoDB", e);
        }
    }

    @Override
    public Optional<Order> findById(String orderId) {
        GetItemRequest request = GetItemRequest.builder()
                .tableName(tableName)
                .key(Map.of("orderId", AttributeValue.builder().s(orderId).build()))
                .build();

        try {
            GetItemResponse response = dynamoDbClient.getItem(request);
            if (!response.hasItem()) {
                return Optional.empty();
            }

            Map<String, AttributeValue> item = response.item();
            String customerName = item.get("customerName").s();
            Order.OrderStatus status = Order.OrderStatus.valueOf(item.get("status").s());
            String createdAt = item.get("createdAt").s();
            String updatedAt = item.get("updatedAt").s();

            String[] itemsArray = item.get("items").s().split(",");
            var items = java.util.Arrays.stream(itemsArray)
                    .map(itemStr -> {
                        String[] parts = itemStr.split("\\|");
                        return new Order.OrderItem(
                                parts[0],
                                parts[1],
                                Integer.parseInt(parts[2]),
                                Double.parseDouble(parts[3])
                        );
                    })
                    .collect(Collectors.toList());

            Order order = new Order(orderId, customerName, items, status, createdAt, updatedAt);
            return Optional.of(order);
        } catch (DynamoDbException e) {
            throw new RuntimeException("Failed to retrieve order from DynamoDB", e);
        }
    }

    @Override
    public void delete(String orderId) {
        DeleteItemRequest request = DeleteItemRequest.builder()
                .tableName(tableName)
                .key(Map.of("orderId", AttributeValue.builder().s(orderId).build()))
                .build();

        try {
            dynamoDbClient.deleteItem(request);
        } catch (DynamoDbException e) {
            throw new RuntimeException("Failed to delete order from DynamoDB", e);
        }
    }
}

// === ARCHIVO: src/main/java/model/Actor.java ===
package model;

import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

// Actor de automatización del dominio de pruebas. Extiende el actor de Screenplay
// y recibe su capacidad de navegación desde un WebDriver ya creado por el proyecto.
public class Actor extends net.serenitybdd.screenplay.Actor {

    private WebDriver browser;

    public Actor(String name) {
        super(Objects.requireNonNull(name, "El nombre del actor no puede ser nulo"));
    }

    public static Actor named(String name) {
        return new Actor(name);
    }

    public static ActorBuilder builder() {
        return new ActorBuilder();
    }

    // Asocia el navegador al actor y le concede la habilidad de navegar la web.
    public Actor withBrowser(WebDriver driver) {
        this.browser = Objects.requireNonNull(driver, "El WebDriver no puede ser nulo");
        this.whoCan(BrowseTheWeb.with(driver));
        return this;
    }

    public WebDriver getBrowser() {
        return browser;
    }

    public void initializeBrowser() {
        if (browser == null) {
            throw new IllegalStateException(
                    "WebDriver no inicializado para el actor " + getName() + ". Llama a withBrowser(...) primero.");
        }
    }

    public void closeBrowser() {
        if (browser != null) {
            browser.quit();
            browser = null;
        }
    }

    public static class ActorBuilder {
        private String name;

        public ActorBuilder withName(String name) {
            this.name = Objects.requireNonNull(name, "El nombre del actor no puede ser nulo");
            return this;
        }

        public Actor build() {
            if (name == null) {
                throw new IllegalStateException("Se requiere un nombre antes de construir el actor");
            }
            return new Actor(name);
        }
    }
}

// === ARCHIVO: src/main/java/config/DynamoDBLocalTestClient.java ===
// TODO(reto): archivo stubbeado; implementa los cuerpos.
package config;

import model.Order;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;

import java.net.URI;
import java.util.Map;

public class DynamoDBLocalTestClient {

    private static final String LOCALSTACK_ENDPOINT = System.getenv().getOrDefault("DYNAMODB_ENDPOINT", "http://localhost:4566");
    private static final String REGION = System.getenv().getOrDefault("AWS_REGION", "us-east-1");

    private final DynamoDbClient client;

    public DynamoDBLocalTestClient() {
        this.client = createLocalClient();
    }

    private DynamoDbClient createLocalClient() {
      throw new UnsupportedOperationException("TODO(reto): createLocalClient");
    }

    public static DynamoDBLocalTestClient getClient() {
      throw new UnsupportedOperationException("TODO(reto): getClient");
    }

    public void saveOrder(Order order) {
      throw new UnsupportedOperationException("TODO(reto): saveOrder");
    }

    public Order getOrder(String orderId) {
      throw new UnsupportedOperationException("TODO(reto): getOrder");
    }

    public void deleteOrder(String orderId) {
      throw new UnsupportedOperationException("TODO(reto): deleteOrder");
    }

    public DynamoDbClient getDynamoDbClient() {
        return client;
    }

    public static class DynamoDBLocalNotAvailableException extends RuntimeException {
        public DynamoDBLocalNotAvailableException(String message) {
            super(message);
        }

        public DynamoDBLocalNotAvailableException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}

// === ARCHIVO: src/main/java/tasks/PersistOrderTask.java ===
// TODO(reto): archivo stubbeado; implementa los cuerpos.
package tasks;

import model.Order;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Actor;
import ports.OrderRepository;

public class PersistOrderTask implements Task {

    private final Order order;
    private final OrderRepository orderRepository;

    public PersistOrderTask(Order order, OrderRepository orderRepository) {
        this.order = order;
        this.orderRepository = orderRepository;
    }

    public static PersistOrderTaskBuilder withOrder(Order order) {
      throw new UnsupportedOperationException("TODO(reto): withOrder");
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
      throw new UnsupportedOperationException("TODO(reto): performAs");
    }

    public static class PersistOrderTaskBuilder {
        private final Order order;
        private OrderRepository orderRepository;

        public PersistOrderTaskBuilder(Order order) {
            this.order = order;
        }

        public PersistOrderTaskBuilder usingRepository(OrderRepository repository) {
            this.orderRepository = repository;
            return this;
        }

        public PersistOrderTask build() {
            if (orderRepository == null) {
                throw new IllegalStateException("OrderRepository no puede ser null");
            }
            return new PersistOrderTask(order, orderRepository);
        }
    }

    public static class OrderPersistenceException extends RuntimeException {
        public OrderPersistenceException(String message) {
            super(message);
        }

        public OrderPersistenceException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}

// === ARCHIVO: src/main/java/questions/PersistOrderQuestion.java ===
// TODO(reto): archivo stubbeado; implementa los cuerpos.
package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import ports.OrderRepository;

import java.time.Duration;

public class PersistOrderQuestion implements Question<Boolean> {

    private final String orderId;
    private final OrderRepository orderRepository;
    private final int maxRetries;
    private final Duration retryInterval;

    public PersistOrderQuestion(String orderId, OrderRepository orderRepository) {
        this.orderId = orderId;
        this.orderRepository = orderRepository;
        this.maxRetries = 5;
        this.retryInterval = Duration.ofMillis(500);
    }

    public static PersistOrderQuestionBuilder hasOrderPersisted(String orderId) {
      throw new UnsupportedOperationException("TODO(reto): hasOrderPersisted");
    }

    @Override
    public Boolean answeredBy(Actor actor) {
      throw new UnsupportedOperationException("TODO(reto): answeredBy");
    }

    public static class PersistOrderQuestionBuilder {
        private final String orderId;
        private OrderRepository orderRepository;
        private int maxRetries = 5;
        private Duration retryInterval = Duration.ofMillis(500);

        public PersistOrderQuestionBuilder(String orderId) {
            this.orderId = orderId;
        }

        public PersistOrderQuestionBuilder usingRepository(OrderRepository repository) {
            this.orderRepository = repository;
            return this;
        }

        public PersistOrderQuestionBuilder withMaxRetries(int retries) {
            this.maxRetries = retries;
            return this;
        }

        public PersistOrderQuestionBuilder withRetryInterval(Duration interval) {
            this.retryInterval = interval;
            return this;
        }

        public PersistOrderQuestion build() {
            if (orderRepository == null) {
                throw new IllegalStateException("OrderRepository no puede ser null");
            }
            return new PersistOrderQuestion(orderId, orderRepository);
        }
    }

    public static class OrderQueryException extends RuntimeException {
        public OrderQueryException(String message) {
            super(message);
        }

        public OrderQueryException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}

// === ARCHIVO: src/main/java/tasks/RetrieveOrderTask.java ===
// TODO(reto): archivo stubbeado; implementa los cuerpos.
package tasks;

import model.Order;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Actor;
import ports.OrderRepository;

public class RetrieveOrderTask implements Task {

    private final String orderId;
    private final OrderRepository orderRepository;
    private Order retrievedOrder;

    public RetrieveOrderTask(String orderId, OrderRepository orderRepository) {
        this.orderId = orderId;
        this.orderRepository = orderRepository;
    }

    public static RetrieveOrderTaskBuilder withOrderId(String orderId) {
      throw new UnsupportedOperationException("TODO(reto): withOrderId");
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
      throw new UnsupportedOperationException("TODO(reto): performAs");
    }

    public Order getRetrievedOrder() {
        return retrievedOrder;
    }

    public static class RetrieveOrderTaskBuilder {
        private final String orderId;
        private OrderRepository orderRepository;

        public RetrieveOrderTaskBuilder(String orderId) {
            this.orderId = orderId;
        }

        public RetrieveOrderTaskBuilder usingRepository(OrderRepository repository) {
            this.orderRepository = repository;
            return this;
        }

        public RetrieveOrderTask build() {
            if (orderRepository == null) {
                throw new IllegalStateException("OrderRepository no puede ser null");
            }
            return new RetrieveOrderTask(orderId, orderRepository);
        }
    }

    public static class OrderNotFoundException extends RuntimeException {
        public OrderNotFoundException(String message) {
            super(message);
        }
    }

    public static class OrderRetrievalException extends RuntimeException {
        public OrderRetrievalException(String message) {
            super(message);
        }

        public OrderRetrievalException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}

// === ARCHIVO: docker-compose.yml ===
# Infraestructura local del reto. SOLO desarrollo: credenciales de ejemplo (local-dev-only), puertos en loopback.
# Levantar: docker compose up -d --wait
name: infra-local
services:
  dynamodb-local:
    image: "amazon/dynamodb-local:2.5.2"
    command: ["-jar","DynamoDBLocal.jar","-sharedDb","-inMemory"]
    ports:
      - "127.0.0.1:8000:8000"
    healthcheck:
      test: ["CMD-SHELL","bash -c ':> /dev/tcp/127.0.0.1/8000' || exit 1"]
      interval: 10s
      timeout: 5s
      retries: 10
      start_period: 5s


// === ARCHIVO: infra-local/README.md ===
# Infraestructura local

Servicios de soporte para el reto, definidos en `docker-compose.yml` (raiz del repo).

```bash
docker compose up -d --wait
```

| Servicio | Imagen | Puertos (host) |
|---|---|---|
| DynamoDB Local | `amazon/dynamodb-local:2.5.2` | 8000 |

Credenciales: solo de desarrollo local (`local-dev-only`, `local`). No son secretos ni deben reutilizarse.
Los scripts de `infra-local/` (tablas, topics) se ejecutan con el compose en estado healthy.


// === ARCHIVO: infra-local/dynamodb/create-tables.sh ===
#!/usr/bin/env bash
# Crea las tablas en DynamoDB Local (idempotente). Credenciales dummy: DynamoDB Local no valida firmas.
set -euo pipefail

export AWS_ACCESS_KEY_ID=local
export AWS_SECRET_ACCESS_KEY=local
export AWS_REGION=us-east-1
ENDPOINT="${DYNAMODB_ENDPOINT:-http://localhost:8000}"

create_table() {
  local name="$1" pk="$2" sk="${3:-}"
  if aws dynamodb describe-table --table-name "$name" --endpoint-url "$ENDPOINT" >/dev/null 2>&1; then
    echo "Tabla $name ya existe"
    return
  fi
  local attrs="AttributeName=$pk,AttributeType=S"
  local keys="AttributeName=$pk,KeyType=HASH"
  if [ -n "$sk" ]; then
    attrs="$attrs AttributeName=$sk,AttributeType=S"
    keys="$keys AttributeName=$sk,KeyType=RANGE"
  fi
  # shellcheck disable=SC2086
  aws dynamodb create-table --table-name "$name" --attribute-definitions $attrs --key-schema $keys \
    --billing-mode PAY_PER_REQUEST --endpoint-url "$ENDPOINT" >/dev/null
  echo "Tabla $name creada"
}

create_table "app-items" "id"

```
