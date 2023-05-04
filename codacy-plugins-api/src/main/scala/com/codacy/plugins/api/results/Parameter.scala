package com.codacy.plugins.api.results

object Parameter {

  // JsonEncoded!
  // case class Value(value:Any) extends AnyVal
  trait Value extends Any

  case class Name(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class DescriptionText(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class Description(name: Parameter.Name, description: Parameter.DescriptionText)

  case class Definition(name: Parameter.Name, value: Parameter.Value)

  case class Specification(name: Parameter.Name, default: Parameter.Value)

}
