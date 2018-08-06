package com.codacy.plugins.api.docker.v2

import com.codacy.plugins.api.docker.v2
import com.codacy.plugins.api.{ErrorMessage, Source}

sealed trait MetricsResult extends Result

object MetricsResult {

  case class FileMetrics(filename: String,
                         complexity: Option[Int] = None,
                         loc: Option[Int] = None,
                         cloc: Option[Int] = None,
                         nrMethods: Option[Int] = None,
                         nrClasses: Option[Int] = None,
                         lineComplexities: Set[LineComplexity] = Set.empty)
      extends MetricsResult

  case class LineComplexity(line: Int, value: Int)

  case class Problem(message: ErrorMessage, file: Option[Source.File], reason: v2.Problem.Reason)
      extends v2.Problem
      with MetricsResult

}
