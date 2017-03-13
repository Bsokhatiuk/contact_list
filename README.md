## Prerequisites
- JDK 1.8 or later
- Maven 3 or later

## Stack
- Spring Security
- Spring Boot
- Spring Data JPA
- Maven
- JSP
- MySQL

## Run
```mvn clean spring-boot:run```

##SQL
    CREATE  TABLE user (id int PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) not NULL, username VARCHAR(255) not NULL, password VARCHAR(255) not NULL);
    CREATE  TABLE role (id int PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255));
    CREATE  TABLE user_role (user_id int, role_id int,FOREIGN KEY(user_id) REFERENCES user(id),FOREIGN KEY(role_id) REFERENCES Role(id));
    CREATE  TABLE record (id int PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) not NULL, surname VARCHAR(255) not NULL, patronymic VARCHAR(255) not NULL, mobile_phone VARCHAR(255) not NULL, home_phone VARCHAR(255), address VARCHAR(255),email VARCHAR(255), user_id int, FOREIGN KEY(user_id) REFERENCES user(id));


