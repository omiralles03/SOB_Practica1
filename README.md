# SISTEMES OBERTS PRACTICA 1 i 2

## 📚 Project Overview
This repository contains the implementation of a web application for managing and publishing articles. The project is divided into two main parts: frontend and backend, following the **Model-View-Controller (MVC)** architecture. The application supports user authentication, article creation and management, and filtering capabilities.

### Key Features:

- **User Registration and Login:** Secure authentication for users.
- **Article Management:** Create, view, delete, and filter articles based on authors and topics.
- **RESTful API:** A backend API built using Jakarta EE to manage all interactions between the client and server.
- **Dynamic Frontend:** Built using JSP and TailwindCSS for responsive design and seamless user experience.

---

## 🗂️ Project Structure
### **1. Backend**
The backend follows a RESTful approach and provides APIs for managing users, articles, and authentication. It handles:
- **Business Logic**: Ensures proper validation and authorization for all operations.
- **Persistence**: Connects to the database to fetch or store data for articles and users.

#### Key Packages
- **Authn:** Handles JWT token generation and verification to secure user authentication.
- **Model:** Java classes representing database entities, mapping data directly to and from the database using JPA.
- **Service:** Contains methods that interact with the database, process data, and provide responses to API calls.

### **2. Frontend**
The frontend is built using JSP pages and TailwindCSS. It handles:
- **Dynamic Content Rendering**: Fetching and displaying data from the backend APIs.
- **Interactive User Interfaces**: Dynamic grids, modals, and responsive layouts for better usability.

#### Key Packages
- **Controllers:** Act as intermediaries between the frontend and backend. They receive data from backend services and pass it to JSP views for rendering.
- **Model:** Java classes used to store and manage data received from the backend APIs (deserialized JSON responses).
- **Service:** Makes API calls to the backend using RESTful endpoints, retrieves data, and sends it to the controllers for further processing.
- **WEB-INF:** Houses JSP files that dynamically render content for users.

- #### Key Folders
- **`layout`**: Contains reusable JSP components like navbars, footers, and sidebars.
- **`pages`**: Full-page templates for user interactions (e.g., home, profile, users).

---

## 🛠️ **How It Works: Communication Between Layers**

### **Frontend → Controller**
1. **User Actions**: When a user performs an action (e.g., clicking a button or submitting a form), a request is sent to the corresponding controller.
2. **Routes**: The controllers define the routes using `@Path` annotations (e.g., `/Auth/login`, `/Article`).
3. **Example**: When accessing `/Users`, the `UsersController` fetches all users and passes them to the `users.jsp` view.

### **Controller → Service**
1. **Delegation**: The controller delegates complex logic to the corresponding service layer.
2. **Business Logic**: The service processes data and interacts with the database through the model.

**Example:**
```java
List<User> users = userService.getAllUsers();
```
calls the `UserService`, which queries the database to fetch user data.

### **Service → Model**
1. **Persistence**: The service communicates with the database using the EntityManager and queries.
2. **DTOs**: Data is transformed into DTOs before being sent to the view, ensuring security and flexibility.

---

## 📑 **Endpoints**

### **1. Authentication**
- `POST /Auth/login`: Logs in a user.
- `POST /Auth/register`: Registers a new user.

### **2. Users**
- `GET /Users`: Returns a list of all users.

### **3. Articles**
- `GET /article`: Fetches all articles.
- `GET /article/{id}`: Fetches a single article by ID.
- `GET /article/search`: Fetches articles filtered by query, topics, or author.

---

## 🚀 **How to Run the Project**

### Prerequisites
- **JDK 17.0.4.1**
- **NetBeans** or another compatible IDE.
- **GlassFish (6.2.1)** server (for Jakarta EE).
- **Database**: Derby JDBC (configured in `persistence.xml`).

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/omiralles03/SOB_Practica1.git
   ```
2. Open the project in your IDE and configure the Java DB and GlashFish Server.
3. Create a new database named homework1 with name and password `root`, `ROOT` or set it up however you like in `persistence.xml`.
4. Connect to the database and launch GlassFish server.
5. Make sure to change the frontend PORTS in services to your GlassFish Server (By default `8080`, in this project `12521`).
6. Clean & Build and Deploy the backend on the GlassFish server.
7. Insert the data to the database with the jsp.install launched in your browser.
8. Clean & Build and Deploy the front on the GlassFish server.
9. Start the application and access it at:
   ```
   http://localhost:<port>/Homework2/Web
   ```

---

## 🧪 **Testing**
The project has been tested with multiple scenarios:
- Valid and invalid user login.
- Creating, viewing, and deleting articles.
- Filtering articles using combinations of topics and authors.
- Using the `POSTMAN` Tool to verify the endpoints.

---

## 📄 Documentation
For a detailed explanation of the project, its architecture, and implementation, please refer to the [Practica 1 Documentation](https://github.com/omiralles03/SOB_Practica1/blob/main/documentacioPractica2.pdf) and [Practica 2 Documentation](https://github.com/omiralles03/SOB_Practica1/blob/main/documentacioPractica2.pdf).
