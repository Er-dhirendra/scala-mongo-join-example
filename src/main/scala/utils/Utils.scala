package utils

import org.mongodb.scala.bson.{BsonDocument, Document}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

object Utils {
  def printResults[T](future: Future[Seq[T]])(implicit manifest: Manifest[T]): Unit = {
    future.onComplete {
      case Success(results) =>
        results.foreach {
          case bsonDoc: BsonDocument => println(s"${bsonDoc.toJson()}")
          case doc: Document         => println(s"${doc.toJson()}")
          case other                 => println(s"${other.toString}")
        }
      case Failure(exception) =>
        println(s"An error occurred: ${exception.getMessage}")
    }
  }
}
