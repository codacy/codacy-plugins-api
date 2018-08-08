package com.codacy.plugins.api.docker.v2

import com.codacy.plugins.api.Source

import scala.concurrent.duration.FiniteDuration

sealed trait DuplicationResult

object DuplicationResult {

  sealed trait Duplication extends DuplicationResult

  case class CloneFile(filePath: String, startLine: Int, endLine: Int)

  case class Clone(cloneLines: String, nrTokens: Int, nrLines: Int, files: Seq[CloneFile]) extends Duplication

  sealed trait Log extends DuplicationResult

  case class Context(reason: Reason) extends Log
  case class Advisory(reason: Reason, file: Option[Source.File]) extends Log

  sealed trait Problem extends DuplicationResult

  case class Failure(reason: Reason, files: Set[Source.File]) extends Problem

  sealed trait Reason

  object Reason {
    case class MissingOptions(missingKeys: Set[String]) extends Reason

    case class OptionProblem(optionName: String, badValue: String, supportedValues: Set[String])
    case class InvalidOptions(options: Set[OptionProblem]) extends Reason

    case class TimedOut(timeout: FiniteDuration) extends Reason

    case class OtherReason(message: String, output: Option[String], stacktrace: Option[String]) extends Reason
  }

}
