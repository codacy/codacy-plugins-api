package codacy.docker.api.duplication

import codacy.docker.api.{DuplicationConfiguration, Source}
import com.codacy.api.dtos.Language

import scala.util.Try

trait DuplicationTool {
  def apply(path: Source.Directory,
            language: Option[Language],
            options: Map[DuplicationConfiguration.Key, DuplicationConfiguration.Value]): Try[List[DuplicationClone]]
}

object DuplicationTool {

  case class Name(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class Version(value: String) extends AnyVal {
    override def toString: String = value
  }

}
