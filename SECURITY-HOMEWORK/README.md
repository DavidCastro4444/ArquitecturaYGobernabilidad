# SECURITY HOMEWORK
## Resumen
Este proyecto es una demostración de cómo implementar la autenticación JWT con Spring Boot.

## Requisitos
- Java 11 o superior
- Maven o Gradle
- Una base de datos compatible con Spring Data JPA (por ejemplo, MySQL, PostgreSQL, etc.)

## Uso

Para autenticar un usuario, envíe una solicitud POST al endpoint /authenticate con los siguientes campos en el cuerpo de la solicitud:

- username: El nombre de usuario del usuario.
- password: La contraseña del usuario

Si la autenticación es exitosa, la aplicación devolverá un token JWT en el cuerpo de la respuesta.

Para acceder a un endpoint seguro, agregue el token JWT a la cabecera de la solicitud en el campo Authorization. El valor de la cabecera debe tener el siguiente formato:

```java
Bearer [token-jwt]
```
## Estructura del proyecto

El proyecto está organizado en los siguientes paquetes:

-    com.example.jwtauthentication.config: Contiene las clases de configuración de seguridad.
-    com.example.jwtauthentication.controller: Contiene los controladores de la aplicación.
-    com.example.jwtauthentication.model: Contiene las clases de modelo de la aplicación.
-    com.example.jwtauthentication.repository: Contiene las clases de repositorio de la aplicación.
-    com.example.jwtauthentication.security: Contiene las clases de seguridad de la aplicación.
-    test: Contiene las pruebas del proyecto.

## Pruebas

El proyecto incluye pruebas unitarias y de integración para varios componentes, como controladores, configuraciones de seguridad y clases de utilidad.

## Mejoras posibles

El proyecto puede mejorarse de varias maneras, por ejemplo:

- Se podría utilizar una biblioteca de autenticación JWT más completa, como JJWT: https://github.com/jwtk/jjwt.
- Se podría implementar la autenticación de dos factores.
- Se podría implementar la autorización de usuarios.

## Contexto de Uso:

# utenticación del Usuario (AuthController y JwtTokenProvider):

El usuario envía una solicitud POST a /authenticate con credenciales (username y password) en el cuerpo.
AuthController autentica al usuario utilizando Spring Security y, si las credenciales son válidas, genera un token JWT utilizando JwtTokenProvider.
El token JWT se devuelve al cliente.

# Validación del Token y Seguridad de Endpoints (JwtTokenFilter y WebSecurityConfig):

- El cliente incluye el token JWT en las solicitudes a los endpoints seguros.
- El filtro JwtTokenFilter intercepta las solicitudes y valida el token utilizando JwtTokenProvider.
- WebSecurityConfig configura Spring Security para asegurar los endpoints y validar tokens JWT para las solicitudes.

# Persistencia del Usuario (User y UserRepository):

-La entidad User representa la estructura del usuario en la base de datos.
-UserRepository proporciona métodos para realizar operaciones CRUD en la tabla de usuarios.

## Flujo de trabajo

El flujo de trabajo de la autenticación es el siguiente:

- El usuario envía una solicitud de autenticación a la aplicación.
- La aplicación consulta la base de datos para verificar las credenciales del usuario.
- Si las credenciales son válidas, la aplicación devuelve un token JWT al usuario.
- El usuario agrega el token JWT a la cabecera de la solicitud cuando solicita acceso a un endpoint seguro.
- La aplicación valida el token JWT y, si es válido, permite el acceso al endpoint.


## Arquitectura
[arquitectura](data:image/png;base64,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)

## Autor
- David Santiago Castro Vargas