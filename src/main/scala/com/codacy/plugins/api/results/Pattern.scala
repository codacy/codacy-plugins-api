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

  trait Specification {
    val patternId: Pattern.Id
    val level: Result.Level
    val category: Category
    val subcategory: Option[Subcategory]
    val parameters: Option[Set[Parameter.Specification]]
    val languages: Option[Set[Language]]
  }

  object Specification {
    private case class SpecificationImpl(patternId: Pattern.Id,
                                         level: Result.Level,
                                         category: Category,
                                         subcategory: Option[Subcategory],
                                         parameters: Option[Set[Parameter.Specification]],
                                         languages: Option[Set[Language]] = None)
        extends Specification

    def unapply(s: Pattern.Specification): Option[(Pattern.Id,
                                                   Result.Level,
                                                   Category,
                                                   Option[Subcategory],
                                                   Option[Set[Parameter.Specification]],
                                                   Option[Set[Language]])] = s match {
      case si: SpecificationImpl =>
        SpecificationImpl.unapply(si)
    }

    def apply(patternId: Pattern.Id,
              level: Result.Level,
              category: Category,
              subcategory: Option[Subcategory],
              parameters: Option[Set[Parameter.Specification]],
              languages: Option[Set[Language]] = None): Specification = {
      val spec = SpecificationImpl(patternId, level, category, subcategory, parameters, languages)

      spec.subcategory match {
        case Some(sc) =>
          sc match {
            case Subcategory.XSS | Subcategory.Input_validation | Subcategory.File_Access | Subcategory.HTTP |
                Subcategory.Cookies | Subcategory.Unexpected_behaviour | Subcategory.Mass_assignment |
                Subcategory.Insecure_Storage | Subcategory.Insecure_modules_libraries | Subcategory.Visibility |
                Subcategory.CSRF | Subcategory.Android | Subcategory.Malicious_code | Subcategory.Cryptography |
                Subcategory.Command_Injection | Subcategory.Firefox_OS | Subcategory.Auth | Subcategory.DoS |
                Subcategory.SQL_Injection | Subcategory.Routes | Subcategory.Regex | Subcategory.SSL | Subcategory.Other
                if category == Category.Security =>
              spec

            case _ => throw new Exception("invalid sub category")
          }
        case None => spec
      }
    }
  }

  type Category = Category.Value

  object Category extends Enumeration {

    val Security, CodeStyle, ErrorProne, Performance, Compatibility, UnusedCode, Complexity, BestPractice,
    Comprehensibility, Duplication, Documentation = Value
  }

  type Subcategory = Subcategory.Value

  object Subcategory extends Enumeration {
    val XSS = Value("XSS")
    val Input_validation = Value("Input validation")
    val File_Access = Value("File Access")
    val HTTP = Value("HTTP")
    val Cookies = Value("Cookies")
    val Unexpected_behaviour = Value("Unexpected behaviour")
    val Mass_assignment = Value("Mass assignment")
    val Insecure_Storage = Value("Insecure Storage")
    val Insecure_modules_libraries = Value("Insecure modules/libraries")
    val Visibility = Value("Visibility")
    val CSRF = Value("CSRF")
    val Android = Value("Android")
    val Malicious_code = Value("Malicious code")
    val Cryptography = Value("Cryptography")
    val Command_Injection = Value("Command Injection")
    val Firefox_OS = Value("Firefox OS")
    val Auth = Value("Auth")
    val DoS = Value("DoS")
    val SQL_Injection = Value("SQL Injection")
    val Routes = Value("Routes")
    val Regex = Value("Regex")
    val SSL = Value("SSL")
    val Other = Value("Other")
  }

}
