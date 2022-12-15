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
  }
}
