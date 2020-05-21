package com.codacy.plugins.api.results

sealed trait Level
object Level {
  case object Error extends Level
  case object Info extends Level
  case object Warning extends Level
}
