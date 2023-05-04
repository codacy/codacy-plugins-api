package com.codacy.plugins.api.results

import com.codacy.plugins.api.languages.Language

object Pattern {

  case class Id(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class Title(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class DescriptionText(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class TimeToFix(value: Int) extends AnyVal {
    override def toString: String = value.toString
  }

  case class Description(patternId: Pattern.Id,
                         title: Pattern.Title,
                         description: Option[Pattern.DescriptionText],
                         timeToFix: Option[Pattern.TimeToFix],
                         parameters: Set[Parameter.Description] = Set.empty)

  case class Definition(patternId: Pattern.Id, parameters: Set[Parameter.Definition] = Set.empty)

  case class Specification(patternId: Pattern.Id,
                           level: Result.Level,
                           category: Category,
                           subcategory: Option[Subcategory],
                           parameters: Set[Parameter.Specification] = Set.empty,
                           languages: Set[Language] = Set.empty,
                           enabled: Boolean = false) {
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
      InsecureModulesLibraries, Visibility, CSRF, Android, MaliciousCode, Cryptography, CommandInjection, FirefoxOS,
      Auth, DoS, SQLInjection, Routes, Regex, SSL, Other = Value
  }
}
