package com.codacy.plugins.api.results

import com.codacy.plugins.api._

import scala.util.Try

trait IssuesTool[T] {
  def apply(source: Source.Directory,
            configuration: Option[List[Pattern.Definition]],
            files: Option[Set[Source.File]],
            options: Map[Options.Key, Options.Value])(implicit specification: IssuesTool.Specification): Try[List[T]]
}

object IssuesTool {

  case class Name(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class Version(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class Specification(name: IssuesTool.Name,
                           version: Option[IssuesTool.Version],
                           patterns: Set[Pattern.Specification])

  case class Configuration(name: IssuesTool.Name, patterns: Option[List[Pattern.Definition]])

  case class CodacyConfiguration(tools: Set[IssuesTool.Configuration],
                                 files: Option[Set[Source.File]],
                                 options: Option[Map[Options.Key, Options.Value]])

  case class CodacyAlternativeConfiguration(enabled: Boolean, include_paths: Option[Set[Source.File]])

}
