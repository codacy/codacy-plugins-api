package com.codacy.plugins.api.metrics

import com.codacy.plugins.api.languages.Language
import com.codacy.plugins.api.{Options, Source}

import scala.util.Try

trait MetricsTool {
  def apply(source: Source.Directory,
            language: Option[Language],
            files: Option[Set[Source.File]],
            options: Map[Options.Key, Options.Value]): Try[List[MetricsResult]]
}

object MetricsTool {
  case class CodacyConfiguration(files: Option[Set[Source.File]],
                                 language: Option[Language],
                                 options: Option[Map[Options.Key, Options.Value]])
}
