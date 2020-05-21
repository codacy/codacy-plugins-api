package com.codacy.plugins.api.metrics

import com.codacy.plugins.api.languages.Language

object MetricsTool {
  case class CodacyConfiguration(files: Set[String],
                                 language: Option[Language],
                                 options: Map[String, String])
}
