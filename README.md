# Online Shop: TOP STORE

A high-quality cosmetics online store

Simple web-site, based on the RDBMS MySQL (MySQLWorkbench was used during creating the project)

(There was also implemented a simple realization with the Storage class, without using JDBC)

The inner structure was created according to N-Tier architecture and SOLID principles 

RBAC was chosen as the authorization mechanism managed with filters

Statefull app (loging in -> session with id)

Packaging: Apache Maven

Web-server: Tomcat

Logger: Log4j
 
Front-end: BootStrap, CSS, html

# Main functions:

- Admin:

  creating, updating and deleting products
  
  creating, updating and deleting users
  
  managing users' roles
  
  deleting orders, having access to its details
  
- User:

  registration
  
  buying products
  
  access to the shopping cart
  
  creating orders
  
  viewing user's orders and its details
  
  viewing user's details and its updating
  
# Configuration:

- Tomcat (Local):

  Deployment - war_exploded, context address - "/"

- Database:

  Run the configuration code from the database.sql file in your RDBMS

  Configure your connection properties in the /com/internet/shop/util/ConnectionUtil.java
  
- Authorizating as an admin:

  On the login page press "Create Admin" button
  
  Then type in "Admin" for the login and "Admin007" for the password
  
  For authorizating as a user press "Sign Up" button on the login page or navigation bar

![Home Page](https://i.imgur.com/9yThKJy.png)
