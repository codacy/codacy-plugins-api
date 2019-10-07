val scala211 = "2.11.12"
val scala212 = "2.12.8"
val scala213 = "2.13.0"

name := "codacy-plugins-api"
organization := "com.codacy"

scalaVersion := scala212
crossScalaVersions := Seq(scala211, scala212, scala213)

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "4.6.0" % Test)

unmanagedSourceDirectories in Compile += {
  val sourceDir = (sourceDirectory in Compile).value
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, n)) if n >= 13 => sourceDir / "scala-2.13+"
    case _                       => sourceDir / "scala-2.13-"
  }
}

// Sonatype repository settings
ThisBuild / credentials += Credentials("Sonatype Nexus Repository Manager",
                                       "oss.sonatype.org",
                                       sys.env.getOrElse("SONATYPE_USER", "username"),
                                       sys.env.getOrElse("SONATYPE_PASSWORD", "password"))
ThisBuild / organization := "com.codacy"
ThisBuild / organizationName := "Codacy"
ThisBuild / organizationHomepage := Some(url("https://www.codacy.com"))

ThisBuild / scmInfo := Some(
  ScmInfo(url("https://github.com/codacy/codacy-plugins-api"), "scm:git@github.com:codacy/codacy-plugins-api"))
ThisBuild / developers := List(
  Developer(id = "johannegger",
            name = "Johann",
            email = "johann [at] codacy.com",
            url = url("https://github.com/johannegger")),
  Developer(id = "rtfpessoa",
            name = "Rodrigo Fernandes",
            email = "rodrigo [at] codacy.com",
            url = url("https://github.com/rtfpessoa")),
  Developer(id = "bmbferreira",
            name = "Bruno Ferreira",
            email = "bruno.ferreira [at] codacy.com",
            url = url("https://github.com/bmbferreira")),
  Developer(id = "xplosunn",
            name = "Hugo Sousa",
            email = "hugo [at] codacy.com",
            url = url("https://github.com/xplosunn")),
  Developer(id = "pedrocodacy",
            name = "Pedro Amaral",
            email = "pamaral [at] codacy.com",
            url = url("https://github.com/pedrocodacy")))

ThisBuild / startYear := Some(2016)
ThisBuild / description := "Library to develop Codacy tool plugins"
ThisBuild / licenses := List(
  "The Apache Software License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))
ThisBuild / homepage := Some(url("http://www.github.com/codacy/codacy-plugins-api/"))

// Remove all additional repository other than Maven Central from POM
ThisBuild / pomIncludeRepository := { _ =>
  false
}
ThisBuild / publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
ThisBuild / publishMavenStyle := true
ThisBuild / publishArtifact in Test := false
