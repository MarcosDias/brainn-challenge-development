# Project with Java, Spring (Boot) and Postgres

## Run

Basic execution

`$ ./mvnw clean install dockerfile:build`

and

`$ docker-compose up --build`

In the flux above, in addition to having the docker/docker-compose installed will be installed maven for the project build locally, ie outside the Docker.

To run only the docker compose, without needing anything else, update the file docker-compose.yml, and uncomment the line:

`# dockerfile: no-dependencies.dockerfil`

and run

`$ docker-compose up --build`
