package codacy.docker.api

sealed trait Source extends Any{
  def path:String
  override def toString: String = path
}

object Source{
  case class Directory(path: String) extends AnyVal with Source
  case class File(path: String) extends AnyVal with Source
}

case class ErrorMessage(value: String) extends AnyVal{
  override def toString: String = value
}

case class Configuration(tools: Set[Tool.Configuration], files: Option[Set[Source.File]])

