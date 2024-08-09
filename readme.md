
```markdown
# MongoDB Docker Setup with Joins

This repository contains a Docker setup for MongoDB with sample data and views demonstrating different types of joins. The setup includes:

- Docker Compose file for MongoDB
- Initialization script that sets up collections, inserts sample data, and creates MongoDB views
- Examples of various MongoDB join operations

## Prerequisites

- Docker
- Docker Compose

## Getting Started

### 1. Clone the Repository

```bash
git clone <repository-url>
cd <repository-directory>
```

### 2. Build and Run the Docker Container

To build and run the Docker container with MongoDB, use Docker Compose. The `docker-compose.yml` file defines the service and configuration.

```bash
docker-compose up --build
```

This command will:
- Build the Docker image using the `Dockerfile` provided
- Start a MongoDB container with the specified configuration

### 3. Initialization Script

The `init-mongo.js` script is used to:
- Insert sample data into the `employees` and `departments` collections
- Create MongoDB views demonstrating different types of joins

The script performs the following operations:

- **Sample Data Insertion**:
    - Adds sample data to the `employees` and `departments` collections.

- **View Creation**:
    - `inner_join_view`: Performs an INNER JOIN between `employees` and `departments`.
    - `left_join_view`: Performs a LEFT JOIN between `employees` and `departments`.
    - `right_join_view`: Performs a RIGHT JOIN between `departments` and `employees`.
    - `full_join_employees_view`: Simulates a FULL JOIN with data from `employees`.
    - `full_join_departments_view`: Simulates a FULL JOIN with data from `departments`.
    - `cross_join_view`: Performs a CROSS JOIN between `employees` and `departments`.
    - `self_join_view`: Performs a SELF JOIN to link employees with their managers.

### 4. Access MongoDB

MongoDB will be available on port 27017 of your local machine. You can connect to it using a MongoDB client or via the MongoDB shell:

```bash
mongo --host localhost --port 27017
```

### 5. Querying Views

Once MongoDB is running and the initialization script has executed, you can query the views to see the results of the joins:

```javascript
// Query INNER JOIN view
print("INNER JOIN result:");
printjson(
  db.inner_join_view.find().toArray()
);

// Query LEFT JOIN view
print("LEFT JOIN result:");
printjson(
  db.left_join_view.find().toArray()
);

// Query RIGHT JOIN view
print("RIGHT JOIN result:");
printjson(
  db.right_join_view.find().toArray()
);

// Query FULL JOIN views
print("FULL JOIN Employees result:");
printjson(
  db.full_join_employees_view.find().toArray()
);

print("FULL JOIN Departments result:");
printjson(
  db.full_join_departments_view.find().toArray()
);

// Query CROSS JOIN view
print("CROSS JOIN result:");
printjson(
  db.cross_join_view.find().toArray()
);

// Query SELF JOIN view
print("SELF JOIN result:");
printjson(
  db.self_join_view.find().toArray()
);
```

### 6. Stopping the Container

To stop and remove the Docker container, use:

```bash
docker-compose down
```

Certainly! Here's a `README.md` file tailored to the Scala project that demonstrates how to use MongoDB with Scala, including the code you provided. This `README.md` will include setup instructions, dependencies, and usage instructions for the provided Scala code.

```markdown
# MongoDB Scala Integration with Joins

This project demonstrates how to use MongoDB with Scala to perform various types of join operations using the `org.mongodb.scala` library. It includes:

- A `DBUtils` trait and object to manage MongoDB connections.
- Utility methods for printing results.
- Scala code examples for different types of joins: INNER JOIN, LEFT JOIN, FULL JOIN, CROSS JOIN, and SELF JOIN.
- Docker setup to run MongoDB.

## Prerequisites

- Java 8 or later
- Scala 2.13 or later
- SBT (Scala Build Tool)
- Docker (optional, for running MongoDB in a container)

## Project Setup

### 1. Clone the Repository

```bash
git clone <repository-url>
cd <repository-directory>
```

### 2. Setup Dependencies

Ensure that your `build.sbt` file includes the necessary dependencies:

```scala
libraryDependencies ++= Seq(
  "org.mongodb.scala" %% "mongo-scala-driver" % "4.5.0" // Adjust version as needed
)
```

### 3. Docker Setup (Optional)

