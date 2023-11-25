## TALLER DE INTRODUCCIÓN A VIRTUALIZACIÓN Y PROG. DISTRIBUIDA



## Documentación de la solución

### Vista de Arquitectura
![ARQ.JPG](https://github.com/DavidCastro4444/ArquitecturaYGobernabilidad/blob/main/Taller-de-Introducci%C3%B3n-a-Virtualizaci%C3%B3n-prog.-distribuida/Imagenes/Dise%C3%B1o%20arquitectura.png)

Esta vista muestra los componentes principales de la solución.
- EC2 Server: Este motor permitirá la creación y ejecución de contenedores.
- Docker Container: Un contenedor es una unidad ligera y portátil que incluye todo lo necesario para ejecutar una pieza de software, incluyendo el código, las bibliotecas, las dependencias y las variables de entorno.
- SparkWebServer: Aplicación java que realiza una exposición de un servicio.
- Browser: Navegador genérico que realiza peticiones al servidor Web

### Componentes relevantes

### *SparkWebServer*
Clase encargada de crear el entorno del servidor web, definir el puerto de exposición y exponer el contenido.
<details><summary>Desplegar Código fuente</summary>
<p>

#### Encontrado en main/java/SparkWebServer.java

```java
import static spark.Spark.*;

public class SparkWebServer {

    public static void main(String... args){
        port(getPort());
        get("hello", (req,res) -> "Hello Docker!");
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}
```
</details></p>

![ARQ.JPG](https://github.com/DavidCastro4444/ArquitecturaYGobernabilidad/blob/main/Taller-de-Introducci%C3%B3n-a-Virtualizaci%C3%B3n-prog.-distribuida/Imagenes/Ejemplo%20ejecucion.drawio.png)

### *DockerFile*
Documento que especifica las dependencias para el microcontenedor Docker. En este caso, la solución define un entorno de ejecución en una imagen estable de OpenJDK 8 con un puerto de exposición definido por configuración (6000 en este caso).
<details><summary>Desplegar Código fuente</summary>
<p>

#### Encontrado en DockerFile

```dockerfile
FROM openjdk:8
LABEL authors="David"

WORKDIR /usrapp/bin

ENV PORT 6000

COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency

CMD ["java","-cp","./classes:./dependency/*","SparkWebServer"]
```
</details></p>

## Instalación
- Realizar el build de la solución utilizando el siguiente comando.
```bash
mvn clean install
```

- Construir la imagen de la solución con el siguiente comando.
```bash
docker build --tag dockersparkprimer .
```

Como resultado de este comando, se ha creado una nueva imagen reutilizable de Docker con el tag definido en el comando. Esta imagen contiene no solo las dependencias de la solución, sino también todos los ejecutables necesarios como un conjunto de procesos.

- Para ejecutar una instancia de la imagen anterior y vincular un puerto local al puerto del contenedor, utilice el siguiente comando
```bash
docker -p <puerto local>:<puerto del contenedor> --name <nombre de la instancia> <nombre de la imagen>
```

- También puede usar el método de composición definido en el documento docker-compose.yml que en esta oportunidad vinculará la imagen al puerto 8087 y automatizará la creación de una base de datos de MongoDB.
```bash
docker-compose up -d
```
<details><summary>Desplegar Código fuente</summary>
<p>

#### Encontrado en DockerFile

```yml
version: '2'

services:
  web:
    build:
      context: .
      dockerfile: Dockerfile
    container_name: web
    ports:
      - "8087:6000"
  db:
    image: mongo:3.6.1
    container_name: db
    volumes:
      - mongodb:/data/db
      - mongodb_config:/data/configdb
    ports:
      - 27017:27017
    command: mongod

volumes:
  mongodb:
  mongodb_config:
```
</details></p>


- Para desplegar esta imagen a un repositorio de DockerHub puede utilizar los siguientes comandos.
```bash
docker tag dockersparkprimer <nombre de repositorio remoto DockerHub>
docker push <nombre de repositorio remoto DockerHub>:<version de la imagen a desplegar>
```


## Detalles técnicos del proyecto
- [Java 8](https://www.oracle.com/co/java/technologies/javase/javase8-archive-downloads.html)
- [Spark Java 2.9.2](https://mvnrepository.com/artifact/com.sparkjava/spark-core/2.9.2)
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/)
