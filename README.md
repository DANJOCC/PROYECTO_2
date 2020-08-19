# PROYECTO_2
Una calculadora de IPs ipv4 e ipv6, ademas del calculo de la IP ipv4 necesario para un numero de host.

El archivo CIP.jar tiene el programa sin la implementacion de una base de datos para poder registrar
y ver las ip ingresadas con anterioridad.
El archivo CIP.zip implementa la base de datos, se debe de cambiar la direccion y el usuario para usarla.

para crear la tabla requerida:

CREATE TABLE IPv4(
        ip VARCHAR(20) primary key,
        tipo VARCHAR(15),
        clase VARCHAR(15),
        difusion VARCHAR(20),
        mascara VARCHAR(20),
        red VARCHAR(20),
        broadcast VARCHAR(20),
        gateway VARCHAR(20)
);
CREATE TABLE IPv6(
        ip VARCHAR(60) primary key,
        clase VARCHAR(35),
);

si se ingresa a la calculadora en modo ipv6 una direccion de este tipo:123.123.123.123 sin mascara de red de la forma /n
el programa lanzara este error: Exception in thread "AWT-EventQueue-0" java.lang.ArrayIndexOutOfBoundsException: 1. 
pero si lo hace con una mascara de red no. si ingresa algo como: 123. o 123.123.123. en modo IPv6 funcionara bien, pero 
no identificara nada.