If you want to use Docker to run MongoDB, ensure that Docker is installed and then use the provided `docker-compose.yml` to set up MongoDB:

#### Dockerfile

```dockerfile
# Use the official MongoDB image from Docker Hub
FROM mongo:latest

# Copy the initialization script to the container
COPY init-mongo.js /docker-entrypoint-initdb.d/

# Expose MongoDB port
EXPOSE 27017
```

#### docker-compose.yml

```yaml
version: '3.8'

services:
  mongo:
    build: .
    ports:
      - "27017:27017"
    volumes:
      - ./init-mongo.js:/docker-entrypoint-initdb.d/init-mongo.js
    environment:
      MONGO_INITDB_DATABASE: testdb
```

To start MongoDB:

```bash
docker-compose up --build
```

### 4. Initialize MongoDB Data

Ensure MongoDB is running, and then execute the `init-mongo.js` script to set up sample data and views. This script should be placed in the `init-mongo.js` file in the project root:

```javascript
// Use or create the database 'testdb'
db = db.getSiblingDB('testdb');

// Sample data for 'employees' collection
db.employees.insertMany([
  { employee_id: 1, name: "Alice", department_id: 101, manager_id: null },
  { employee_id: 2, name: "Bob", department_id: 102, manager_id: 1 },
  { employee_id: 3, name: "Charlie", department_id: 103, manager_id: 1 }
]);

// Sample data for 'departments' collection
db.departments.insertMany([
  { department_id: 101, department_name: "HR" },
  { department_id: 102, department_name: "Engineering" },
  { department_id: 103, department_name: "Sales" }
]);

// Create views for different joins (similar to the previous script)
```

### 5. Running the Scala Code

Compile and run the Scala code to demonstrate the join operations:

1. **Compile the Scala Project**:

   ```bash
   sbt compile
   ```

2. **Run the Scala Application**:

   ```bash
   sbt run
   ```

Here's a `README.md` file for your Scala project, which includes examples of different types of MongoDB joins using views and aggregation pipelines. This README assumes that the user is familiar with MongoDB and Scala.

