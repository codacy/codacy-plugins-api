package com.codacy.plugins.api.results

import com.codacy.plugins.api.{ErrorMessage, Source}

import java.time.Instant

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
                   suggestion: Option[Suggestion] = None)
      extends Result

  case class FileError(filename: Source.File, message: Option[ErrorMessage]) extends Result

  /** SBOM - Software Bill of Materials
    *
    * A SBOM declares the inventory of components used to build a software artifact, including any open source and
    * proprietary software components.
    *
    * This class models CycloneDX 1.6 BOM format.
    *
    * @param bomFormat The format of the SBOM.
    * @param specVersion The version of the SBOM format used to build this SBOM.
    * @param metadata SBOM metadata.
    * @param components A list of software components.
    * @param dependencies Document dependency relationships between components.
    * @see https://github.com/CycloneDX/cyclonedx-core-java/blob/master/src/main/resources/bom-1.6.schema.json
    */
  case class SBOM(bomFormat: SBOM.BOMFormat.Value,
                  specVersion: String,
                  metadata: SBOM.Metadata,
                  components: List[SBOM.Component],
                  dependencies: List[SBOM.Dependency])
      extends Result

  object SBOM {
    type BOMFormat = BOMFormat.Value
    object BOMFormat extends Enumeration {
      val CycloneDX: Value = Value("CycloneDX")
    }

    /** SBOM metadata.
      *
      * @param timestamp When the SBOM file was generated.
      * @param tools The tools used in the SBOM creation.
      * @param component The artifact that the SBOM describes.
      */
    case class Metadata(timestamp: Instant, tools: Tools, component: Component)

    case class Tools(components: List[Tool])

    /** A tool used in SBOM generation. E.g. Trivy.
      *
      * @param `type` The tool type.
      * @param name The tool name.
      * @param group The tool group. E.g. com.codacy.
      * @param version The tool version.
      */
    case class Tool(`type`: Component.Type, name: String, group: String, version: String)

    /** A software or hardware component used to build a software artifact.
      *
      * @param `bom-ref` An identifier used to reference the component elsewhere in the SBOM. Unique within the SBOM.
      * @param `type` The type of component.
      * @param name The component name.
      * @param group The component group.
      * @param version The component version.
      * @param purl The package URL. See https://github.com/package-url/purl-spec
      * @param properties A list of component properties as name-value pairs.
      * @param licenses Component licenses.
      */
    case class Component(`bom-ref`: String,
                         `type`: Component.Type,
                         name: String,
                         group: Option[String],
                         version: Option[String],
                         purl: Option[String],
                         properties: List[Property],
                         licenses: List[LicenseWrapper])

    object Component {
      type Type = Type.Value
      object Type extends Enumeration {
        val Application: Value = Value("application")
        val Framework: Value = Value("framework")
        val Library: Value = Value("library")
        val Container: Value = Value("container")
        val Platform: Value = Value("platform")
        val OperatingSystem: Value = Value("operating-system")
        val Device: Value = Value("device")
        val DeviceDrive: Value = Value("device-driver")
        val Firmware: Value = Value("firmware")
        val File: Value = Value("file")
        val MachineLearningModel: Value = Value("machine-learning-model")
        val Data: Value = Value("data")
        val CryptographicAsset: Value = Value("cryptographic-asset")
      }
    }

    /** A name-value pair representing a piece of information not officially supported by the SBOM schema. */
    case class Property(name: String, value: String)

    case class LicenseWrapper(license: License)

    /** A software license.
      *
      * `id` and `name` cannot both be undefined.
      */
    case class License(id: Option[String], name: Option[String])

    /** Documents a dependency relationship between components.
      *
      * @param ref The reference to a component. Same as [[Component.`bom-ref`]].
      * @param dependsOn References to other components this component depends on.
      */
    case class Dependency(ref: String, dependsOn: List[String])
  }

  type Level = Level.Value

  object Level extends Enumeration {
    val Err: Value = Value("Error")
    val Warn: Value = Value("Warning")
    val Info: Value = Value("Info")
  }

}
