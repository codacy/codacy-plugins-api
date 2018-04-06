package com.codacy.api.dtos

sealed abstract class Language(val extensions: Set[String],
                               val overriddenName: Option[String] = Option.empty[String],
                               val files: Set[String] = Set.empty[String]) {
  self: Product =>
  val name: String = overriddenName.getOrElse(self.productPrefix)

  override def toString: String = name
}

object Languages {

  lazy val extensionsByLanguage: Map[Language, Set[String]] = all.map { lang =>
    (lang, lang.extensions)
  }(collection.breakOut)

  lazy val filenamesByLanguage: Map[Language, Set[String]] = all.map { lang =>
    (lang, lang.files)
  }(collection.breakOut)

  lazy val languageByExtension: Map[String, Language] = all.flatMap { lang =>
    lang.extensions.map(extension => (extension.toLowerCase(), lang))
  }(collection.breakOut)

  lazy val languageByFilename: Map[String, Language] = all.flatMap { lang =>
    lang.files.map(file => (file.toLowerCase(), lang))
  }(collection.breakOut)

  def extensions(language: Language): Option[Set[String]] = extensionsByLanguage.get(language)

  def filenames(language: Language): Option[Set[String]] = filenamesByLanguage.get(language)

  def fromName(name: String): Option[Language] = all.find(lang => name.equalsIgnoreCase(lang.name))

  def forPath(
    filePath: String,
    customExtensions: List[(Language, Seq[String])] = List.empty[(Language, Seq[String])]): Option[Language] = {
    lazy val languageByCustomExtension: List[(String, Language)] = {
      val customExtensionsMap: Map[Language, Set[String]] = customExtensions.map {
        case (lang, exts) =>
          (lang, extensionsByLanguage.getOrElse(lang, Set.empty) ++ exts)
      }(collection.breakOut)

      customExtensionsMap.flatMap {
        case (lang, extensions) => extensions.map(extension => (extension.toLowerCase, lang))
      }.toList.sortBy {
        case (ext, lang) => (lang.toString, ext)
      }
    }

    filePath.split('/').lastOption.flatMap { filename =>
      languageByCustomExtension.collectFirst { case (ext, lang) if filename.endsWith(ext) => lang }.orElse {
        (for {
          extension <- filename.split('.').lastOption
          dottedExtension = s".$extension"
        } yield languageByExtension.get(dottedExtension.toLowerCase)).flatten
      }.orElse(languageByFilename.get(filename.toLowerCase))
    }
  }

  def filter(files: Set[String],
             languages: Set[Language],
             customExtensions: Map[Language, Set[String]] = Map.empty[Language, Set[String]]): Set[String] = {
    val allExtensions = languages.flatMap { language =>
      customExtensions.getOrElse(language, extensionsByLanguage.getOrElse(language, Set.empty))
    }

    files.filter(file => allExtensions.exists(e => file.endsWith(e)))
  }

  val all: Set[Language] = Set(Javascript,
                               Scala,
                               CSS,
                               PHP,
                               Python,
                               Ruby,
                               Java,
                               CoffeeScript,
                               Swift,
                               CPP,
                               C,
                               Shell,
                               TypeScript,
                               Dockerfile,
                               SQL,
                               PLSQL,
                               JSON,
                               SASS,
                               LESS,
                               Go,
                               JSP,
                               Velocity,
                               XML,
                               Apex,
                               Elixir,
                               Clojure,
                               Rust,
                               Haskell,
                               Erlang,
                               YAML,
                               Dart,
                               Elm,
                               HTML,
                               Groovy,
                               VisualForce,
                               Perl,
                               CSharp,
                               VisualBasic,
                               ObjectiveC,
                               FSharp,
                               Cobol,
                               Fortran,
                               R,
                               Scratch,
                               Lua,
                               Lisp,
                               Prolog,
                               Julia,
                               Kotlin,
                               OCaml)

  // Support startdate: always
  case object Javascript extends Language(extensions = Set(".js", ".jsx"))

  case object Scala extends Language(extensions = Set(".scala"))

  case object CSS extends Language(extensions = Set(".css"))

  case object PHP extends Language(extensions = Set(".php"))

  case object Python extends Language(extensions = Set(".py"))

