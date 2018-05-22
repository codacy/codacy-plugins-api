package codacy.docker.api.metrics

import codacy.docker.api.{MetricsConfiguration, Source}
import com.codacy.api.dtos.Language

import scala.util.Try

trait MetricsTool {
  def apply(source: Source.Directory,
            language: Language,
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
