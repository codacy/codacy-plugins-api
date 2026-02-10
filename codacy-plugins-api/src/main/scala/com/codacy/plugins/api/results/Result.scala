package com.codacy.plugins.api.results

import com.codacy.plugins.api.{ErrorMessage, Source}

sealed trait Result

object Result {

  case class Lines(begin: Source.Line, end: Option[Source.Line])

  case class Position(line: Source.Line, column: Option[Int])

  case class Positions(begin: Position, end: Option[Position])

  case class Location(path: Source.File, lines: Option[Lines], positions: Option[Positions])

  case class Message(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class Suggestion(value: String) extends AnyVal {
    override def toString: String = value
  }

  case class Issue(filename: Source.File,
                   message: Result.Message,
                   patternId: Pattern.Id,
                   line: Source.Line,
                   suggestion: Option[Suggestion],
                   sourceId: Option[String])
      extends Result

  case class FileError(filename: Source.File, message: Option[ErrorMessage]) extends Result

  /** SBOM - Software Bill of Materials
    *
    * A SBOM declares the inventory of components used to build a software artifact, including any open source and
    * proprietary software components.
    *
    * @param bomFormat The format of the SBOM. Currently only [[https://cyclonedx.org/ CycloneDX]] specification in JSON
    *                  format is supported.
    * @param specVersion The version of the SBOM format used to build this SBOM.
    * @param sbom The actual SBOM content. To be parsed by downstream consumers according to [[bomFormat]] and
    *             [[specVersion]].
    */
  case class SBOM(bomFormat: SBOM.BOMFormat.Value, specVersion: String, sbom: String) extends Result

  object SBOM {

    /** An enum representing the supported BOM formats. */
    type BOMFormat = BOMFormat.Value
    object BOMFormat extends Enumeration {

      /** [[https://cyclonedx.org/ CycloneDX]] specification in JSON format. */
      val CycloneDXJSON: Value = Value("CycloneDXJSON")
    }
  }

  type Level = Level.Value

  object Level extends Enumeration {
    val Err: Value = Value("Error")
    val High: Value = Value("High")
    val Warn: Value = Value("Warning")
    val Info: Value = Value("Info")
  }

}
