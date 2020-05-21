package com.codacy.plugins.api.results

import com.codacy.plugins.api.languages.Language

object Pattern {

  case class Id(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class Title(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class Description(patternId: String,
                         title: String,
                         description: Option[String],
                         timeToFix: Option[Int],
                         parameters: Set[Parameter.Description])

  case class Definition(patternId: String, parameters: Set[Parameter.Definition])

  case class Specification(patternId: String,
                           level: Level,
                           category: Category,
                           subcategory: Option[Subcategory],
                           parameters: Set[Parameter.Specification],
                           languages: Set[Language]) {
    require(subcategory.isEmpty || category == Category.Security, "Security is the only category having subcategories")
  }

  type Category = Category.Value
  object Category extends Enumeration {
    val Security, CodeStyle, ErrorProne, Performance, Compatibility, UnusedCode, Complexity, BestPractice,
    Comprehensibility, Duplication, Documentation = Value
  }

  type Subcategory = Subcategory.Value
  object Subcategory extends Enumeration {
    val XSS, InputValidation, FileAccess, HTTP, Cookies, UnexpectedBehaviour, MassAssignment, InsecureStorage,
    InsecureModulesLibraries, Visibility, CSRF, Android, MaliciousCode, Cryptography, CommandInjection, FirefoxOS, Auth,
    DoS, SQLInjection, Routes, Regex, SSL, Other = Value
  }
}
