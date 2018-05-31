import sbt._

resolvers ++= Seq(DefaultMavenRepository,
                  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
                  "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
                  Classpaths.typesafeReleases,
                  Classpaths.sbtPluginReleases)

// Sonatype publishing
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "0.5.1")
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.0.0")

// Dependency updates
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.1.9")

// Coverage
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.1")
addSbtPlugin("com.codacy" % "sbt-codacy-coverage" % "1.3.12")
