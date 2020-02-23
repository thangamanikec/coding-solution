# coding-solution

At the initial level of Design, created domain solution with the below assumptions :

1. A Village can have any number of counters
2. A counter will be tagged to only one village
3. A counter can send counter_Callback record any number of time

Application was planned to be built with Java 8 on :
1. Spring Boot Microservices
2. Maven
3. Spring Web MVC
4. Spring REST Services
5. JPA
6. SQL Database
7. Junit

-Database was created based on the Domain solution arrived

-schema.sql & data.sql are placed in resource folders and takes care in creating Table and embedding sample data for the Application

-Have created the Repository Services and Domain entities respectively with Spring Data JPA

-Have designed the Controlling part with Spring Data Rest API which is hypermedia driven RESTful API providing Out of Box Features
with few lines of code

-Have accomplished JUnit Testing and Integration Testing on the end-to-end model

Attached few REST API calls with the project which can be imported and can be hit directly via Postman:

1. For making a Counter Entry
2. To record a Counter to a Village with Village Details
3. To read each of the Counter Detail
4. To read each of the Village Record
5. To Find the Energy Consumption of Each Village for past 24 hrs


Note : The final accomplished Class Diagram and Domain Diagram is attached with this project for review and Application URL: localhost:8080 needs to be hit using Postman client
