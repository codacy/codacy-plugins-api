package com.codacy.plugins.api.results

object Parameter {

  case class Description(name: String, description: String)

  case class Definition(name: String, value: String)

  case class Specification(name: String, default: String)

}
