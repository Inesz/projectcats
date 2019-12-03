# projectcats
Based on online course https://kobietydokodu.pl/kurs-javy/
Web Application in Spring Framework.
CRUD form management cats (pets) data.

Technologies used:
- Java JDK 11
- Spring 5
- Maven 3
- Hibernate 5
- JPA
- Rest WebService (Spring)
- Apache CXF SOAP WebService (javax.jws; JEE; code first approach)
- Google Cloud storage
- JPQL
- MySQL DB
- Lgger SLF4J
- Javadoc
- JSTL
- WebJar
- JSP, JSPF
- Bootstrap 4, CSS, LESS, JavaScript

Development environment:
- intelliJ IDEA Community Edition
- Apache Tomcat (Smart Tomcat)
- SonarLint
- Google Cloud (https://console.cloud.google.com/)
- DB https://remotemysql.com
- Wizdler for SOAP messages sending

Implemented functionality:
- authentication and authorization users
- i18n for PL, GB, US, ZH
- CRUD for cats
- get/set cat image with google cloud
- DTO validation for cat and cat image
- cat image file format validation
- weather widget (https://weatherwidget.io/) for Gdańsk from forecast7.com
- rest service: simply hello message; cat by id with catFoto info
- soap service: simply hello message; cats list with catsFoto info
- every 3 minutes log app running message

Comment about the application's operation:
- demo version
- non-implemented functionality of adding a new user
- launching the application deletes the data from the database and adds default ones (files in google storage are not removed, however are not related to information in the database)

Rest Service:
- http://localhost:8080/catswebapp/rest/cats/91
- http://localhost:8080/catswebapp/rest/cats/hello

Soap Service (check documentation/SOAP):
- http://localhost:8080/catswebapp/soap/api SOAPAction: "helloSOAP"
- http://localhost:8080/catswebapp/soap/api SOAPAction: "catsList"