package codacy.docker.api.metrics


case class FileMetrics(filename: String,
                       complexity: Option[Int] = None,
                       buildtime: Option[Long] = None,
                       loc: Option[Int] = None,
                       cloc: Option[Long] = None,
                       duplicatedLines: Option[Int] = None,
                       nrMethods: Option[Int] = None,
                       nrClasses: Option[Int] = None,
                       lineComplexities: Set[LineComplexity] = Set.empty)

//TODO: should I remove the Class and Method metrics, since we only using their total number?
//or should I remove some of their attributes?
case class ClassMetrics(className: String,
                        complexity: Option[Long] = None,
                        loc: Option[Long] = None,
                        cloc: Option[Long] = None,
                        line: Option[Int] = None)

case class MethodMetrics(methodName: String,
                         complexity: Option[Long] = None,
                         loc: Option[Long] = None,
                         cloc: Option[Long] = None,
                         line: Option[Int] = None)

case class LineComplexity(line: Int, value: Int)

