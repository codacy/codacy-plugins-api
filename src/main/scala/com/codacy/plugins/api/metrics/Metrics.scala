package com.codacy.plugins.api.metrics

import com.codacy.plugins.api._

sealed trait MetricsResult extends Result

case class FileMetrics(filename: String,
                       complexity: Option[Int] = None,
                       loc: Option[Int] = None,
                       cloc: Option[Int] = None,
                       nrMethods: Option[Int] = None,
                       nrClasses: Option[Int] = None,
                       lineComplexities: Set[LineComplexity] = Set.empty)
    extends MetricsResult

case class LineComplexity(line: Int, value: Int)

case class MetricsProblem(message: ErrorMessage, file: Option[Source.File], reason: AnalysisProblem.Reason)
    extends AnalysisProblem
    with MetricsResult
