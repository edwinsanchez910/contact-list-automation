# language: es
@regresion
Característica: Gestion de usuarios y contactos en Contact List
  Como QA de automatizacion
  Quiero validar los flujos criticos de UI y API
  Para soportar una regresion confiable de la aplicacion Contact List

  @api @registro @happy_path
  Escenario: Happy Path - Registro exitoso de usuario
    Cuando registra por API el usuario con firstName "Edwin", lastName "Sanchez", email base "noxos001@hotmail.es" y password "Valida123!"
    Entonces el usuario debe crearse correctamente por API

  @api @registro @validacion
  Escenario: Registro con firstName vacio
    Cuando registra por API el usuario con firstName "", lastName "Sanchez", email base "noxos002@hotmail.es" y password "Valida123!"
    Entonces la API debe rechazar la solicitud con validacion para "firstName"

  @api @registro @validacion
  Escenario: Registro con lastName vacio
    Cuando registra por API el usuario con firstName "Edwin", lastName "", email base "noxos003@hotmail.es" y password "Valida123!"
    Entonces la API debe rechazar la solicitud con validacion para "lastName"

  @api @registro @validacion
  Escenario: Registro con email vacio
    Cuando registra por API el usuario con firstName "Edwin", lastName "Sanchez", email base "" y password "Valida123!"
    Entonces la API debe rechazar la solicitud con validacion para "email"

  @api @registro @validacion
  Escenario: Registro con password vacio
    Cuando registra por API el usuario con firstName "Edwin", lastName "Sanchez", email base "noxos004@hotmail.es" y password ""
    Entonces la API debe rechazar la solicitud con validacion para "password"

  @api @registro @validacion
  Escenario: Registro con email invalido
    Cuando registra por API el usuario con firstName "Edwin", lastName "Sanchez", email base "noxos005hotmail.es" y password "Valida123!"
    Entonces la API debe rechazar la solicitud con validacion para "email"

  @api @registro @validacion
  Escenario: Registro con password invalida
    Cuando registra por API el usuario con firstName "Edwin", lastName "Sanchez", email base "noxos006@hotmail.es" y password "123"
    Entonces la API debe rechazar la solicitud con validacion para "password"

  @api @login @smoke
  Escenario: Login exitoso
    Dado que existe un usuario creado por API
    Cuando inicia sesion por API con sus credenciales
    Entonces debe recibir acceso exitoso por API

  @ui @contactos @smoke
  Escenario: Crear contacto exitosamente
    Dado que Edwin abre la aplicacion Contact List
    Cuando crea una cuenta nueva desde la interfaz
    Y registra un contacto desde la interfaz
    Entonces debe ver el contacto creado en la lista

  @api @contactos @validacion
  Escenario: Crear contacto con campo First Name vacio
    Dado que el cliente API esta autenticado
    Cuando crea por API un contacto con firstName ""
    Entonces la API debe rechazar la solicitud con validacion para "firstName"

  @api @contactos @validacion
  Escenario: Crear contacto con email invalido
    Dado que el cliente API esta autenticado
    Cuando crea por API un contacto con email "correo_invalido"
    Entonces la API debe rechazar la solicitud con validacion para "email"
