val scala211 = "2.11.12"
val scala212 = "2.12.10"
val scala213 = "2.13.10"
val scala3 = "3.2.2"

name := "codacy-plugins-api"
organization := "com.codacy"

scalaVersion := scala212

crossScalaVersions := Seq(scala211, scala212, scala213, scala3)

libraryDependencies ++= Seq("wordspec", "shouldmatchers").map(m => "org.scalatest" %% s"scalatest-$m" % "3.2.14" % Test)

Compile / unmanagedSourceDirectories += {
  val sourceDir = (sourceDirectory in Compile).value
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((major, minor)) if major > 2 || minor >= 13 =>
      sourceDir / "scala-2.13+"
    case _ =>
      sourceDir / "scala-2.13-"
  }
}

pgpPassphrase := Option(System.getenv("SONATYPE_GPG_PASSPHRASE")).map(_.toCharArray)

description := "A dependency free api for Codacy Tools"

scmInfo := Some(
  ScmInfo(url("https://github.com/codacy/codacy-plugins-api"), "scm:git:git@github.com:codacy/codacy-plugins-api.git"))

publicMvnPublish
