package com.codacy.plugins.api.metrics

import com.codacy.plugins.api.languages.Language
import com.codacy.plugins.api.{MetricsConfiguration, Source}

import scala.util.Try

trait MetricsTool {
  def apply(source: Source.Directory,
            language: Option[Language],
            files: Option[Set[Source.File]],
            options: Map[MetricsConfiguration.Key, MetricsConfiguration.Value]): Try[List[FileMetrics]]
}

object MetricsTool {

  case class Name(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class Version(value: String) extends AnyVal {
    override def toString: String = value
  }

}
