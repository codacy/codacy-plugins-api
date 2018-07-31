package com.codacy.plugins.api.results

import com.codacy.plugins.api._

sealed trait ToolResult extends Result

object ToolResult {

  case class Lines(begin: Source.Line, end: Option[Source.Line])

  case class Position(line: Source.Line, column: Option[Int])

  case class Positions(begin: Position, end: Option[Position])

  case class Location(path: Source.File, lines: Option[Lines], positions: Option[Positions])

  case class Message(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class Issue(file: Source.File, message: Message, patternId: Pattern.Id, line: Source.Line) extends ToolResult

  case class ExtendedIssue(check_name: Pattern.Id,
                           description: Message,
                           categories: List[String],
                           location: Location,
                           severity: Option[String])
      extends ToolResult

  case class FileError(file: Source.File, message: Option[ErrorMessage]) extends ToolResult

  case class ToolProblem(message: ErrorMessage, file: Option[Source.File], reason: AnalysisProblem.Reason)
      extends AnalysisProblem
      with ToolResult

  type Level = Level.Value

  object Level extends Enumeration {
    val Err: Value = Value("Error")
    val Warn: Value = Value("Warning")
    val Info: Value = Value("Info")
  }

}
