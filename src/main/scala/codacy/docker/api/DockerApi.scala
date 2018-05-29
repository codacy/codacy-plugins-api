package codacy.docker.api

import com.codacy.api.dtos.Language

sealed trait Source extends Any {
  def path: String

  override def toString: String = path
}

object Source {

  case class Directory(path: String) extends AnyVal with Source

  case class File(path: String) extends AnyVal with Source

  case class Line(value: Int) extends AnyVal {
    override def toString: String = value.toString
  }

}

case class ErrorMessage(value: String) extends AnyVal {
  override def toString: String = value
}

case class Configuration(tools: Set[Tool.Configuration],
                         files: Option[Set[Source.File]],
                         options: Option[Map[Configuration.Key, Configuration.Value]])

object Configuration {

  case class Key(value: String) extends AnyVal

  // JsonEncoded!
  // case class Value(value:Any) extends AnyVal
  trait Value extends Any

}

case class AlternativeConfiguration(enabled: Boolean, include_paths: Option[Set[Source.File]])

case class MetricsConfiguration(files: Option[Set[Source.File]],
                                language: Option[Language],
                                options: Option[Map[MetricsConfiguration.Key, MetricsConfiguration.Value]])

object MetricsConfiguration {

  case class Key(value: String) extends AnyVal

  trait Value extends Any
}

case class DuplicationConfiguration(language: Option[Language],
                                    params: Option[Map[DuplicationConfiguration.Key, DuplicationConfiguration.Value]])

object DuplicationConfiguration {

  case class Key(value: String) extends AnyVal

  trait Value extends Any
}

case class CodacyConfiguration(duplication: DuplicationConfiguration)