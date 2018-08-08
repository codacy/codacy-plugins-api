package com.codacy.plugins.api.docker.v2

import com.codacy.plugins.api.Source

import scala.concurrent.duration.FiniteDuration

sealed trait MetricsResult

object MetricsResult {

  sealed trait Metric extends MetricsResult

  case class LineComplexity(line: Int, value: Int)

  case class FileMetrics(filename: String,
                         complexity: Option[Int] = None,
                         loc: Option[Int] = None,
                         cloc: Option[Int] = None,
                         nrMethods: Option[Int] = None,
                         nrClasses: Option[Int] = None,
                         lineComplexities: Set[LineComplexity] = Set.empty)
      extends Metric

  sealed trait Log extends MetricsResult

  case class Context(reason: Reason) extends Log
  case class Advisory(reason: Reason, file: Option[Source.File]) extends Log

  sealed trait Problem extends MetricsResult

  case class Failure(reason: Reason, files: Set[Source.File]) extends Problem

  sealed trait Reason

  object Reason {
    case class MissingOptions(missingKeys: Set[String]) extends Reason

    case class OptionProblem(optionName: String, badValue: String, supportedValues: Set[String])
    case class InvalidOptions(options: Set[OptionProblem]) extends Reason

    case class TimedOut(timeout: FiniteDuration) extends Reason

    case class Generic(message: String, output: Option[String], stacktrace: Option[String]) extends Reason
  }

}
