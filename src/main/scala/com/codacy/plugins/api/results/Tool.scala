package com.codacy.plugins.api.results

object Tool {

  case class Specification(name: String, version: Option[String], patterns: Set[Pattern.Specification])

  case class Configuration(name: String, patterns: Seq[Pattern.Definition])

  case class CodacyConfiguration(tools: Set[Tool.Configuration],
                                 files: Set[String],
                                 options: Map[String, String])

}
