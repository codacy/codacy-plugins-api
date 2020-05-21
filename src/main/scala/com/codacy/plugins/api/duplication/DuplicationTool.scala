package com.codacy.plugins.api.duplication

import com.codacy.plugins.api.languages.Language

object DuplicationTool {
  case class CodacyConfiguration(language: Option[Language], params: Map[String, String])
}
