package DBUtils

import org.mongodb.scala.bson.BsonDocument
import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}

trait DBUtils {
  val uri: String
  val client : MongoClient
  val database: MongoDatabase
}
