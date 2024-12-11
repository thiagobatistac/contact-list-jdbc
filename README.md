# 📇 Contact List CRUD Application using JDBC

This is a simple CRUD application for managing a contact list, developed in Java using **JDBC** for database interaction. The application allows users to manage contact groups and individual contacts, including adding, updating, deleting, and viewing them.

## ✨ Features

- **Contact Groups**: Create new contact groups (e.g., "Family", "Friends", "Work").
- **Manage Contacts**: Add, view, update, and delete contacts with name, email, phone number, and group association.
- **Search Contacts**: Search for contacts by ID.
- **Error Handling**: Input validation to ensure data integrity and handle invalid data.
- **Database Integration**: Uses JDBC to interact with a SQL database to store contacts and groups.

## 🚀 Getting Started

These instructions will help you set up and run the project on your local machine.

### 🛠️ Prerequisites

- **Java 8 or higher** installed on your machine.
- A **SQL database** (e.g., MySQL) to store contact and group data.

### 📥 Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/contact-list-crud-jdbc.git
   ```
   
2. **Navigate to the project directory**:
   ```bash
   cd contact-list-crud-jdbc
   ```

3. **Set up the database**:
   - Create a SQL database and configure the JDBC connection settings in the project.
   - Run the necessary SQL scripts to create the database schema.

4. **Compile and run**:
   - Compile the Java code:
     ```bash
     javac Application/Program.java
     ```
   - Run the application:
     ```bash
     java Application.Program
     ```

## 📚 Usage

- **Add Contact Group**: Create a new contact group (e.g., "Family", "Work").
- **Add Contact**: Add a contact with name, email, phone number, and group ID.
- **View Contacts**: View all contacts or search for a specific contact by ID.
- **Update or Delete Contacts**: Update or delete contact information as needed.
- **View Groups**: View all groups or search for specific groups.

## 📂 Project Structure

- **Application**: Contains the main class (`Program`) where users interact with the console to manage contacts and groups.
- **Entities**: Contains the `Contact` and `Group` classes which define the properties and structure for contacts and groups.
- **DAO**: Contains the `ContactDAO` and `GroupDAO` classes for database interactions.

---

## 👤 Contact

Created by **Thiago Batista Coutinho**.  
Feel free to reach out for any questions, feedback, or collaboration!


