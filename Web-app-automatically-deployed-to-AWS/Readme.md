
											

# Web app automatically deployed to AWS

Para esta actividad se realizaron 3 procesos 

## Contenido: 🗂️ 

 > CDK
 > 
 > Terraform 
 > 
 > SSH

# Arquitectura

![image](https://github.com/DavidCastro4444/ArquitecturaYGobernabilidad/blob/main/Web-app-automatically-deployed-to-AWS/Imagines/Arquitectura.png)

- WebServer: Un servidor web que sirve contenido estático y dinámico a los usuarios.
- SecurityGroup: Un grupo de seguridad que controla el acceso al servidor web.
- MyLaunchC.conf: Un archivo de configuración que se utiliza para iniciar el servidor web.
- MyApplication: La aplicación web que se ejecuta en el servidor web.
- User Manager: Un servicio que gestiona los usuarios de la aplicación web.

La arquitectura funciona de la siguiente manera:

- El servidor web se inicia en una instancia EC2 de AWS.
- El grupo de seguridad permite que los usuarios autorizados accedan al servidor web.
- El archivo de configuración MyLaunchC.conf se utiliza para iniciar el servidor web con las opciones deseadas.
- La aplicación web se ejecuta en el servidor web.
- El servicio User Manager se utiliza para gestionar los usuarios de la aplicación web.
- Para escalar la aplicación web, se pueden agregar más instancias EC2 al grupo de seguridad. Esto se puede hacer manualmente o mediante el uso de una política de escalado automático.

En la imagen, se muestra cómo se utilizan los siguientes servicios de AWS para implementar la arquitectura:

- ALBTargetGroup: Un grupo de destino de balanceador de carga que distribuye el tráfico entre las instancias EC2.
- ALBListener: Un oyente de balanceador de carga que escucha las solicitudes entrantes.
- AutoScalingGroup: Un grupo de escalado automático que controla el número de instancias EC2 en el grupo de seguridad.

La arquitectura se puede mejorar agregando los siguientes componentes:

- CDN: Una red de distribución de contenido que puede proporcionar rendimiento y disponibilidad mejorados para la aplicación web.
- Cache: Un caché que puede mejorar el rendimiento de la aplicación web almacenando datos en memoria.
- Logging: Un servicio de registro que puede ayudar a diagnosticar problemas con la aplicación web.

# RESULTADOS AWS

## Parametros
![image](https://github.com/DavidCastro4444/ArquitecturaYGobernabilidad/blob/main/Web-app-automatically-deployed-to-AWS/Imagines/paramaters.PNG)

## Instancia 
![image](https://github.com/DavidCastro4444/ArquitecturaYGobernabilidad/blob/main/Web-app-automatically-deployed-to-AWS/Imagines/evidencia%201.PNG)

## Instancias EC2 Creadas 
![image](https://github.com/DavidCastro4444/ArquitecturaYGobernabilidad/blob/main/Web-app-automatically-deployed-to-AWS/Imagines/EC2.PNG)

  
### Autor  
  David Santiago Castro Vargas
