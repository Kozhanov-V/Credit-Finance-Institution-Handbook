# Credit-Finance-Institution-Handbook 📖
## Description 🏻

This is a web application for administering reference information about credit finance institutions. It includes functionality for adding, editing, and deleting records through the user interface, as well as a mechanism for batch uploading and updating reference data based on the information provided by the Central Bank of Russia. The application provides API access for "external" services to reference information and its search.

## Technologies

- Backend:
  - Spring Boot 
  - Spring MVC 
  - Spring Data 
- Database:
   - .h2 
- Frontend:
  - HTML
  - CSS
  - Vue.js
## Implementation Details ℹ️

- The API is described using Swagger (http://localhost:8080/swagger-ui.html).
- The application has a configurable logger.
- The interface is user-friendly (warnings, messages, highlights).
- It avoids the problem of duplicate clicks to delete/save/update in case of "freezing" of the network/server and other factors.
- Problems in the application (backend or front) should not stump the user (it is unacceptable to display a white screen, server errors in the form of Stacktrace, codes 400-500, and others).

## Installation & Running the App with Docker 🏃‍♂️
1. Clone the repository from GitHub.
   
   ```
   git clone https://github.com/Kozhanov-V/Credit-Finance-Institution-Handbook.git
   ```
2. Navigate into the project directory.
   ```
   cd Credit-Finance-Institution-Handbook
   ```
3. Build and run the docker image.
   ```
   docker-compose up --build
   ```
   Now, you should be able to access the application at localhost:5173.
  
## Screenshots 💻
### Home Page
![Home Page](/screenshots/home.jpg)
### Handbook page
![Handbook Page](/screenshots/main.png)
### Favorites entry page
![Favorites Page](/screenshots/favorites.png)
### Login Page
![Login Page](/screenshots/login.png)
### Import Page
![Import Page](/screenshots/import.png)

## Conclusion
We hope that you find this repository useful for understanding and exploring credit finance institution management. Feel free to use, fork, star, or contribute to this project. Your suggestions, issues, and pull requests are welcome. If you have any questions, don't hesitate to reach out. Enjoy using this project!

Thank you for visiting!

Happy coding!
