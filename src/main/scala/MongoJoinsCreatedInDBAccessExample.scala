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
