Payment Manager 
======================== 
Simple web application to manage payments between users. 
Current features: 
Login 
Users creation and deletion 
Account and payment management  
 
======================= 
General Architecure 
The project has tow server applications: WebVisualizer and Core, both might be in the same or different server. The first one is the web GUI that uses "Core" server to access to the application data. Core exposes services that could be consumed for any other application (Desktop, mobile, etc.. )  
 
================== 
How to deploy 
================== 
Requariments: 
Tomcat 7 
maven 2.3.2 
postgres 9.3 
java JDK 7 
Internet access 
============= 
Build Project 
Make sure maven is in the Path environment and run the follow commad in the proyect's root directory: 
mvn package   
=========== 
Configure: 
--Core:  
Run database creation script in /Core/config/db_creation_script in postgres terminal (this also create a default user width credentials: admin@test.com password: 1234) 
Set prostgres database properties in :webapp/WEB-INF/configuration.properties 
--WebVisualizer : 
Set urls in resources/configuration.properties to aim to Core server
