# Blog-REST-API
Creating REST API Spring Boot Project for Blog Application

A blogging Application is a web-based application that allows users to create and publish blog posts, read and comment on other users' posts, and categorize posts by topics.
The application has a user authentication system that allows users to create accounts, login, logout securely, resetting passwords, and updating account information.
It uses a RESTful API to handle requests and responses between the frontend and backend of the application.
The API allows for CRUD operations on blog posts, comments, categories, and user accounts.


### Technologies/Tools used: </br>
Java 17 </br>
Spring Boot framework </br>
Hibernate </br>
MySQL database </br>
Apache Tomcat </br>
Postman


## Features
- User Login / Signup 
- Create Blog / post
- CRUD Operation on POST
- Comment on post
- CRUD Operation on comment
- Pagination
_ Search By USER
- Search By Category
- Sorting


## Backend Work

- Stored Data in MySQL
- Authentication of Login
- Input Validation
- Server side pagination


### Architecture </br>
![image](https://github.com/jagruti1998/Blog-REST-API/assets/50023337/0e023e7c-4f40-410e-930f-09f7c4b82e12)

### ER Diagram </br>
![image]()

## Installation & Run
 - Before running the API server, we should update the database config inside the application.properties file.
 - Update the port number, username and password as per our local database config.  
    - server.port=8080
    - spring.datasource.url=jdbc:mysql://localhost:3306/myblog
    - spring.datasource.username=root
    - spring.datasource.password=root
    - spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
    - spring.jpa.hibernate.ddl-auto=update

  
# PostMan
  
