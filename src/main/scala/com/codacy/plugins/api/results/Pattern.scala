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
            case Subcategory.BadDeserialization | Subcategory.BrokenAccess | Subcategory.BrokenAuth |
                Subcategory.Injection | Subcategory.Misconfiguration | Subcategory.NoLogging |
                Subcategory.SensitiveData | Subcategory.VulnerableComponent | Subcategory.XSS | Subcategory.XXE
                if category == Category.Security =>
              spec

            case _ => throw new Exception("invalid sub category")
          }
        case None => spec
      }
    }
  }

  sealed trait Category

  object Category {
    case object Security extends Category
    case object CodeStyle extends Category
    case object ErrorProne extends Category
    case object Performance extends Category
    case object Compatibility extends Category
    case object UnusedCode extends Category
    case object Complexity extends Category
    case object BestPractice extends Category
    case object Comprehensibility extends Category
    case object Duplication extends Category
    case object Documentation extends Category
  }

  sealed trait Subcategory

  object Subcategory {
    case object Injection extends Subcategory
    case object BrokenAuth extends Subcategory
    case object SensitiveData extends Subcategory
    case object XXE extends Subcategory
    case object BrokenAccess extends Subcategory
    case object Misconfiguration extends Subcategory
    case object XSS extends Subcategory
    case object BadDeserialization extends Subcategory
    case object VulnerableComponent extends Subcategory
    case object NoLogging extends Subcategory
  }

}
