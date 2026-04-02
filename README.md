# Contact List API + UI Automation

Estado: SOLUCIONADO — se corrigieron errores de parsing Gherkin y se añadió la opción de configurar la URL base de la API. Las pruebas pueden ejecutarse contra https://thinking-tester-contact-list.herokuapp.com o mediante una URL personalizada con la propiedad JVM -Dcontactlist.base.url.

Breve descripción del proyecto:

Proyecto de automatizacion para `Contact List App`:

- UI: https://thinking-tester-contact-list.herokuapp.com/
- API: https://documenter.getpostman.com/view/4012288/TzK2bEa8

La solucion usa Java 17, Maven, Serenity BDD, Cucumber/Gherkin, Screenplay para UI y Rest Assured para API.

## Alcance automatizado

- Registro de usuario desde UI.
- Cierre de sesion e inicio de sesion desde UI.
- Creacion de contacto desde UI y validacion en la tabla.
- Creacion de usuario desde API.
- Creacion, consulta, actualizacion parcial y eliminacion de contacto desde API.
- Validacion de que un contacto eliminado responde `404`.

## Estructura principal

```text
src/test/java/com/contactlist/automation/
|-- api
|   `-- ContactListApi.java
|-- models
|   |-- Contacto.java
|   `-- UsuarioContactList.java
|-- questions
|   `-- ContactoVisible.java
|-- runners
|   `-- ContactListTestRunner.java
|-- stepdefinitions
|   |-- ContactListApiStepDefinitions.java
|   `-- ContactListUiStepDefinitions.java
|-- tasks
|   |-- AbrirContactList.java
|   |-- CerrarSesionContactList.java
|   |-- CrearContactoUi.java
|   |-- IniciarSesionContactList.java
|   `-- RegistrarUsuarioContactList.java
|-- userinterfaces
`-- utils

src/test/resources/features/
`-- contact_list_regresion.feature

docs/
|-- defects.md
`-- test-plan.md
```

## Estrategia

El plan prioriza flujos criticos de regresion: autenticacion, creacion de datos, persistencia visible en UI y contrato basico CRUD en API. Los datos de prueba se generan dinamicamente para evitar colisiones cuando la base de datos conserva usuarios o contactos de ejecuciones previas.

La UI sigue Screenplay:

- `Tasks`: acciones del usuario.
- `Questions`: validaciones.
- `UserInterfaces`: localizadores.
- `StepDefinitions`: traduccion Gherkin a automatizacion.

La API se centraliza en `ContactListApi`, dejando los step definitions como orquestadores y validadores de estados HTTP/respuestas JSON.

## Ejecucion

Desde consola:

```bash
mvn test
```

Para generar reporte Serenity agregado:

```bash
mvn verify
```

El reporte queda en:

```text
target/site/serenity/index.html
```

## Notas

- La aplicacion puede reiniciar su base de datos periodicamente.
- Los escenarios estan etiquetados con `@ui`, `@api`, `@smoke` y `@regresion`.
- Chrome debe estar disponible localmente para ejecutar el escenario UI.

## Diagrama (Mapa mental)

El mapa mental del proyecto está en `docs/contact-list-mindmap.drawio` (formato draw.io). También disponible en Google Drive: https://drive.google.com/file/d/1v6IHIZhxluT9KFgb34urIqj-uNUV1OFL/view?usp=sharing

