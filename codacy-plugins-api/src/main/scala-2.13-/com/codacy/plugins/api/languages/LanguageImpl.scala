package com.codacy.plugins.api.languages

import Languages._

private[languages] object LanguagesImpl {
  def extensionsByLanguageImpl: Map[Language, Set[String]] =
    all.map { lang =>
      (lang, lang.extensions)
    }(collection.breakOut)

  def filenamesByLanguageImpl: Map[Language, Set[String]] =
    all.map { lang =>
      (lang, lang.files)
    }(collection.breakOut)

  def languageByExtensionImpl: Map[String, Language] =
    all.flatMap { lang =>
      lang.extensions.map(extension => (extension.toLowerCase(), lang))
    }(collection.breakOut)

  def languageByFilenameImpl: Map[String, Language] =
    all.flatMap { lang =>
      lang.files.map(file => (file.toLowerCase(), lang))
    }(collection.breakOut)

  def forPathImpl(
    filePath: String,
    customExtensions: List[(Language, Seq[String])] = List.empty[(Language, Seq[String])]): Option[Language] = {
    lazy val languageByCustomExtension: List[(String, Language)] = {
      val customExtensionsMap: Map[Language, Set[String]] = customExtensions.map {
        case (lang, exts) =>
          (lang, exts.to[Set] ++ extensionsByLanguage.getOrElse(lang, Set.empty))
      }(collection.breakOut)

      customExtensionsMap.flatMap {
        case (lang, extensions) => extensions.map(extension => (extension.toLowerCase, lang))
      }.toList.sortBy {
        case (ext, lang) => (lang.toString, ext)
      }
    }

    filePath.split('/').lastOption.flatMap { filename =>
      languageByFilename
        .get(filename.toLowerCase)
        .orElse {
          languageByCustomExtension.collectFirst { case (ext, lang) if filename.endsWith(ext) => lang }
        }
        .orElse {
          (for {
            extension <- filename.split('.').lastOption
            dottedExtension = s".$extension"
          } yield languageByExtension.get(dottedExtension.toLowerCase)).flatten
        }
    }
  }
}
