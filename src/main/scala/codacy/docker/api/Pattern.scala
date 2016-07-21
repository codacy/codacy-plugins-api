package codacy.docker.api

object Pattern {

  case class Id(value: String) extends AnyVal{
    override def toString: String = value
  }

  case class Definition(patternId: Pattern.Id, parameters: Option[Set[Parameter.Definition]])

  case class Specification(patternId: Pattern.Id, level:Result.Level, category: Category, parameters: Option[Set[Parameter.Specification]])

  type Category = Category.Value
  object Category extends Enumeration{
    val Security, CodeStyle, ErrorProne, Performance, Compatibility, UnusedCode,
    //Deprecated
    Complexity, BestPractice, Comprehensibility, Duplication, Documentation = Value
  }
}
