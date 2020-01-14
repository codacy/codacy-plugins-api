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
                         parameters: Option[Set[Parameter.Description]])

  case class Definition(patternId: Pattern.Id, parameters: Option[Set[Parameter.Definition]])

  case class Specification(patternId: Pattern.Id,
                           level: Result.Level,
                           category: Specification.Category,
                           subcategory: Option[Specification.Subcategory],
                           parameters: Option[Set[Parameter.Specification]],
                           languages: Option[Set[Language]] = None)
  object Specification {
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
}
