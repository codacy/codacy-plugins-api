val scala211 = "2.11.12"
val scala212 = "2.12.10"
val scala213 = "2.13.1"

name := "codacy-plugins-api"
organization := "com.codacy"

scalaVersion := scala212

crossScalaVersions := Seq(scala211, scala212, scala213)

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "4.8.3" % Test)

unmanagedSourceDirectories in Compile += {
  val sourceDir = (sourceDirectory in Compile).value
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, n)) if n >= 13 => sourceDir / "scala-2.13+"
    case _                       => sourceDir / "scala-2.13-"
  }
}

pgpPassphrase := Option(System.getenv("SONATYPE_GPG_PASSPHRASE")).map(_.toCharArray)

description := "A dependency free api for Codacy Tools"

scmInfo := Some(
  ScmInfo(url("https://github.com/codacy/codacy-plugins-api"), "scm:git:git@github.com:codacy/codacy-plugins-api.git"))

publicMvnPublish