  // Support startdate: 31 August 2015
  case object Ruby
      extends Language(extensions = Set(".rb", ".gemspec", ".podspec", ".jbuilder", ".rake", ".opal"),
                       files = Set("Gemfile",
                                   "Gemfile.lock",
                                   "config.ru",
                                   "Rakefile",
                                   "Capfile",
                                   "Guardfile",
                                   "Podfile",
                                   "Thorfile",
                                   "Vagrantfile",
                                   "Berksfile",
                                   "Cheffile",
                                   "Vagabondfile",
                                   "Fastfile"))

  case object Java extends Language(extensions = Set(".java"))

  case object CoffeeScript extends Language(extensions = Set(".coffee"))

  // Support startdate: 27 June 2016
  case object Swift extends Language(extensions = Set(".swift"))

  // Support startdate: 18 July 2016
  case object CPP extends Language(extensions = Set(".cpp", ".hpp", ".cc", ".cxx"))

  case object C extends Language(extensions = Set(".c", ".h"))

  case object Shell extends Language(extensions = Set(".sh", ".bash"))

  // Support startdate: 24 November 2016
  case object TypeScript extends Language(extensions = Set(".ts", ".tsx"))

  // Support startdate: December 2016
  case object Dockerfile extends Language(extensions = Set(".dockerfile"), files = Set("Dockerfile"))

  // Support startdate: January 2017
  case object SQL extends Language(extensions = Set(".sql"))

  case object PLSQL
      extends Language(
        extensions = Set(".trg", // Triggers
                         ".prc",
                         ".fnc", // Standalone Procedures and Functions
                         ".pld", // Oracle*Forms
                         ".pls",
                         ".plh",
                         ".plb", // Packages
                         ".pck",
                         ".pks",
                         ".pkh",
                         ".pkb", // Packages
                         ".typ",
                         ".tyb", // Object Types
                         ".tps",
                         ".tpb" // Object Types
        ))

  // Support startdate: February 2017
  case object JSON extends Language(extensions = Set(".json"))

  case object SASS extends Language(extensions = Set(".scss"))

  case object LESS extends Language(extensions = Set(".less"))

  // Support startdate: March 2017
  case object Go extends Language(extensions = Set(".go"))

  case object JSP extends Language(extensions = Set(".jsp"))

  case object Velocity extends Language(extensions = Set(".vm"))

  case object XML extends Language(extensions = Set(".xml", ".xsl", ".wsdl", ".pom"))

  case object Apex extends Language(extensions = Set(".cls", ".trigger"))

  // Support startdate: April 2017
  case object VisualForce extends Language(extensions = Set(".component", ".page"))

  // Support startdate: May 2017
  case object CSharp extends Language(extensions = Set(".cs"))

  // Support startdate: September 2017
  case object Kotlin extends Language(extensions = Set(".kt", ".kts"))

  // Soon

  case object Elixir extends Language(extensions = Set(".ex", ".exs"))

  case object Clojure extends Language(extensions = Set(".clj", ".cljs", ".cljc", ".edn"))

  case object Rust extends Language(extensions = Set(".rs", ".rlib"))

  case object Haskell extends Language(extensions = Set(".hs", ".lhs"))

  case object Erlang extends Language(extensions = Set(".erl"))

  case object YAML extends Language(extensions = Set(".yaml", ".yml"))

  case object Dart extends Language(extensions = Set(".dart"))

  case object Elm extends Language(extensions = Set(".elm"))

  case object HTML extends Language(extensions = Set(".html"))

  case object Groovy extends Language(extensions = Set(".groovy"))

  // Others

  case object Perl extends Language(extensions = Set(".pl"))

  case object VisualBasic extends Language(extensions = Set(".vb"))

  case object ObjectiveC extends Language(overriddenName = Some("Objective C"), extensions = Set(".m"))

  case object FSharp extends Language(extensions = Set(".fs"))

  case object Cobol extends Language(extensions = Set(".cbl", ".cob"))

  case object Fortran extends Language(extensions = Set(".f90", ".f95", ".f03"))

  case object R extends Language(extensions = Set(".r"))

  case object Scratch extends Language(extensions = Set(".scratch", ".sb", ".sprite", ".sb2", ".sprite2"))

  case object Lua extends Language(extensions = Set(".lua"))

  case object Lisp extends Language(extensions = Set(".asd", ".el", ".lsp", ".lisp"))

  case object Prolog extends Language(extensions = Set(".P", ".swipl"))

  case object Julia extends Language(extensions = Set(".jl"))

  case object OCaml extends Language(extensions = Set(".ml", ".mli", ".mly", ".mll"))

}
