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
                         parameters: Set[Parameter.Description] = Set.empty)

  case class Definition(patternId: Pattern.Id, parameters: Set[Parameter.Definition] = Set.empty)

  case class Specification(patternId: Pattern.Id,
                           level: Result.Level,
                           category: Category,
                           subcategory: Option[Subcategory],
                           scanType: Option[ScanType] = Option.empty,
                           parameters: Set[Parameter.Specification] = Set.empty,
                           languages: Set[Language] = Set.empty,
                           enabled: Boolean = false) {
    require(subcategory.isEmpty || category == Category.Security, "Security is the only category having subcategories")
  }

  type Category = Category.Value
  object Category extends Enumeration {
    val Security, CodeStyle, ErrorProne, Performance, Compatibility, UnusedCode, Complexity, BestPractice,
      Comprehensibility, Duplication, Documentation = Value
  }

  type Subcategory = Subcategory.Value
  object Subcategory extends Enumeration {
    val XSS, InputValidation, FileAccess, HTTP, Cookies, UnexpectedBehaviour, MassAssignment, InsecureStorage,
      InsecureModulesLibraries, Visibility, CSRF, Android, MaliciousCode, Cryptography, CommandInjection, FirefoxOS,
      Auth, DoS, SQLInjection, Routes, Regex, SSL, Other = Value
  }

  /** ScanType represents the type of analysis performed to discover issues that match the associated patterns.
    *
    * Scan types can be divided into two "categories":
    *   - Static scan types: these are the only scan types that can actually be associated to a pattern of our static
    *     analysis pipeline. These scans are performed on "static" source code.
    *     - SAST
    *     - SCA
    *     - ContainerSCA
    *     - Secrets
    *     - IaC
    *     - CICD
    *     - License
    *   - Dynamic scan types: are performed on live applications and can never be associated with patterns of our static
    *     analysis pipeline. They are defined here for completion.
    *     - PenTesting
    *     - DAST
    *     - CSPM
    */
  type ScanType = ScanType.Value
  object ScanType extends Enumeration {

    /** Static application security testing, i.e. source code scanning. */
    val SAST = Value

    /** Software composition analysis or supply chain security. Scan open source libraries that projects depend on for vulnerabilities or CVEs.*/
    val SCA = Value

    /** Like SCA but scanning container dependencies. */
    val ContainerSCA = Value

    /** Scan files for exposed API keys, passwords, certificates, encryption keys, etc. */
    val Secrets = Value

    /** Scan infrastructure-as-code files for misconfigurations and vulnerabilities. */
    val IaC = Value

    /** Scan CI/CD files for misconfigurations and vulnerabilities. */
    val CICD = Value

    /** Scan license files for compliance with organization policies. */
    val License = Value

    /** Manually scan an application or system for vulnerabilities. */
    val PenTesting = Value

    /** Similar to pen-testing, but automated and not as customizable. */
    val DAST = Value

    /** Cloud security posture management. Scan live cloud environments for infrastructure and configuration risks. */
    val CSPM = Value
  }
}
