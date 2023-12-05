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
![Java 8](https://github.com/DavidCastro4444/ArquitecturaYGobernabilidad/blob/main/SECURITY-HOMEWORK/Imagenes/descarga.png)

## Autor
- David Santiago Castro Vargas