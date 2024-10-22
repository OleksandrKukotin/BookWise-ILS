# BookWise ILS
## About
A simple integrated library system for dooks/documents management in institutions provides such a service. Requirements and idea based on my coursework project in Java for WEB course of 13th season of GeekHub courses.
## Requirements
### **Technologies:**
- Java 17+
- Spring Boot, Security, JDBC
- Gradle: 8.6
- JUnit5, AssertJ, TestContainers, Mockito
- PostgreSQL

### **Functionality:**
- Authentication
    - Users should be able to register with the application: ✅
        - Users should input email, username, password, and password confirmation. ✅
        - The email/username must be unique, and not contain any special symbols (like `’=-\_/?|\][,<.>”&^%$#№!). ✅
        - Passwords should be securely stored using appropriate encryption techniques. ✅
        - If email and username are not used, register a new user and automatically log in. ✅
    - Users should be able to log in: ✅
        - User can use their username/email and password to login to the system ✅
    - Users should be able to reset their passwords if forgotten. ✅
        - The recovery code/link should be sent to the user's email. ✅
    - Support Remember-me feature: ✅
        - Implement remember-me functionality for persistent user sessions ✅
        - Allow users to enable or disable the remember-me functionality during login ✅
        - Remember-me token increases inactivity time. When the user logs in with the checked remember-me flag - the logout will be done only in 7 days of inactivity. ✅
    - Support Log-out feature: ✅
        - When user authenticated, it should be able to log-out ✅
        - Users should be automatically logged out after 2 hours of inactivity on the application. ✅
- Authorization
    - Define multiple user roles with different levels of access and permissions: ✅
        - Implement role-based access control (RBAC) for securing application resources. ✅
        - Support Role hierarchy (ex. User < Admin < Super Admin)  ✅
    - Create an admin interface:
        - Restrict access to the admin panel to users with appropriate roles. ✅
        - Implement features such as user management, role assignment, resource management, etc. ✅
        - Admin can reset the user password. Email must be sent to the user's mail.
        - Super Administrator can make regular user admin and backward ✅
        - Super Administrator can exist only in one instance in the system. This role cannot be assigned to anyone else.
        - If your system has additional roles - they can be assigned from the admin panel.
- Your code must follow:
    - Business logic must cover everything we learned during this course. (except Reflection and Concurrency).
    - Code Quality:
        - Achieve a minimum test coverage of 85% for all critical components.
        - Write unit tests using JUnit and Mockito for all business logic.
        - Write integration tests to validate interactions between components.
        - Pipelines are green! ✅
    - Exceptions
        - Use custom exception classes for different types of errors.
        - Provide meaningful error messages and appropriate HTTP status codes in responses.
        - Exception handlers should be ready for any backend error. Users never should see a white screen or infinite redirect. Register custom error-pages
    - Structure:
        - Organize the application into layers such as presentation, service, and data access. ✅
        - Ensure separation of concerns between layers for better maintainability. ✅
        - Write clean and modular code following coding best practices.
        - All API endpoints must be in the “rest-api” gradle module. Business logic must be in the ”domain” module
    - API:
        - WEB layer should expose at least one endpoint for each of GET, POST, PUT, DELETE http methods. ✅
        - GET-all API should support pagination.
        - Data must be passed via request bodies, path parameters, and path variables.
    - Data integrity:
        - Implement server-side validation for all incoming data.
        - Prevent common security vulnerabilities such as SQL injection and cross-site scripting (XSS). ✅
    - IO/NIO:
        - Implement file upload and download functionality.
    - Configuration:
        - Use annotations and properties to create beans and configure applications. ✅
        - Dev and Prod profiles are implemented
        - The database structure is generated by Flyway migrations. Super Admin inserted via migration. ✅



### **Additional Requirements:**
- User interface to interact with the application. **Login, Register, and password recovery pages are required!!!** For other endpoints, Swagger will be enough. ✅
- Use a logging framework such as Log4j or SLF4J for centralized logging.
