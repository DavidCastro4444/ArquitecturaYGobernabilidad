## Architecture of Massively distributed applications



## Documentación de la solución

Esta arquitectura promueve la modularidad y la escalabilidad, permitiendo un desarrollo, despliegue y mantenimiento más eficientes y flexibles. Cada microservicio se centra en una tarea específica, lo que facilita la gestión y evolución del sistema. La interconexión entre los servicios se realiza a través del API Gateway, y la configuración y el descubrimiento de servicios permiten una comunicación fluida entre ellos. Además, se incluyen servicios de autenticación, autorización, registro y monitoreo para garantizar la seguridad y el rendimiento del sistema.

### Vista de Arquitectura
![ARQ.JPG](https://github.com/DavidCastro4444/ArquitecturaYGobernabilidad/blob/main/Taller-de-Introducci%C3%B3n-a-Virtualizaci%C3%B3n-prog.-distribuida/Imagenes/Dise%C3%B1o%20arquitectura.png)


## Catálogo de servicios 💬

| Recurso      | Operación | URL                        | Descripción                                                     |
|--------------|-----------|----------------------------|-----------------------------------------------------------------|
| Users        | GET       | /users/                    | Lista los usuarios registrados en la solución                   | 
| Users        | POST      | /users/                    | Registra un nuevo usuario en la solución                        | 
| Users        | GET       | /users/{id}                | Obtiene información del usuario con id = {id}                  | 
| Users        | PUT       | /users/{id}                | Actualiza la información del usuario con id = {id}             | 
| Users        | GET       | /users/{id}/followers      | Obtiene seguidores del usuario con id = {id}                   | 
| Users        | GET       | /users/{id}/following      | Obtiene usuarios seguidos por el usuario con id = {id}         | 
| Tweet        | POST      | /tweets/                   | Crea un nuevo tweet                                            | 
| Tweet        | GET       | /tweets/{id}               | Obtiene información del tweet con id = {id}                    | 
| Tweet        | DELETE    | /tweets/{id}               | Elimina el tweet con id = {id}                                 | 
| Tweet        | POST      | /tweets/{id}/like          | Da "me gusta" a un tweet                                       | 
| Tweet        | POST      | /tweets/{id}/retweet       | Retwittea un tweet                                             | 
| Follow       | POST      | /follow/                   | Sigue a un usuario                                             | 
| Follow       | POST      | /unfollow/                 | Deja de seguir a un usuario                                    | 
| Notification  | POST      | /notifications/            | Envia una notificación                                         | 
| Notification  | GET       | /notifications/{userId}    | Obtiene notificaciones para un usuario                         | 
| Hashtag      | POST      | /hashtags/                 | Crea un nuevo hashtag                                          | 
| Hashtag      | GET       | /hashtags/{id}             | Obtiene información del hashtag con id = {id}                  | 
| Hashtag      | GET       | /hashtags/{id}/tweets      | Obtiene tweets asociados a un hashtag con id = {id}             | 
| Search       | GET       | /search/users?q={query}    | Busca usuarios por consulta                                     | 
| Search       | GET       | /search/tweets?q={query}   | Busca tweets por consulta                                       | 
| Search       | GET       | /search/hashtags?q={query} | Busca hashtags por consulta                                   | 
| Timeline     | GET       | /timeline/{userId}         | Obtiene la línea de tiempo de un usuario                       | 
| Analytics    | GET       | /analytics/users/{userId}  | Obtiene análisis de un usuario                                 | 
| Analytics    | GET       | /analytics/tweets/{tweetId} | Obtiene análisis de un tweet                                  |



# Grafo para visualizar la relacion entre ellos
- Users
  - GET /users/
  - POST /users/
  - GET /users/{id}
  - PUT /users/{id}
  - GET /users/{id}/followers
  - GET /users/{id}/following

- Tweet
  - POST /tweets/
  - GET /tweets/{id}
  - DELETE /tweets/{id}
  - POST /tweets/{id}/like
  - POST /tweets/{id}/retweet

- Follow
  - POST /follow/
  - POST /unfollow/

- Notification
  - POST /notifications/
  - GET /notifications/{userId}

- Hashtag
  - POST /hashtags/
  - GET /hashtags/{id}
  - GET /hashtags/{id}/tweets

- Search
  - GET /search/users?q={query}
  - GET /search/tweets?q={query}
  - GET /search/hashtags?q={query}

- Timeline
  - GET /timeline/{userId}

- Analytics
  - GET /analytics/users/{userId}
  - GET /analytics/tweets/{tweetId}

