package com.codacy.plugins.api.duplication

import com.codacy.plugins.api.languages.Language
import com.codacy.plugins.api.{DuplicationConfiguration, Source}

import scala.util.Try

trait DuplicationTool {
  def apply(path: Source.Directory,
            language: Option[Language],
            options: Map[DuplicationConfiguration.Key, DuplicationConfiguration.Value]): Try[List[DuplicationClone]]
}

object DuplicationTool {

  case class Name(value: String) extends AnyVal {
    override def toString: String = value
  }

}
