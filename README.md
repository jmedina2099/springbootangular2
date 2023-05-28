El front-end se despacha desde springboot en el puerto 8080, de la misma manera los endpoints viven también en el puerto 8080:

http://localhost:8080/angularapp/

La aplicación se compila con maven 3.9.1 y con Java 11:

mvn compile

Y se ejecuta con:

mvn spring-boot:run
