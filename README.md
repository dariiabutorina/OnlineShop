# Online Shop: TOP STORE

A high-quality cosmetics online store

Simple **web-site**, based on the **RDBMS MySQL** (MySQLWorkbench was used during creating the project) using **JDBC**

(There was also implemented a plain implementation with the Storage class, without using JDBC)

The inner structure was created according to **N-Tier architecture** and **SOLID principles**

**RBAC** was chosen as the authorization mechanism managed with **filters**

**Statefull app** (authentication —> session with id)

**Packaging:** Apache Maven

**Web-server:** Tomcat

**Logger:** Log4j
 
**Front-end:** BootStrap, CSS, html

# Main functions:

- **Admin**:

  Creating, updating and deleting products
  
  Creating, updating and deleting users
  
  Managing users' roles
  
  Deleting orders, having access to its details
  
- **User**:

  Registering
  
  Buying products
  
  Having access to the shopping cart
  
  Creating orders
  
  Viewing user's orders and its details
  
  Viewing user's details and its updating
  
# Configuration:

- **Tomcat (Local)**:

  Deployment — _war_exploded_, context address — _"/"_

- **Database**:

  Run the _configuration code_ from the _database.sql_ file in your RDBMS

  Configure your _connection properties_ in the _/com/internet/shop/util/ConnectionUtil.java_
  
- **Authorizating as an admin**:

  On the _login page_ (_"Sign In"_ button) press the _"Create Admin"_ button
  
  Then type in _"Admin"_ for the _login_ and _"Admin007"_ for the _password_ on the login page

- **Authorizating as a user**:

  Register at first — press the _"Sign Up"_ button on the login page or navigation bar
  
  Then sign in on the login page (_"Sign In"_ button)

![Home Page](https://i.imgur.com/JjQh5KP.png)

![Login Page](https://i.imgur.com/jaPWeaJ.png)
