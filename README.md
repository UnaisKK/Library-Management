# Library-Management System using Spring Boot
## Introduction
A Library Management System designed to see the books available in a library. It allows patrons to borrow/return books from the library. The backend is designed as a **Monolithic Architecture**.
## Technologies and Dependencies Used
* [Maven](https://maven.apache.org/) used as a dependency management tool.
* [Spring Boot](https://spring.io/projects/spring-boot) used to build hassle free web applications and writing REST APIs.
* [Spring Security](https://spring.io/projects/spring-security) used for Authentication and Authorizations.
* [Spring data JPA (Hibernate)](https://hibernate.org/) Used to reduce the time of writing hardcoded sql queries and instead allows to write much more readable and scalable code
* [MySQL](https://www.mysql.com/) used as a Java persistence store
* [Project Lombok](https://projectlombok.org/) Reduces the time  of writing java boiler plate code.



## Backend Design
### Entities
Actors/Entities are inspired by the real world entities that can use the applications
1. **Book** having attributes:
* id (unique primary key), title, description, author, ISBN, publication year, quantity.
2. **Patron** having attributes:
* id (unique primary key), name, address, phoneNumber, emailAddress.
3. **Borrow** having attributes:
* id (unique primary key) book_id (foreign key), patron_id (foreign key), borrowing date, return date.
4. **UserInfo** having attributes:
* id (unique primary key),name, email, password and role

## Authentication

User has to store username and password manually(temporary solution). Password has to be encoded string.
Step 1-> Either user can create an api to register the user
or user can load password directly, eg:- "$2a$12$Q5UpOHzlXqI5vLr6ZZOLWuEghgvv88iP4a0vXCFba91N0ySRy556O" for "123456" password.
Step 2-> call /api/authenticate API to get the token with following JSON body
{
"username":"Unais",
"password":"123456"
}

step 3-> Once token is generated, user can hit other APIs by passing bearer token. As of now all the APIs are allowed to authenticate with this token. it can be configurable by adding different roles.
