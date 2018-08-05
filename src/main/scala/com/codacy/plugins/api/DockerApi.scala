package com.codacy.plugins.api

import scala.concurrent.duration.FiniteDuration

trait Result

trait AnalysisProblem {
  val message: ErrorMessage
  val file: Option[Source.File]
  val reason: AnalysisProblem.Reason
}

case class AnalysisWarning(message: ErrorMessage, file: Option[Source.File], reason: AnalysisProblem.Reason)
    extends AnalysisProblem
case class AnalysisError(message: ErrorMessage, file: Option[Source.File], reason: AnalysisProblem.Reason)
    extends AnalysisProblem

object AnalysisProblem {
  sealed trait Reason
  case class MissingConfiguration(supportedFilename: Set[String]) extends Reason
  case class InvalidConfiguration(unsupportedPatterns: Set[String], unsupportedValues: Set[ParameterProblem])
      extends Reason
  case class MissingOptions(missingKeys: Set[String]) extends Reason
  case class InvalidOptions(options: Set[OptionProblem]) extends Reason
  case class TimedOut(timeout: FiniteDuration) extends Reason
  case class MissingArtifacts(supported: Set[String]) extends Reason
  case class InvalidArtifacts(paths: Set[String]) extends Reason
  case class OtherReason(message: String, output: Option[String], stacktrace: Option[String]) extends Reason

  //Should we allow this?
  //We are keeping the FileError in the ToolResult file
  //case class FileError() extends Reason

  case class ParameterProblem(patternId: String, parameterName: String, badValue: String, supportedValues: Set[String])
  case class OptionProblem(optionName: String, badValue: String, supportedValues: Set[String])
}

sealed trait Source extends Any {
  def path: String

  override def toString: String = path
}

object Source {

  case class Directory(path: String) extends AnyVal with Source

  case class File(path: String) extends AnyVal with Source

  case class Line(value: Int) extends AnyVal {
    override def toString: String = value.toString
  }

}

case class ErrorMessage(value: String) extends AnyVal {
  override def toString: String = value
}

object Options {

  case class Key(value: String) extends AnyVal

  // JsonEncoded!
  trait Value extends Any

}
