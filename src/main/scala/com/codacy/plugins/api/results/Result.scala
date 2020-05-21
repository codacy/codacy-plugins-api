package com.codacy.plugins.api.results

sealed trait Result

object Result {
  case class Issue(filename: String, message: String, patternId: String, line: Int) extends Result

  case class FileError(filename: String, message: String) extends Result
}
