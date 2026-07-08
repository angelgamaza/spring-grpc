# Spring Boot gRPC - Basic Example

A practical Spring Boot example that demonstrates how to build and run a gRPC server and client with Spring Boot 3.4.3, PostgreSQL and protobuf-based contracts.

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.3-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-%23C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-%23CC0200?style=for-the-badge&logo=flyway&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![gRPC](https://img.shields.io/badge/gRPC-4285F4?style=for-the-badge&logo=trpc&logoColor=white)

This project provides a basic example about how to use gRPC with Spring Boot 3.4.3. The application exposes a gRPC server with the defined methods and types in the `schema.proto`
file inside the `grpc-proto` module.

The project also includes a comprehensive Docker setup (multi-stage Dockerfile and docker-compose) to build and run the database, the gRPC server and the sample gRPC client
locally. The server is exposed on port `8080` and PostgreSQL on port `5432`.

## 📦 Why this Project Exists

- It provides a clear example of integrating Spring Boot with gRPC.
- It demonstrates how to define protobuf contracts and generate server/client stubs from them.
- It offers a simple local infrastructure that can be fully run with Docker Compose (Database, Server and Client).

## 🎯 Key Features

- gRPC server and sample client applications.
- Protobuf-based service definitions and message schemas.
- PostgreSQL support for the backend infrastructure.
- Dockerized local environment with multi-stage builds for quick setup and testing.
- Native gRPC health checks using `grpc_health_probe`.

## 📂 Project Structure

The codebase is organized into a small set of modules that make the flow easy to follow:

- `grpc-proto` for the protobuf definitions and generated stubs.
- `grpc-server` for the gRPC server implementation.
- `grpc-client` for a sample client that calls the server.
- `Dockerfile` (Multi-stage) to compile and build lightweight runtime images for both the server and client.
- `docker-compose.yml` to orchestrate the PostgreSQL database, the gRPC server and the gRPC client.

## 🚀 Getting Started

### Prerequisites

To run this application, you must have the following installed:

- Docker and Docker Compose.
- Java 21+ (Optional, if you want to run outside of Docker).
- Maven 3.9.3+ (Optional, if you want to build outside of Docker).

### Run the Application with Docker Compose (Recommended)

The entire infrastructure, including the database, server and client, is containerized.

1. Start the database and the gRPC server in detached mode:

```bash
docker-compose up -d --build postgres grpc-server
```

*Note: The server container includes a native gRPC health check that ensures it is fully ready before accepting traffic.*

2. Once the server is healthy, execute the sample gRPC client to test the server methods:

```bash
docker-compose up --build grpc-client
```

This will start the client container, which will call the server and perform several method invocations, showing an output like the following in your terminal:

```bash
2025-03-06T17:32:14.396+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : Getting all Directors...
2025-03-06T17:32:14.980+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : [716a2c3b-88cc-4ab8-b32d-f48455009ea1] Steven Spielberg
2025-03-06T17:32:14.981+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : [65928b6d-0fc9-4a92-8a36-bf652012b8c7] George Lucas
2025-03-06T17:32:14.981+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : [4b62fc13-4422-42bc-a387-43034a6a1e29] James Cameron
2025-03-06T17:32:14.981+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : [17949569-7ca0-461c-a161-cf5e705e0a3a] Quentin Tarantino
2025-03-06T17:32:14.981+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : [f604a7e7-1446-4813-af04-984161c34c59] Christopher Nolan
2025-03-06T17:32:14.982+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : [13220b2f-e5d4-4733-bdc0-d29a274488f1] Angelin Test
2025-03-06T17:32:14.982+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : Getting all USA Films...
2025-03-06T17:32:15.040+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : [d0e6f5c0-8aaf-4e16-a699-10b180a6b293] Jurassic Park [USA]
2025-03-06T17:32:15.040+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : [eef64214-6ab7-4c10-aaae-738d5a2eacfb] Jaws [USA]
2025-03-06T17:32:15.040+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : [9935687e-5299-4503-905e-6a0c299468fc] Indiana Jones [USA]
2025-03-06T17:32:15.040+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : [85870af4-bf2d-43cc-bb1e-fc633a74c7b8] E.T. [USA]
2025-03-06T17:32:15.040+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : [1690ac0f-c74e-43ca-8505-9a18b19b0756] Star Wars [USA]
2025-03-06T17:32:15.040+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : [7d841160-8894-4621-92d5-dbcfeddb3862] The Empire Strikes Back [USA]
2025-03-06T17:32:15.040+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : [8eddff8a-7e57-476a-b6f2-7a36821b0855] Return of the Jedi [USA]
2025-03-06T17:32:15.040+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : [f9b3a215-5057-48ad-b871-a36eb643603f] The Terminator [USA]
2025-03-06T17:32:15.041+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : [32f5b451-bc9e-40bb-9c07-1b838e3ccfce] Aliens [USA]
2025-03-06T17:32:15.041+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : [e99c6944-5e3a-4e54-9ea2-50d12bc4d18a] Titanic [USA]
2025-03-06T17:32:15.041+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : [ec46162b-4266-4ca4-a2fa-7bcacf9835f0] Pulp Fiction [USA]
2025-03-06T17:32:15.041+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : [91598bfe-481a-4fa3-8914-3e45a29fbdb2] Kill Bill [USA]
2025-03-06T17:32:15.041+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : [d6e253bc-3635-4233-94e5-7a4e616d08ce] Django Unchained [USA]
2025-03-06T17:32:15.041+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : [d12d1780-4cec-4c8b-bb15-4b371652d1ee] Inception [USA]
2025-03-06T17:32:15.041+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : [c41a1d8a-5a6b-429c-89d1-ac68b1f55425] Interstellar [USA]
2025-03-06T17:32:15.041+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : Getting Film with name: Pulp Fiction...
2025-03-06T17:32:15.099+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : [ec46162b-4266-4ca4-a2fa-7bcacf9835f0] Pulp Fiction [USA] from [Quentin Tarantino]
2025-03-06T17:32:15.099+01:00  INFO 13428 --- [grpc-client] [           main] c.g.e.g.g.GrpcClientApplication          : ---------------------------------------------
```

3. To stop all running containers and clean up the environment, execute:

```bash
docker-compose down
```

### Run the Application Locally (Without Dockerizing the Apps)

If you prefer to run the Java applications natively from your IDE or terminal:

1. Install the generated stubs and build the modules:

```bash
mvn clean install
```

2. Start **only** the database container:

```bash
docker-compose up -d postgres
```

3. Start the gRPC server:

```bash
java -jar grpc-server/target/grpc-server-1.0.0.jar
```

4. Start the gRPC client:

```bash
java -jar grpc-client/target/grpc-client-1.0.0.jar
```

## 📚 gRPC Reference

In order to try the gRPC application, you must use the URL http://localhost:8080, where the gRPC server will provide the methods to invoke.

### Methods

The gRPC server provides the methods:

```protobuf
service FilmService {

  // Save a film
  rpc save(FilmPostDto) returns (FilmDto);

  // Find one film by its ID
  rpc findById(StringValue) returns (FilmRelationsDto) {}

  // Find one film by its name
  rpc findByName(StringValue) returns (FilmRelationsDto) {}

  // Find all films
  rpc findAll(Empty) returns (FilmsList) {}

  // Find all films by its country
  rpc findAllByCountry(StringValue) returns (FilmsList) {}

  // Find all films by its director id
  rpc findAllByDirectorId(StringValue) returns (FilmsList) {}

}

service DirectorService {

  // Save a director
  rpc save(DirectorPostDto) returns (DirectorDto);

  // Find one director by its ID
  rpc findById(StringValue) returns (DirectorRelationsDto) {}

  // Find all directors
  rpc findAll(Empty) returns (DirectorsList) {}

}
```

#### Usage

The gRPC client is provided to test the gRPC server methods invocation, but the methods can also be invoked by Postman.

A guide about how to do it can be found at: https://learning.postman.com/docs/sending-requests/grpc/grpc-request-interface/.

## 👨‍💻 Author

Ángel Gamaza - angel.gamaza@gmail.com