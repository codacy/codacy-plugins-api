name := "codacy-plugins-api"
version := "1.0.0-SNAPSHOT"
organization := "com.codacy"

scalaVersion := "2.11.12"
crossScalaVersions := Seq(scalaVersion.value, "2.12.4")

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "4.0.2" % Test)

description := "Library to develop Codacy tool plugins"
homepage := Some(url("https://github.com/codacy/codacy-plugins-api"))
startYear := Some(2016)
organizationName := "Codacy"
organizationHomepage := Some(new URL("https://www.codacy.com"))
licenses := Seq("The Apache Software License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := { _ =>
  false
}
publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (version.value.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
pomExtra :=
  <scm>
    <url>https://github.com/codacy/codacy-plugins-api</url>
    <connection>scm:git:git@github.com:codacy/codacy-engine-scala-seed.git</connection>
    <developerConnection>scm:git:https://github.com/codacy/codacy-plugins-api.git</developerConnection>
  </scm>
    <developers>
      <developer>
        <id>johannegger</id>
        <name>Johann</name>
        <email>johann [at] codacy.com</email>
        <url>https://github.com/johannegger</url>
      </developer>
    </developers>
