//> using scala "3"
//> using dep "com.lihaoyi::upickle:3.1.3"
//> using dep "com.lihaoyi::os-lib:0.9.3"

import upickle.default.*
import os.Path

import scala.util.Try

case class Record(name: String, id: String, version: Double) derives ReadWriter

trait Utility {
  def findInFile(path: Path)(predicate: Record => Boolean): Option[Record]
}

object JsonUtility {
  def instance: Utility =
    new Utility {

      override def findInFile(path: Path)(
          predicate: Record => Boolean
      ): Option[Record] =
        Try {
          val text = os.read(path)
          read[List[Record]](text)
        }.toOption.flatMap(_.find(predicate))

    }
}
