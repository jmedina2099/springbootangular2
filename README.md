/////////////////////////////////////////////////////////////////

El front-end se despacha desde springboot en el puerto 8080, de la misma manera los endpoints viven también en el puerto 8080

La aplicación usa maven 3.9.1 y con Java 17

Se puede usar docker para tener la aplicacion en un container

/////////////////////////////////////////////////////////////////

Para development:

mvn compile -Pfront-dev

mvn package -Pdev

mvn spring-boot:run -Pdev

/////////////////////////////////////////////////////////////////

Para produccion:

mvn compile -Pfront-prod

mvn package -Pprod

mvn spring-boot:run -Pprod

/////////////////////////////////////////////////////////////////

Para contenedor y apache externo:

mvn compile -Pfront-container

mvn package -Pcontainer

/////////////////////////////////////////////////////////////////

Para contenedor en produccion y apache externo:

mvn compile -Pfront-container-prod

mvn package -Pcontainer-prod

/////////////////////////////////////////////////////////////////

Para dockerize y compilar desde container con:

docker build . -t container

docker run -p 80:8080 container

/////////////////////////////////////////////////////////////////

Para limpiar el front

mvn clean -Pclean-front

/////////////////////////////////////////////////////////////////

Para limpiar el back

mvn clean -Pdev

/////////////////////////////////////////////////////////////////

Para correr las pruebas unitarias:

mvn test -Ptest

/////////////////////////////////////////////////////////////////

Para probar sonar:

mvn validate -Psonar

/////////////////////////////////////////////////////////////////
