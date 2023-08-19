Repository (Data) Layer Testing:

Focuses on interactions with the database and ensuring that data is being read, written, and updated correctly.
Positive tests at this layer validate that data is being stored, retrieved, and manipulated as expected.
Negative tests at this layer might focus on database constraints, handling exceptions, and ensuring that improper data operations are appropriately prevented.
However, extensive negative testing related to business logic and validations should ideally be left to higher layers, such as the service layer.

Service Layer Testing:

Focuses on business logic, application rules, and orchestrating interactions between various components.
Positive tests at this layer validate that the service methods produce the expected results when given valid inputs and when following the application's business rules.
Negative tests at this layer are particularly important for testing how your application handles invalid or unexpected inputs, as well as how it responds to various error conditions. This includes testing scenarios like validation failures, business rule violations, and exception handling.

Controller (Presentation) Layer Testing:

Focuses on the interactions with the outside world, such as HTTP requests and responses.
Positive tests ensure that your endpoints or APIs correctly handle requests and return appropriate responses.
Negative tests at this layer can include testing error handling and edge cases related to input validation and response codes.