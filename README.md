

Welcome to the Event Social API, a Spring Boot-based side project that provides a RESTful API for managing social events. The project uses PostgreSQL as the relational database.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Configuration](#configuration)
  - [Installation](#installation)
- [API Endpoints](#api-endpoints)
- [Technologies](#technologies)
- [Contributing](#contributing)
- [License](#license)

## Overview

The Event Social API is designed to facilitate the planning, organization, and participation in social events through a RESTful architecture. Whether it's a casual meetup, a conference, or a party, this API provides endpoints for event management.


## Getting Started

### Prerequisites

Make sure you have the following installed:

- Java Development Kit (JDK) 11 or later
- Maven
- PostgreSQL

### Configuration

Configure the database connection in `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/event_social_app
spring.datasource.username=your_username
spring.datasource.password=your_password
```
Installation
Clone the repository:

```bash
Copy code
git clone https://github.com/your-username/event-social-api.git
Build and run the application:
```
```bash
Copy code
cd event-social-api
mvn clean install
java -jar target/app.jar
The API should be accessible at http://localhost:8080.
```

Technologies
Spring Boot: Backend framework for building Java-based applications.
Spring Security: Provides authentication and authorization support.
PostgreSQL: Relational database for storing user and event data.
Maven: Build and dependency management tool.
