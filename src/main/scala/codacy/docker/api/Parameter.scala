package codacy.docker.api

object Parameter {

  //jsonEncoded!
  trait Value extends Any
  //case class Value(value:Any) extends AnyVal

  case class Name(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class Definition(name: Parameter.Name, value: Parameter.Value)

  case class Specification(name: Parameter.Name, default: Parameter.Value)

}
