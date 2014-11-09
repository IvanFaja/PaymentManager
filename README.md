Payment Manager  
========================  
Simple web application to manage payments between users.  
Current features: 
---------------------  
*Login  
*Users creation and deletion  
*Account and payment management   
 
 
General Architecure  
=======================  
The project has two server applications: WebVisualizer and Core, both might be deployed in the same or different servers. The first one is the web GUI that uses "Core" server to access to the application data. Core exposes services that could be consumed for any other application (Desktop, mobile, etc. )   
  
How to deploy  
==================  
Requariments:  
----------------- 
*Tomcat 7  
*maven 2.3.2  
*postgres 9.3  
*Java JDK 7  
*Internet access  
  
Build Project 
----------------- 
Make sure maven is in the Path environment and run the follow commad in the proyect's root directory:  
mvn package    
 
Configuration:  
-------------- 
Core: 
####### 
*Run database creation script in /Core/config/db_creation_script in postgres terminal (this also create a default user width credentials: admin@test.com password: 1234)  
 
 
*Set prostgres database properties in :webapp/WEB-INF/configuration.properties  
 
 
WebVisualizer : 
############ 
*Set urls in resources/configuration.properties to aim to Core server 
 
Español 
========== 
Una aplicación para manejar pagos entre usuarios 
Características actuales :  
*Manejo de sesiones por usuario 
*Creación y eliminación de usuarios 
*Creación de cuenta y transferencias 
General Architecure  
=======================  
El proyecto consta de dos aplicaciones: WebVisualizer y Core, las cuales pueden estar en el mismo o diferentes servidores. La primera es una interfaz gráfica web que utiliza  el servidor "Core" para acceder a los datos. Los servicios expuestos por el servidor "Core" pueden ser consumidos por otro tipo de aplicaciones (aplicaciones de escritorio, móviles, etc.) 
Como desplegar la aplicación  
==================  
Requisitos:  
----------------- 
*Tomcat 7  
*maven 2.3.2  
*postgres 9.3  
*Java JDK 7  
*Acceso a Internet  
Construir el Project 
----------------- 
Estando seguro que maven esta en las variables de entorno correr el siguiente comando en el directorio raíz del proyecto  
mvn package    
 
Configuración:  
-------------- 
Core: 
####### 
*Correr el script de creacion de la base de datos: "/Core/config/db_creation_script" en  la consola de postgres (este script tambien crea un usuario con las credenciales: admin@test.com password: 1234)  
*Cambiar el archivo ":webapp/WEB-INF/configuration.properties" para que contenga la informacion correcta de la base de datos  
WebVisualizer : 
############ 
*Cambiar el archivo "resources/configuration.properties" para que las urls apunten a donde esta el servidor "Core"
