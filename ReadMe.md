# NFL Roster App - Dockerized Full-Stack Application

This project is a fully containerized full-stack application consisting of a PostgreSQL database, a SpringBoot application for the backend, and a React.js Vite frontend. The entire stack is built and can run locally using Docker Compose.

## Prerequisites

Required 
You only need Docker Desktop!

### Docker Destop includes Docker Compose that is also needed
#### You can install it from these sources
  - macOS: https://www.docker.com/products/docker-desktop/
  - 
    Windows: https://www.docker.com/products/docker-desktop/
  - 
    Linux: https://docs.docker.com/engine/install/
  
#### To verify the installation 
In your terminal you can type in the commands 

    docker --version 

    docker compose version

### Notes:
-You don't need PostgreSQL installed locally

-You don't need Java, Node.js, or npm installed

-Docker handles:

-Database creation and population 

-Backend compilation and execution

-Frontend build and development server

-Database is automatically initialized using versioned SQL files on first startup

## Build

1. Open your terminal
2. cd into the root file into which you pulled the source code from my Github repository
3. In the root directory run the command


    docker compose up --build

4.After the build is finished you are able to access the application
5.You are also able to view the three containers postgres,nfl-backend and demo-frontend 

## Access to the webapplication

Once all the containers are running we can access the website on 
http://localhost:5173

The backend API is on 
http://localhost:8080

An example of a API Endpoint 
http://localhost:8080/api/v1/player

On the http:http://localhost:5173 you can find the website with all its features that include 
searching players based on team, position, team and position and via search bar looking up the players first & last name.

The application allows fans, nfl staff, and fantasy league fans to quickly look up their favourite club or player, as well 
as looking up the position that their fantasy team is missing.

If you want to check the database running in its docker container you could do so using the terminal.
We can count all data units with the terminal command 

    docker compose exec db psql -U antoniomajstorovic -d nfl_2025 
    -c "SELECT COUNT (*) FROM player_data"

In this mean we execute psql commands straight from the db container without actually installing PostgreSQL locally.

## Remove and Rebuild

If you want to remove the application or reset the application and datbase, you can follow the commands:

Remove the application and database:
    
    docker compose down -v

This command removes the containers and volumes (-v flag)

Reset the application

    docker compose down -v
    docker compose up --build

### What does this achieve

-Stops all contatiners
-Removes the database volume
-Recreates the database from SQL seed files
-Rebuilds backend and frontend images

## Additional Notes

-Database schema and seed data is located in:
    
    -docker/init/
-SQL files are executed automatically by PostgreSQL on first startup
-The setup is fully reproducible on any machine
-This project is suitable for:
    
    -Technical review
    -Local development
    -Portofolio Demonstration
