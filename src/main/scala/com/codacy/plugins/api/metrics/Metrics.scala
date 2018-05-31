package com.codacy.plugins.api.metrics

case class FileMetrics(filename: String,
                       complexity: Option[Int] = None,
                       loc: Option[Int] = None,
                       cloc: Option[Int] = None,
                       nrMethods: Option[Int] = None,
                       nrClasses: Option[Int] = None,
                       lineComplexities: Set[LineComplexity] = Set.empty)

case class LineComplexity(line: Int, value: Int)
