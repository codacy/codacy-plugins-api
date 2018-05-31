package com.codacy.plugins.api

sealed trait Source extends Any {
  def path: String

  override def toString: String = path
}

object Source {

  case class Directory(path: String) extends AnyVal with Source

  case class File(path: String) extends AnyVal with Source

  case class Line(value: Int) extends AnyVal {
    override def toString: String = value.toString
  }

}

case class ErrorMessage(value: String) extends AnyVal {
  override def toString: String = value
}

object Options {

  case class Key(value: String) extends AnyVal

  // JsonEncoded!
  trait Value extends Any

}
