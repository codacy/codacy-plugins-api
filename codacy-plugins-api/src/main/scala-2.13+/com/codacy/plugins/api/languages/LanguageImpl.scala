package com.codacy.plugins.api.languages

import Languages._

private[languages] object LanguagesImpl {
  def extensionsByLanguageImpl: Map[Language, Set[String]] =
    all.view.map { lang =>
      (lang, lang.extensions)
    }.to(Map)

  def filenamesByLanguageImpl: Map[Language, Set[String]] =
    all.view.map { lang =>
      (lang, lang.files)
    }.to(Map)

  def languageByExtensionImpl: Map[String, Language] =
    all.view.flatMap { lang =>
      lang.extensions.map(extension => (extension.toLowerCase(), lang))
    }.to(Map)

  def languageByFilenameImpl: Map[String, Language] =
    all.view.flatMap { lang =>
      lang.files.map(file => (file.toLowerCase(), lang))
    }.to(Map)

  def forPathImpl(
    filePath: String,
    customExtensions: List[(Language, Seq[String])] = List.empty[(Language, Seq[String])]): Option[Language] = {
    lazy val languageByCustomExtension: List[(String, Language)] = {
      val customExtensionsMap: Map[Language, Set[String]] = customExtensions.view.map {
        case (lang, exts) =>
          (lang, exts.view.to(Set) ++ extensionsByLanguage.getOrElse(lang, Set.empty))
      }.to(Map)

      customExtensionsMap.view.flatMap {
        case (lang, extensions) => extensions.map(extension => (extension.toLowerCase, lang))
      }.to(List).sortBy {
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
