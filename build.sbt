val scala212 = "2.12.17"
val scala213 = "2.13.10"
val scala3 = "3.2.2"

ThisBuild / scalaVersion := scala212
ThisBuild / crossScalaVersions := Seq(scala212, scala213, scala3)

lazy val `codacy-plugins-api` =
  crossProject(JVMPlatform, NativePlatform)
    .crossType(CrossType.Pure)
    .settings(name := "codacy-plugins-api",
              organization := "com.codacy",
              libraryDependencies ++= Seq("wordspec", "shouldmatchers").map(m =>
                "org.scalatest" %%% s"scalatest-$m" % "3.2.14" % Test),
              Compile / unmanagedSourceDirectories += {
                val sourceDir = (ThisBuild / baseDirectory).value / name.value / "src" / "main"
                CrossVersion.partialVersion(scalaVersion.value) match {
                  case Some((major, minor)) if major > 2 || minor >= 13 =>
                    sourceDir / "scala-2.13+"
                  case _ =>
                    sourceDir / "scala-2.13-"
                }
              },
              pgpPassphrase := Option(System.getenv("SONATYPE_GPG_PASSPHRASE")).map(_.toCharArray),
              description := "A dependency free api for Codacy Tools",
              scmInfo := Some(
                ScmInfo(url("https://github.com/codacy/codacy-plugins-api"),
                        "scm:git:git@github.com:codacy/codacy-plugins-api.git")),
              publicMvnPublish)

lazy val root =
  project
    .in(file("."))
    .settings(publish / skip := true)
    .aggregate(`codacy-plugins-api`.jvm, `codacy-plugins-api`.native)
