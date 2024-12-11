Contact List CRUD Application using JDBC
This is a simple CRUD application for managing a contact list, developed in Java using JDBC for database interaction. The application allows users to manage contact groups and individual contacts, including adding, updating, deleting, and viewing them.

Features

Contact Groups: Create new contact groups (e.g., "Family", "Friends", "Work").
Manage Contacts: Add, view, update, and delete contacts with name, email, phone number, and group association.
Search Contacts: Search for contacts by ID.
Error Handling: Input validation to ensure data integrity and handle invalid data.
Database Integration: Uses JDBC to interact with a SQL database to store contacts and groups.
Getting Started

These instructions will help you set up and run the project on your local machine.

Prerequisites

Java 8 or higher installed on your machine.
A SQL database (e.g., MySQL) to store contact and group data.
Installation

Clone the repository:
Bash

git clone https://github.com/yourusername/contact-list-crud-jdbc.git
Navigate to the project directory:
Bash

cd contact-list-crud-jdbc
Set up the database:
Create a SQL database and configure the JDBC connection settings in the project.
Run the necessary SQL scripts to create the database schema.
Compile and Run

Compile the Java code:
Bash

javac Application/Program.java
Run the application:
Bash

java Application.Program
Usage

The application provides a console interface for managing contacts and groups. You can perform actions like:

Add Contact Group
Add Contact
View Contacts
Update or Delete Contacts
View Groups
Project Structure

The project is organized into the following folders:

Application: Contains the main class (Program) where users interact with the console.
Entities: Contains the Contact and Group classes which define the properties and structure for contacts and groups.
DAO: Contains the ContactDAO and GroupDAO classes for interacting with the database.
Contact

Created by Thiago Batista Coutinho. Feel free to reach out for any questions, feedback, or collaboration!
