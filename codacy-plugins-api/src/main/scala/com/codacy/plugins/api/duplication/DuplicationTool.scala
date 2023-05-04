package com.codacy.plugins.api.duplication

import com.codacy.plugins.api.languages.Language
import com.codacy.plugins.api.{Options, Source}

import scala.util.Try

trait DuplicationTool {
  def apply(path: Source.Directory,
            language: Option[Language],
            options: Map[Options.Key, Options.Value]): Try[List[DuplicationClone]]
}

object DuplicationTool {
  case class CodacyConfiguration(language: Option[Language], params: Option[Map[Options.Key, Options.Value]])
}
