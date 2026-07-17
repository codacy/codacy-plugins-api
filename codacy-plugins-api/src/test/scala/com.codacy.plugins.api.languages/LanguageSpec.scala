package com.codacy.plugins.api.languages

import org.scalatest.matchers.should.Matchers
import org.scalatest.OptionValues
import org.scalatest.wordspec.AnyWordSpec

class LanguageSpec extends AnyWordSpec with Matchers with OptionValues {
  "Languages" should {
    "filter by language" in {
      val expected = Seq("src/main/scala/com/codacy/File1.scala",
                         "src/main/scala/com/codacy/File1.java",
                         "src/main/scala/com/codacy/File1.sc")

      val files = Languages
        .filter(Set("src/main/scala/com/codacy/File1.scala",
                    "src/main/scala/com/codacy/File1.java",
                    "src/main/scala/com/codacy/File1.sc",
                    "src/main/scala/com/codacy/File1.rb",
                    "src/main/scala/com/codacy/File2.rb",
                    "src/main/scala/com/codacy/File1.py"),
                Set(Languages.Scala, Languages.Java),
                Map((Languages.Scala, Set(".sc"))))
        .toList

      files should contain theSameElementsAs expected
    }

    "forPath" in {
      Languages.forPath("src/main/scala/com/codacy/File1.scala").value shouldBe Languages.Scala
      Languages
        .forPath("src/main/scala/com/codacy/File1.sc", List((Languages.Scala, Seq(".sc"))))
        .value shouldBe Languages.Scala
      Languages.forPath("src/File3.mjs").value shouldBe Languages.Javascript
    }

    "forPath resolves extensions case-insensitively" in {
      // extensions are stored lowercase; lookup lowercases the file's extension too
      Languages.forPath("src/analysis.R").value shouldBe Languages.R
      Languages.forPath("man/topic.Rd").value shouldBe Languages.R
      Languages.forPath("src/Main.FS").value shouldBe Languages.FSharp
    }

    "forPath resolves newly added extensions" in {
      val cases = Seq("mod.hrl" -> Languages.Erlang,
                      "script.es" -> Languages.Erlang,
                      "cli.escript" -> Languages.Erlang,
                      "legacy.f" -> Languages.Fortran,
                      "legacy.for" -> Languages.Fortran,
                      "legacy.f77" -> Languages.Fortran,
                      "modern.f08" -> Languages.Fortran,
                      "sig.fsi" -> Languages.FSharp,
                      "script.fsx" -> Languages.FSharp,
                      "lib.pm" -> Languages.Perl,
                      "docs.pod" -> Languages.Perl,
                      "script.perl" -> Languages.Perl,
                      "some.t" -> Languages.Perl,
                      "man.rd" -> Languages.R,
                      "sweave.rsx" -> Languages.R,
                      "page.htm" -> Languages.HTML,
                      "page.xhtml" -> Languages.HTML,
                      "rules.pro" -> Languages.Prolog,
                      "rules.prolog" -> Languages.Prolog,
                      "core.cl" -> Languages.Lisp,
                      "project.sb3" -> Languages.Scratch,
                      "sprite.sprite3" -> Languages.Scratch,
                      "config.luau" -> Languages.Lua)
      cases.foreach {
        case (path, language) => Languages.forPath(path).value shouldBe language
      }
    }

    "forPath resolves newly added manifest and lock files" in {
      val cases = Seq("rebar.config" -> Languages.Erlang,
                      "rebar.lock" -> Languages.Erlang,
                      "elm.json" -> Languages.Elm,
                      "elm-package.json" -> Languages.Elm,
                      "paket.lock" -> Languages.FSharp,
                      "cpanfile" -> Languages.Perl,
                      "cpanfile.snapshot" -> Languages.Perl,
                      "renv.lock" -> Languages.R,
                      ".Rprofile" -> Languages.R,
                      "Project.toml" -> Languages.Julia,
                      "dune-project" -> Languages.OCaml)
      cases.foreach {
        case (path, language) => Languages.forPath(path).value shouldBe language
      }
    }
  }
}
