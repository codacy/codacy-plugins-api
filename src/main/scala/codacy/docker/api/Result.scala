package codacy.docker.api

sealed trait Result

object Result {

  case class Message(value: String) extends AnyVal{
    override def toString: String = value
  }

  case class Line(value: Int) extends AnyVal{
    override def toString: String = value.toString
  }

  case class Issue(file: Source.File, message: Result.Message, patternId: Pattern.Id, line: Result.Line) extends Result

  case class FileError(file: Source.File, message: Option[ErrorMessage]) extends Result

  type Level = Level.Value

  object Level extends Enumeration{
    val Err  = Value("Error")
    val Warn = Value("Warning")
    val Info = Value("Info")
  }
}

