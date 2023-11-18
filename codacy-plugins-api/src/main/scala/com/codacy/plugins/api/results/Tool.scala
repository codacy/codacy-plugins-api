package com.codacy.plugins.api.results

import com.codacy.plugins.api._

import scala.util.Try

trait Tool {
  def apply(source: Source.Directory,
            configuration: Option[List[Pattern.Definition]],
            files: Option[Set[Source.File]],
            options: Map[Options.Key, Options.Value])(implicit specification: Tool.Specification): Try[List[Result]]
}

object Tool {

  case class Name(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class Version(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class Specification(name: Tool.Name, version: Option[Tool.Version], patterns: Set[Pattern.Specification])

  case class Configuration(name: Tool.Name, patterns: Option[List[Pattern.Definition]])

  case class CodacyConfiguration(tools: Set[Tool.Configuration],
                                 files: Option[Set[Source.File]],
                                 options: Option[Map[Options.Key, Options.Value]])

}
