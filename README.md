# ARSW - Nombres, redes, clientes y servidores
## Nicolás Prieto Vargas  

## 1. Leyendo los valores de un objeto URL

Escriba un programa en el cual usted cree un objeto URL e imprima en
pantalla cada uno de los datos que retornan los métodos: getProtocol, getAuthority, getHost, getPort, getPath, getQuery,
getFile, getRef.

Este ejercicio se realiza en el archivo URLReader.java de la siguiente manera:

utiliza la clase URL del paquete java.net para analizar y descomponer la dirección web http://www.google.com/. Al 
ejecutarse, crea un objeto URL con esa dirección y luego imprime y guarda en una lista (metodos) diferentes componentes
de la URL: el protocolo (como "http"), la autoridad (host y puerto juntos), el host (por ejemplo, "www.google.com"), el
puerto (si está definido), la ruta del recurso (path), la consulta (query), el archivo completo (path + query) y la
referencia (fragmento tras #, si lo hubiera). Esencialmente, sirve como ejemplo para mostrar cómo descomponer una URL
en sus partes con Java. Este programa sirve con cualquier URL, en este caso la respuesta con la URL de gooogle sería:

![img.png](Imagenes/img.png)

## 2. Leyendo páginas de internet
Escriba una aplicación browser que pregunte una dirección URL al usuario
y que lea datos de esa dirección y que los almacene en un archivo con el nombre
resultado.html.  
Luego intente ver este archivo en el navegador.

Este ejercicio se realiza en el archivo NavegadorApp.java

Este programa en Java simula el comportamiento básico de un navegador web. Solicita al usuario que ingrese una URL (como
https://www.google.com) a través de la consola. Luego, intenta conectarse a esa dirección usando la clase URL, y si tiene
éxito, lee línea por línea el contenido HTML de la página usando un BufferedReader. Ese contenido se guarda localmente
en un archivo llamado resultado.html mediante un BufferedWriter. Si la URL es inválida o ocurre un error durante la
lectura o escritura del archivo, el programa captura y muestra el mensaje de error correspondiente. En resumen, descarga
el contenido de una página web y lo guarda en un archivo HTML local. Para este ejercicio, se hará con la URL de wikipedia.

![img_1.png](Imagenes/img_1.png)

Se crea el archivo resultado.html en la carpeta del repositorio.

![img_2.png](Imagenes/img_2.png)

Y al abrir resultado.html.

![img_3.png](Imagenes/img_3.png)

Observamos que no cargan las imágenes pero si el texto como es y en las posiciones originales

![img_4.png](Imagenes/img_4.png)

Esta es la imagen de la página original

## 3. Implementación de servidor

Escriba un servidor que reciba un número y responda el cuadrado de este
número.

Este ejercicio se realiza en los archivos ServidorCuadrado.java y ClienteCuadrado.java.

Este conjunto de programas en Java implementa una aplicación cliente-servidor simple usando sockets para calcular el 
cuadrado de un número. El archivo ServidorCuadrado.java actúa como servidor: escucha conexiones entrantes en el puerto 
35000, acepta un número enviado por un cliente, lo intenta convertir a entero, calcula su cuadrado y lo imprime en 
consola. Por otro lado, ClienteCuadrado.java se conecta al servidor local (localhost),
solicita al usuario un número, lo envía al servidor y espera una respuesta la cual se imprime en consola.

Al correr el programa debemos primero ejecutar el Servidor, y luego el cliente, dando como resultado:

![img_5.png](Imagenes/img_5.png)  
![img_6.png](Imagenes/img_6.png)

## 4. Servidor web

Escriba un servidor web que soporte múltiples solicitudes seguidas (no concurrentes). El servidor debe retornar todos
los archivos solicitados, incluyendo páginas html e imágenes.

Este ejercicio se realiza en el archivo WebServer.java

Este programa en Java implementa un servidor web básico que escucha en el puerto 8080 y sirve archivos estáticos desde
un directorio local definido (ROOT). Al iniciarse, el servidor acepta conexiones entrantes, lee la solicitud HTTP (solo
maneja peticiones GET), y busca el archivo correspondiente dentro del directorio raíz. Si el archivo solicitado existe y
no es un directorio, lo envía al cliente con los encabezados HTTP adecuados, incluyendo el tipo MIME determinado por la
extensión del archivo. Si el archivo no existe, responde con un error 404 Not Found y muestra un mensaje HTML simple. 
Para este programa se creó una página html sencilla con texto y dos imágenes.

Al programa se le pidió que mostrara qué elemento se estaba solicitando, quedando de la siguiente manera.

![img7.png](Imagenes/img7.png)

La página es así:

![img8.png](Imagenes/img8.png)

## 5. Datagramas

Utilizando Datagramas escriba un programa que se conecte a un servidor
que responde la hora actual en el servidor. El programa debe actualizar la hora
cada 5 segundos según los datos del servidor. Si una hora no es recibida debe
mantener la hora que tenía. Para la prueba se apagará el servidor y después de
unos segundos se reactivará. El cliente debe seguir funcionando y actualizarse
cuando el servidor esté nuevamente funcionando.

Este ejercicio se realiza en los archivos HoraCliente.java y HoraServidor.java.

El archivo HoraServer.java crea un servidor UDP que escucha en el puerto 9876. Cada vez que recibe un paquete, obtiene 
la hora actual con formato HH:mm:ss, la convierte a bytes y la envía de vuelta al cliente que hizo la solicitud.
Por su parte, HoraCliente.java es un cliente UDP que envía repetidamente la palabra "HORA" al servidor cada 5 segundos.
Luego espera una respuesta (hasta 2 segundos por intento). Si recibe la hora, la muestra; si no hay respuesta (por ejemplo,
el servidor no está disponible), muestra un mensaje indicando que se usará la hora previa.

Para que el programa funcione, primero debemos ejecutar el cliente y luego el servidor, dando como resultado:

![img44.png](Imagenes/img44.png)

Se evidencia que carga bien la hora cada 5 segundos y al apagar el servidor, queda mostrando la última hora que se obtuvo.

## 6. CHAT

Utilizando RMI, escriba un aplicativo que pueda conectarse a otro
aplicativo del mismo tipo en un servidor remoto para comenzar un chat. El
aplicativo debe solicitar una direcci´on IP y un puerto antes de conectarse con el
cliente que se desea. Igualmente, debe solicitar un puerto antes de iniciar para
que publique el objeto que recibe los llamados remotos en dicho puerto.

Para la solución de este ejercicio se usaron los archivos ChatApp.java ChatImpl y ChatInterface.

Este sistema implementa un chat entre dos usuarios usando RMI (Remote Method Invocation) en Java, permitiendo que se
envíen mensajes en tiempo real a través de la red. En ChatApp.java, al iniciar la aplicación, se le pide al usuario su
nombre, un puerto para publicar su objeto remoto (ChatImpl), y la información de conexión del otro usuario (IP, puerto y
nombre). Luego, se registra el objeto remoto (ChatImpl, que implementa ChatInterface) en un registry local y se conecta
al objeto remoto del otro usuario usando su dirección RMI. Una vez conectados, entra en un bucle donde cada mensaje
escrito se envía al otro usuario usando el método remoto recibirMensaje.

Para probar este programa, se deben abrir dos terminales en el mismo computador, usa será la de IntelliJ y la otra será 
la consola de comandos.

![img222.png](Imagenes/img222.png)

![img33.png](Imagenes/img33.png)

Acá podemos ver las dos terminales conectadas, ahora se enviará un mensaje desde ambas y se verá reflejado en el otro chat.

![img55.png](Imagenes/img55.png)

Mensaje enviado por Nicolás

![img2222.png](Imagenes/img2222.png)

Mensaje recibido por Ana.

## FIN