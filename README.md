# Automatización de Pruebas de Integración en DynamoDB

El sistema de gestión de pedidos utiliza una tabla DynamoDB para almacenar información de pedidos. El objetivo es automatizar pruebas de integración que validen la lectura y escritura de datos en esta tabla. Los pedidos tienen atributos como ID, nombre del cliente, productos y estado. La tabla debe mantener la consistencia de los datos y manejar adecuadamente los errores de lectura y escritura.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Automatizacion de pruebas de integracion contra DynamoDB |
| **Nivel** | advanced-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Tener el proyecto base del reto funcionando en tu máquina: clonar el repositorio, instalar las herramientas y verificar que compila y arranca antes de empezar a practicar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Instala las herramientas para ejecutar el proyecto: JDK 17+ y Maven 3.9+.
- Clona el repositorio del reto (el enlace viene en la entrega del reto y en la sección "Repositorio del Proyecto" del PDF) y ábrelo en tu IDE.
- Instala las dependencias y compila ejecutando `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package && docker compose config -q` en la raíz del repositorio.
- Confirma que el comando termina sin errores: el proyecto arranca y sus pruebas corren.
- Ubica los archivos de práctica: son los que contienen `TODO(reto)` (búscalos en el repositorio). Lee el README.md y el AGENTS.md antes de empezar.

**Entregable:** El proyecto compila/arranca sin errores con `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package && docker compose config -q`.

<details>
<summary>Pistas de conocimiento</summary>

- Los archivos con `TODO(reto)` son el ejercicio: el resto del repositorio ya está resuelto y no hace falta tocarlo.
- Si el comando de verificación falla, revisa la versión de las herramientas instaladas antes de modificar código.
- AGENTS.md describe el reto, el comando de verificación y las reglas del repositorio.

</details>

### Fase 1: Configuración del Entorno de Pruebas

**Objetivo:** Configurar el entorno de pruebas con DynamoDB y Serenity BDD 4.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Configurar el acceso a DynamoDB en el entorno de pruebas.
- Instalar y configurar Serenity BDD 4 con el patrón Screenplay.
- Asegurar que las esperas sean explícitas y no se use Thread.sleep.

**Entregable:** Entorno de pruebas configurado con acceso a DynamoDB y Serenity BDD 4.

<details>
<summary>Pistas de conocimiento</summary>

- Configuración de DynamoDB en AWS.
- Uso de Serenity BDD 4 y el patrón Screenplay.

</details>

### Fase 2: Desarrollo de Pruebas de Escritura

**Objetivo:** Crear pruebas automatizadas para validar la escritura de pedidos en DynamoDB.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Desarrollar un escenario de prueba que escriba un pedido en DynamoDB.
- Verificar que el pedido persistido se puede leer correctamente.
- Asegurar que las esperas sean explícitas y no se use Thread.sleep.

**Entregable:** Pruebas automatizadas para la escritura de pedidos en DynamoDB.

<details>
<summary>Pistas de conocimiento</summary>

- Creación de escenarios de prueba con Serenity BDD 4.
- Uso de esperas explícitas en las pruebas.

</details>

### Fase 3: Desarrollo de Pruebas de Lectura

**Objetivo:** Crear pruebas automatizadas para validar la lectura de pedidos en DynamoDB.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Desarrollar un escenario de prueba que lea un pedido de DynamoDB.
- Verificar que los datos leídos coincidan con los esperados.
- Asegurar que las esperas sean explícitas y no se use Thread.sleep.

**Entregable:** Pruebas automatizadas para la lectura de pedidos en DynamoDB.

<details>
<summary>Pistas de conocimiento</summary>

- Creación de escenarios de prueba con Serenity BDD 4.
- Uso de esperas explícitas en las pruebas.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son las pruebas de integración y por qué son importantes en este contexto?
- **paraQueSirve**: ¿Para qué sirven las pruebas de integración en la validación del flujo de persistencia de pedidos?
- **comoSeUsa**: ¿Cómo se utilizan Serenity BDD 4 y el patrón Screenplay en la automatización de pruebas de integración?
- **erroresComunes**: ¿Cuáles son los errores comunes al automatizar pruebas de integración contra DynamoDB y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones implica la automatización de pruebas de integración en términos de consistencia y manejo de errores?

## Criterios de Evaluacion

- Configuración correcta del entorno de pruebas con acceso a DynamoDB y Serenity BDD 4.
- Uso de esperas explícitas en lugar de Thread.sleep.
- Desarrollo de pruebas automatizadas para la escritura de pedidos en DynamoDB.
- Desarrollo de pruebas automatizadas para la lectura de pedidos en DynamoDB.
- Verificación de que los datos leídos coincidan con los esperados.

## Mapa de fases

La **Fase 0** es clonar el repositorio, instalar dependencias y correr las pruebas de aceptacion: todas fallan porque la logica de cada pieza esta pendiente.

| Fase | Pieza (skill) | Archivo:simbolo | Criterios | Prueba | Comando |
|------|---------------|-----------------|-----------|--------|---------|
| f1 — Configuración del Entorno de Pruebas | Configuración de cliente de DynamoDB para pruebas locales | `src/main/java/config/DynamoDBLocalTestClient.java:DynamoDBLocalTestClient` | El entorno de pruebas tiene acceso a una instancia local de DynamoDB; El cliente de DynamoDB para pruebas está configurado y disponible para las pruebas | — | — |
| f2 — Desarrollo de Pruebas de Escritura | Implementación de tarea Screenplay para persistencia de pedidos | `src/main/java/tasks/PersistOrderTask.java:PersistOrderTask` | Las pruebas de escritura validan que un pedido se persiste correctamente en DynamoDB; Las pruebas manejan errores de persistencia (ej: tabla no disponible) | — | — |
| f2 — Desarrollo de Pruebas de Escritura | Implementación de pregunta Screenplay para verificación de persistencia | `src/main/java/questions/PersistOrderQuestion.java:PersistOrderQuestion` | Las pruebas de escritura validan que un pedido se persiste correctamente en DynamoDB; Las pruebas manejan errores de persistencia (ej: tabla no disponible) | — | — |
| f3 — Desarrollo de Pruebas de Lectura | Implementación de tarea Screenplay para recuperación de pedidos | `src/main/java/tasks/RetrieveOrderTask.java:RetrieveOrderTask` | Las pruebas de lectura validan que un pedido se recupera correctamente desde DynamoDB; Las pruebas manejan casos donde el pedido no existe | — | — |

## Pruebas de aceptacion

Las pruebas de aceptacion (columna "Prueba" de la tabla) son de **solo lectura**: definen que significa "listo". No las edites, no las borres y no les agregues `skip`; lo que se resuelve es el codigo de cada pieza.

## Restricciones del pedido

| Restriccion | Donde se cumple | Estado |
|-------------|-----------------|--------|
| Usar Serenity BDD 4 con el patron Screenplay (dependencia serenity en el manifiesto) | chequeo estatico (manifest): exige `serenity-core`, `serenity-screenplay` | Verificada |
| No usar Thread.sleep; las esperas deben ser explicitas | chequeo estatico (code): prohibe `Thread.sleep` | Restriccion no verificada en chequeo estatico (code): prohibe `Thread.sleep` |
| Un escenario de aceptacion verifica el item persistido en DynamoDB | sin ubicar | Restriccion no verificada en sin ubicar |

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

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package && docker compose config -q
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
