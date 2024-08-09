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
    unwind("$department_info"), // Fixed here
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
    unwind("$manager_info"), // Fixed here
    project(Document("employee_name" -> "$name", "manager_name" -> "$manager_info.name"))
  )).toFuture()

  println("=====selfJoinResult=====")
  Thread.sleep(1000)
  printResults(selfJoinResult)

  // Clean up resources
  Thread.sleep(1000)
  MongoUtils.client.close()
}