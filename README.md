
### `Sitio desplegado en VPS`
Se hizo el despliegue de la base, ElasticSearch y el proyecto web en contenedores docker dentro de un
VPS al cual se puede acceder en el siguiente link: <br />

•	Spring Boot application: http://45.79.56.138:8001/link/{username} <br />
•	Elastic Search Container: http://45.79.56.138:9200/agent/_search <br />


Se pueden utilizar los siguientes usuarios para las pruebas: <br />
•	sarmi1994 <br />
•	smith1122 <br />
•	maria1984 <br />
•	cmartinez <br />
•	zulaym <br />


### `Creación de base de datos`

Ejecutar el archivo deploy/database/start.sql para crear la base de datos, la tabla e insertar los datos iniciales. <br />

### `Ejecución de código`
1. Descargar proyecto.
2. Cambiar el parámetro path.json.file del archivo application.properties para especificar la ruta donde se encuentra el archivo clicks.json.
3. Abrir la consola de comando y ubicarse dentro de la ruta donde se encuentra el proyecto.
4. Ejecutar el comando mvnw clean install
5. Ejecutar el comando: mvnw clean package
6. Se generará un archivo .jar dentro de la carpeta target/ que será nuestro ejecutable.
7. Ubicarse en la ruta "target/" y ejecutar el jar con el comando: java -jar JustClick-0.0.1-SNAPSHOT.jar
8. Abrir el navegador y escribir la url "http://45.79.56.138:8001/link/{username}" donde denberá reemplazar "{username}" por uno de los usuarios especificados anteriormente.



### `Campo max_value y cookie`
El campo max_value se estableció en el valor de 5. <br />
Se estableció una cookie como contador con el valor según el número de solicitud que se haya hecho en la web, si ese valor llega a ser igual al valor del campo max_value, será redirigido a una página 404.





