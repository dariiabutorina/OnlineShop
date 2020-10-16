# Online Shop: TOP STORE

A high-quality cosmetics online store

Simple **web-site**, based on the **RDBMS MySQL** 

(MySQLWorkbench was used during creating the project) using **JDBC**

(There was also implemented a plain implementation with the Storage class, without using JDBC)

The inner structure was created according to **N-Tier architecture** and **SOLID principles**

**RBAC** was chosen as the authorization mechanism managed with **filters**

**Stateful app** (authentication —> session with the id)

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

  **Deployment** — war_exploded, context address — "/"

- **Database**:

  Run the configuration code from the **/resources/database.sql** file in your RDBMS

  Configure your connection properties in the **/util/ConnectionUtil.java**
  
- **Authorizing as an admin**:

  On the **login page** ("Sign In" button) press the "Create Admin" button
  
  Then type in "Admin" for the **login** and "Admin007" for the **password** on the login page

- **Authorizing as a user**:

  **Register** at first — press the "Sign Up" button on the login page or navigation bar
  
  Then **sign in on** the login page ("Sign In" button)
  
# Author:

[Dariia Pikul](https://github.com/DariiaPikul)
  
# Preview:

![Home Page](https://i.imgur.com/JjQh5KP.png)

![Login Page](https://i.imgur.com/jaPWeaJ.png)

![All Products (Admin) Page](https://i.imgur.com/HgPEuZN.png)