```markdown
# MongoDB Joins Example in Scala

## Overview

This Scala project demonstrates how to perform various types of MongoDB joins using the MongoDB Scala Driver. The example includes different types of joins such as inner join, left join, right join, full join, cross join, and self join, utilizing MongoDB aggregation pipelines and views.

## Prerequisites

- Scala 2.13 or higher
- MongoDB 4.2 or higher
- MongoDB Scala Driver

## Project Setup

1. **Clone the Repository**

   ```bash
   git clone https://github.com/your-repo/mongodb-joins-example.git
   cd mongodb-joins-example
   ```

2. **Build the Project**

   Ensure you have [sbt](https://www.scala-sbt.org/) installed and then build the project using:

   ```bash
   sbt compile
   ```

3. **Run the Example**

   Execute the Scala application:

   ```bash
   sbt run
   ```

## Code Overview

### `MongoJoinsCreatedInDBAccessExample.scala`

This Scala application prints out the contents of MongoDB collections and views. It assumes that views like `inner_join_view`, `left_join_view`, `right_join_view`, `full_join_employees_view`, `full_join_departments_view`, `cross_join_view`, and `self_join_view` have been pre-created in the MongoDB database.

```scala
import DBUtils.MongoUtils._
import utils.Utils.printResults

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object MongoJoinsCreatedInDBAccessExample extends App{
  println("===employeesCollection======")
  printResults(employeesCollection.find().toFuture())
  Thread.sleep(100)
  println("===departmentsCollection======")
  printResults(departmentsCollection.find().toFuture())
  Thread.sleep(100)
  println("===inner_join_view======")
  printResults(inner_join_view.find().toFuture())
  Thread.sleep(100)
  println("===left_join_view======")
  printResults(left_join_view.find().toFuture())
  Thread.sleep(100)
  println("===right_join_view======")
  printResults(right_join_view.find().toFuture())
  Thread.sleep(100)
  println("===full_join_employees_view======")
  printResults(full_join_employees_view.find().toFuture())
  Thread.sleep(100)
  println("===full_join_departments_view======")
  printResults(full_join_departments_view.find().toFuture())
  Thread.sleep(100)
  println("===cross_join_view======")
  printResults(cross_join_view.find().toFuture())
  Thread.sleep(100)
  println("===self_join_view======")
  printResults(self_join_view.find().toFuture())
}
```

### `MongoJoinsExample.scala`

This Scala application demonstrates various types of MongoDB joins using aggregation pipelines. It connects to MongoDB, performs different joins, and prints the results.

```scala
import DBUtils.MongoUtils
import org.mongodb.scala._
import org.mongodb.scala.model.Aggregates._
import utils.Utils.printResults

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object MongoJoinsExample extends App {

  val employeesCollection = MongoUtils.employeesCollection
  println("=====employeesCollection=====")
  printResults(employeesCollection.find().toFuture())
  Thread.sleep(1000)
  val departmentsCollection = MongoUtils.departmentsCollection
  println("=====departmentsCollection=====")
  printResults(departmentsCollection.find().toFuture())
  Thread.sleep(1000)

  // INNER JOIN
  val innerJoinResult = employeesCollection.aggregate(Seq(
    lookup("departments", "department_id", "department_id", "department_info"),
    unwind("$department_info"),
    project(Document("name" -> 1, "department_name" -> "$department_info.department_name"))
  )).toFuture()

  println("=====innerJoinResult=====")
  Thread.sleep(1000)
  printResults(innerJoinResult)

  // LEFT JOIN
  val leftJoinResult = employeesCollection.aggregate(Seq(
    lookup("departments", "department_id", "department_id", "department_info"),
    unwind("$department_info"),
    project(Document(
      "name" -> 1,
      "department_name" -> "$department_info.department_name"
    ))
  )).toFuture()

  println("=====leftJoinResult=====")
  Thread.sleep(1000)
  printResults(leftJoinResult)

  // RIGHT JOIN (by reversing roles)
  val rightJoinResult = departmentsCollection.aggregate(Seq(
    lookup("employees", "department_id", "department_id", "employee_info"),
    unwind("$employee_info"),
    project(Document(
      "department_name" -> 1,
      "name" -> "$employee_info.name"
    ))
  )).toFuture()

  println("=====rightJoinResult=====")
  Thread.sleep(1000)
  printResults(rightJoinResult)

  // FULL JOIN (simplified)
  val fullJoinResult = departmentsCollection.aggregate(Seq(
    lookup("employees", "department_id", "department_id", "employee_info"),
    unwind("$employee_info"),
    project(Document(
      "department_name" -> 1,
      "employees" -> "$employee_info.name"
    ))
  )).toFuture()
  println("=====fullJoinResult=====")
  Thread.sleep(1000)
  printResults(fullJoinResult)

  // CROSS JOIN
  val crossJoinResult = for {
    employees <- employeesCollection.find().toFuture()
    departments <- departmentsCollection.find().toFuture()
  } yield {
    for {
      emp <- employees
      dep <- departments
    } yield {
      val employeeName = emp.getString("name")
      val departmentName = dep.getString("department_name")
      Document("employee_name" -> employeeName,
        "department_name" -> departmentName
      )
    }
  }

  println("=====crossJoinResult=====")
  Thread.sleep(1000)
  printResults(crossJoinResult)

  // SELF JOIN
  val selfJoinResult = employeesCollection.aggregate(Seq(
    lookup("employees", "manager_id", "employee_id", "manager_info"),
    unwind("$manager_info"),
    project(Document("employee_name" -> "$name", "manager_name" -> "$manager_info.name"))
  )).toFuture()

  println("=====selfJoinResult=====")
  Thread.sleep(1000)
  printResults(selfJoinResult)

  // Clean up resources
  Thread.sleep(1000)
  MongoUtils.client.close()
}
```

## Details of Joins

- **Inner Join**: Joins `employees` with `departments` on `department_id` and returns employees with their department names.
- **Left Join**: Joins `employees` with `departments` and returns all employees with their department names or "No department" if not found.
- **Right Join**: Joins `departments` with `employees` and returns all departments with their employees or "No employees" if not found.
- **Full Join**: Shows all departments with their employees or indicates if there are no employees for a department.
- **Cross Join**: Produces a Cartesian product of `employees` and `departments`.
- **Self Join**: Joins `employees` with themselves to match employees with their managers.

![Motivational Image](motivationa_image.jpg)

> **"Where there is a will, there is a way !"**  
> *— Proverb*
