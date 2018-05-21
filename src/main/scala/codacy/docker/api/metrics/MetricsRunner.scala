package codacy.docker.api.metrics

import codacy.docker.api.{Configuration, Source}
import com.codacy.api.dtos.Language

import scala.util.Try

trait MetricsRunner {
  def apply(source: Source.Directory,
            language: Language,
            files: Option[Set[Source.File]],
            options: Map[Configuration.Key, Configuration.Value]): Try[List[FileMetrics]]
}

object MetricsRunner {

  case class Name(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class Version(value: String) extends AnyVal {
    override def toString: String = value
  }

}
