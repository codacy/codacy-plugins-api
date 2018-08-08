package com.codacy.plugins.api.docker.v1

import com.codacy.plugins.api.results.Pattern
import com.codacy.plugins.api.{ErrorMessage, Source}

sealed trait Result

object Result {

  case class Lines(begin: Source.Line, end: Option[Source.Line])

  case class Position(line: Source.Line, column: Option[Int])

  case class Positions(begin: Position, end: Option[Position])

  case class Location(path: Source.File, lines: Option[Lines], positions: Option[Positions])

  case class Message(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class Issue(file: Source.File, message: Result.Message, patternId: Pattern.Id, line: Source.Line) extends Result

  case class ExtendedIssue(check_name: Pattern.Id,
                           description: Result.Message,
                           categories: List[String],
                           location: Location,
                           severity: Option[String])
      extends Result

  case class FileError(file: Source.File, message: Option[ErrorMessage]) extends Result

}
