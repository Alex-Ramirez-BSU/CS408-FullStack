![CI](https://github.com/Alex-Ramirez-BSU/CS408-FullStack/actions/workflows/ci.yml/badge.svg)
# Trail Tracker

***CS408 FullStack***<br>
Trail Tracker is a simple Spring Boot web application that allows users to record, view, and analyze trails they have explored.

## Features

- Add new trails with details such as name, date, location, distance, and rating
- View a list of all trails
- Filter trails by difficulty, rating, or name
- View detailed information for each trail
- Edit and delete existing trails
- View statistics such as total distance, average rating, and trails per month

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Thymeleaf
- H2 Database
- Bootstrap

## Running the Application

1. Clone the repository
2. Navigate to the project folder
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Open your browser and go to:
    ```bash
   http://localhost:8080
    ```

## Database

- Uses H2 database (file-based or embedded depending on configuration)
- Schema and sample data are initialized on startup using:
    - `schema.sql`
    - `data.sql`

## Project Structure

- `model` – Contains the Trail entity
- `repository` – Handles database operations
- `controller` – Handles routing and application logic
- `templates` – Thymeleaf HTML pages
- `static` – CSS and other static assets

## Notes

- Rating is constrained between 0–5
- Difficulty is user-defined (e.g., Easy, Medium, Hard)
- Data persistence depends on H2 configuration

## Future Improvements

- User authentication (login/signup)
- Switch to PostgreSQL or MySQL
- Image uploads for trails
- Map integration  