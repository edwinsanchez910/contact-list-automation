# Plan de pruebas - Contact List App

## Objetivo

Validar por regresion los flujos criticos de autenticacion y gestion de contactos en UI y API.

## Criterios usados para priorizar

- Funcionalidades core del negocio: usuarios y contactos.
- Riesgo alto de integracion: autenticacion requerida para operar contactos.
- Cobertura API + UI para detectar fallos de contrato y fallos de experiencia.
- Datos dinamicos para mitigar inconsistencias por reinicio o persistencia de base de datos.

## Casos de prueba

| ID | Capa | Caso | Precondicion | Pasos resumidos | Resultado esperado | Estado |
| --- | --- | --- | --- | --- | --- | --- |
| CT-001 | UI | Registro, login y creacion de contacto | Aplicacion disponible | Abrir app, crear usuario, cerrar sesion, iniciar sesion, crear contacto | El contacto aparece en la tabla | Automatizado |
| CT-002 | API | Crear usuario | API disponible | Enviar `POST /users` con usuario valido | Respuesta `201` y token no nulo | Automatizado |
| CT-003 | API | Crear contacto autenticado | Usuario creado y token valido | Enviar `POST /contacts` con contacto valido | Respuesta `201` e `_id` no nulo | Automatizado |
| CT-004 | API | Consultar contacto | Contacto existente | Enviar `GET /contacts/{id}` | Respuesta `200` con nombre y apellido esperados | Automatizado |
| CT-005 | API | Actualizar telefono de contacto | Contacto existente | Enviar `PATCH /contacts/{id}` con telefono nuevo | Respuesta `200`; consulta posterior retorna telefono nuevo | Automatizado |
| CT-006 | API | Eliminar contacto | Contacto existente | Enviar `DELETE /contacts/{id}` y consultar de nuevo | Delete responde `200`; consulta posterior responde `404` | Automatizado |

## Casos recomendados para ampliar

| ID | Capa | Caso | Resultado esperado |
| --- | --- | --- | --- |
| CT-007 | UI | Login con password incorrecto | Se muestra mensaje de error y no permite entrar |
| CT-008 | UI | Validacion de campos obligatorios al crear contacto | No se crea contacto sin campos requeridos |
| CT-009 | API | Crear contacto sin token | Respuesta `401` |
| CT-010 | API | Crear usuario con email ya existente | Respuesta de error controlada |
| CT-011 | API | Actualizacion completa con `PUT /contacts/{id}` | Todos los campos quedan actualizados |

## Evidencia

La evidencia tecnica se obtiene del reporte Serenity generado en `target/site/serenity/index.html`.
