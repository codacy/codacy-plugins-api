package codacy.docker.api

import scala.util.Try

trait Tool {
  def apply(source: Source.Directory, configuration: Option[List[Pattern.Definition]], files: Option[Set[Source.File]],
            options: Map[Configuration.Key, Configuration.Value])
           (implicit specification: Tool.Specification): Try[List[Result]]
}

object Tool {

  case class Name(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class Configuration(name: Tool.Name, patterns: Option[List[Pattern.Definition]])

  // There are other fields like name and description but i don't care about them inside the tool
  case class Specification(name: Tool.Name, patterns: Set[Pattern.Specification])

}
