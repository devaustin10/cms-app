# Contact Management System App

Welcome to the Contact Management System App! This application is designed to help you manage your contacts effectively. It consists of a Spring Boot backend and an Angular frontend, along with a MySQL database for storing contact information.

## Features

- Add, edit, and delete contacts.
- View a list of contacts with their details.
- Search for specific contacts by name or other criteria.

## Technologies Used

- Backend:
    - Spring Boot with Spring MVC for RESTful API development.
      - API's tested using PostMan
    - Spring JDBC for database connectivity.
    - MySQL database for data storage.
    - JUnit for unit testing.

- Frontend:
    - Angular framework for building the user interface.
    - Angular CLI for project management and deployment.
    - Bootstrap for responsive and visually appealing styling.

## Prerequisites

Before you begin, ensure you have met the following requirements:
- Install Node.js and npm for the Angular frontend.
- Set up the MySQL database and create necessary schemas using MySQL Workbench.
- Have Java and Spring Boot set up for the backend development.
- Configure the necessary database connection properties in the Spring Boot application.

## Installation and Usage

1. Clone the repository to your local machine.
2. Navigate to the `backend` directory and run the Spring Boot backend application.

cd backend
./mvnw spring-boot:run

3. In a separate terminal, navigate to the `frontend` directory and install the frontend dependencies.

cd frontend
npm install

4. Start the Angular development server.

ng serve

5. Open your web browser and access the application at `http://localhost:4200`.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, feel free to create a pull request or an issue in this repository.

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgments

- This project was developed as a demonstration of integrating Spring Boot and Angular frameworks.
- Thanks to the Spring Boot and Angular communities for their valuable resources and documentation.

