name := "codacy-plugins-api"
version := "1.0.0-SNAPSHOT"
organization := "com.codacy"

scalaVersion := "2.11.12"
crossScalaVersions := Seq(scalaVersion.value, "2.12.4")

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "4.0.2" % Test)

// Sonatype repository settings
credentials += Credentials(
  "Sonatype Nexus Repository Manager",
  "oss.sonatype.org",
  sys.env.getOrElse("SONATYPE_USER", "username"),
  sys.env.getOrElse("SONATYPE_PASSWORD", "password")
)
publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := { _ =>
  false
}
// Add the default sonatype repository setting
publishTo := sonatypePublishTo.value

organizationName := "Codacy"
organizationHomepage := Some(new URL("https://www.codacy.com"))
startYear := Some(2016)
description := "Library to develop Codacy tool plugins"
licenses := Seq("The Apache Software License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))
homepage := Some(url("http://www.github.com/codacy/codacy-plugins-api/"))
pomExtra :=
  <scm>
    <url>https://github.com/codacy/codacy-plugins-api</url>
    <connection>scm:git:git@github.com:codacy/codacy-plugins-api.git</connection>
    <developerConnection>scm:git:https://github.com/codacy/codacy-plugins-api.git</developerConnection>
  </scm>
    <developers>
      <developer>
        <id>johannegger</id>
        <name>Johann</name>
        <email>johann [at] codacy.com</email>
        <url>https://github.com/johannegger</url>
      </developer>
      <developer>
        <id>rtfpessoa</id>
        <name>Rodrigo Fernandes</name>
        <email>rodrigo [at] codacy.com</email>
        <url>https://github.com/rtfpessoa</url>
      </developer>
      <developer>
        <id>bmbferreira</id>
        <name>Bruno Ferreira</name>
        <email>bruno.ferreira [at] codacy.com</email>
        <url>https://github.com/bmbferreira</url>
      </developer>
      <developer>
        <id>xplosunn</id>
        <name>Hugo Sousa</name>
        <email>hugo [at] codacy.com</email>
        <url>https://github.com/xplosunn</url>
      </developer>
      <developer>
        <id>pedrocodacy</id>
        <name>Pedro Amaral</name>
        <email>pamaral [at] codacy.com</email>
        <url>https://github.com/pedrocodacy</url>
      </developer>
    </developers>
