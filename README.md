Prime Number Selector Microservices
Project Overview
This project consists of two microservices:

Producer: Generates random numbers and sends them to a stream also writes them to a CSV file.
Consumer: Receives the numbers, identifies prime numbers, and writes them to a CSV file.

Prerequisites
Java 17 or later
Docker installed on your machine
Git installed on your machine
Instructions
1. Clone the Repository
Clone the project repository to your local machine:
2. Build the Microservices
Before running the microservices, you need to build the Producer and Consumer projects. Navigate to the root directories of each microservice and build them using Gradle.
3. Running the Microservices with Docker Compose
Once the projects are built, navigate back to the root directory of the repository and start all the microservices using Docker Compose:
docker-compose up --build

4. Verifying the Output
Producer: Will generate random numbers and write them to a data.csv file located in app/files directory.
Consumer: Will consume these numbers, identify the prime numbers, and write them to a data.csv file located in app/files directory.
Both CSV files will be located in their respective service directories. You can verify the results by checking these files.

5. Running Tests

6. Stopping the Services
To stop the microservices, press CTRL+C in the terminal where Docker Compose is running. To clean up the containers, run:
docker-compose down

7. Additional Notes
Ensure Docker is running before you start the services.
If you make any changes to the code, rebuild the services before running them again.