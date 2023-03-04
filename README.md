# Ktor API - A sample Ktor API

Sample project, just to show how to use Ktor with a simple API.
The API has 1-2 GET endpoints which just print Hello World and 
or retrieve a User from a database (Postgres) and a POST endpoint
which creates a new User in the database.


### To fix

- jackson for Kotlin doesn't work well with LocalDateTime


## Usage

### Run the application

```bash
./gradlew run
```
**Notes**: A variable called `TEST_PSQL_URL_KTOR` with the JDBC URL of the database must be set in the environment.
