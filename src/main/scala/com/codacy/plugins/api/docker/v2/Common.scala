package com.codacy.plugins.api.docker.v2

import com.codacy.plugins.api.{ErrorMessage, Source}

import scala.concurrent.duration.FiniteDuration

trait Result

trait Problem {
  val message: ErrorMessage
  val file: Option[Source.File]
  val reason: Problem.Reason
}

object Problem {

  case class Warning(message: ErrorMessage, file: Option[Source.File], reason: Problem.Reason) extends Problem
  case class Error(message: ErrorMessage, file: Option[Source.File], reason: Problem.Reason) extends Problem

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
