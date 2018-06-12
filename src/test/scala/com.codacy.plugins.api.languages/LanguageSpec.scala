package com.codacy.plugins.api.languages

import org.specs2.control.NoLanguageFeatures
import org.specs2.mutable.Specification

class LanguageSpec extends Specification with NoLanguageFeatures {
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
        .to[List]

      files should containTheSameElementsAs(expected)
    }

    "forPath" in {
      Languages.forPath("src/main/scala/com/codacy/File1.scala") should beEqualTo(Some(Languages.Scala))
      Languages.forPath("src/main/scala/com/codacy/File1.sc", List((Languages.Scala, Seq(".sc")))) should beEqualTo(
        Some(Languages.Scala))
    }
  }
}
