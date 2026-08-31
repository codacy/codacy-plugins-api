package com.codacy.plugins.api.metrics

case class FileMetrics(filename: String, complexity: Option[Int] = None, loc: Option[Int] = None)
