package com.codacy.plugins.api.docker.v2

import com.codacy.plugins.api.docker.v2
import com.codacy.plugins.api.results.Pattern
import com.codacy.plugins.api.{ErrorMessage, Source}

sealed trait IssueResult extends Result

object IssueResult {

  case class Lines(begin: Source.Line, end: Option[Source.Line])

  case class Position(line: Source.Line, column: Option[Int])

  case class Positions(begin: Position, end: Option[Position])

  case class Location(path: Source.File, lines: Option[Lines], positions: Option[Positions])

  case class Message(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class Issue(file: Source.File, message: Message, patternId: Pattern.Id, line: Source.Line) extends IssueResult

  case class Problem(message: ErrorMessage, file: Option[Source.File], reason: v2.Problem.Reason)
      extends v2.Problem
      with IssueResult

  object Level extends Enumeration {
    val Err: Value = Value("Error")
    val Warn: Value = Value("Warning")
    val Info: Value = Value("Info")
  }

}
