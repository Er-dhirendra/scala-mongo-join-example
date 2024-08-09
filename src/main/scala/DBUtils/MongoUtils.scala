package DBUtils

import org.mongodb.scala.bson.BsonDocument
import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}

object MongoUtils extends DBUtils {

  val uri = "mongodb://localhost:27017"
  // Create a MongoClient
  val client: MongoClient = MongoClient(uri)

  val database: MongoDatabase = client.getDatabase("testdb")
  // Access the database and collection
  val employeesCollection: MongoCollection[BsonDocument] = database.getCollection("employees")
  val departmentsCollection: MongoCollection[BsonDocument] = database.getCollection("departments")
  val inner_join_view: MongoCollection[BsonDocument] = database.getCollection("inner_join_view")
  val left_join_view: MongoCollection[BsonDocument] = database.getCollection("left_join_view")
  val right_join_view: MongoCollection[BsonDocument] = database.getCollection("right_join_view")
  val full_join_employees_view: MongoCollection[BsonDocument] = database.getCollection("full_join_employees_view")
  val full_join_departments_view: MongoCollection[BsonDocument] = database.getCollection("full_join_departments_view")
  val cross_join_view: MongoCollection[BsonDocument] = database.getCollection("cross_join_view")
  val self_join_view: MongoCollection[BsonDocument] = database.getCollection("self_join_view")

}
