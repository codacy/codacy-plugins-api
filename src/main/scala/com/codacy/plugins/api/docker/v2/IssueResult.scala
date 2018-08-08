package com.codacy.plugins.api.docker.v2

import com.codacy.plugins.api.Source
import com.codacy.plugins.api.results.Pattern
import com.codacy.plugins.api.results.Pattern.{Category, Level}

import scala.concurrent.duration.FiniteDuration

sealed trait IssueResult

object IssueResult {

  sealed trait Issue extends IssueResult

  case class Lines(begin: Source.Line, end: Option[Source.Line])

  case class Position(line: Source.Line, column: Option[Int])

  case class Positions(begin: Position, end: Option[Position])

  case class Location(path: Source.File, lines: Option[Lines], positions: Option[Positions])

  case class Message(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class Occurrence(file: Source.File,
                        message: Message,
                        patternId: Pattern.Id,
                        line: Source.Line,
                        level: Option[Level.Value] = Option.empty[Level.Value],
                        categories: Set[Category.Value] = Set.empty[Category.Value])
      extends Issue

  sealed trait Log extends IssueResult

  case class Context(reason: Reason) extends Log
  case class Advisory(reason: Reason, file: Option[Source.File]) extends Log

  sealed trait Problem extends IssueResult

  case class Failure(reason: Reason, files: Set[Source.File]) extends Problem

  sealed trait Reason

  object Reason {
    case class MissingConfiguration(supportedFilename: Set[String]) extends Reason

    case class ParameterProblem(patternId: String,
                                parameterName: String,
                                badValue: String,
                                supportedValues: Set[String])
    case class InvalidConfiguration(unsupportedPatterns: Set[String], unsupportedValues: Set[ParameterProblem])
        extends Reason

    case class MissingOptions(missingKeys: Set[String]) extends Reason

    case class OptionProblem(optionName: String, badValue: String, supportedValues: Set[String])
    case class InvalidOptions(options: Set[OptionProblem]) extends Reason

    case class TimedOut(timeout: FiniteDuration) extends Reason

    case class MissingArtifacts(supported: Set[String]) extends Reason

    case class InvalidArtifacts(paths: Set[String]) extends Reason

    case class OtherReason(message: String, output: Option[String], stacktrace: Option[String]) extends Reason
  }

}
