package com.codacy.plugins.api.results

import com.codacy.plugins.api.docker.v1.Result
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
                           category: Category,
                           parameters: Option[Set[Parameter.Specification]],
                           languages: Option[Set[Language]] = None)

  type Category = Category.Value

  object Category extends Enumeration {
    val Security, CodeStyle, ErrorProne, Performance, Compatibility, UnusedCode, //Deprecated
    Complexity, BestPractice, Comprehensibility, Duplication, Documentation = Value
  }

}
