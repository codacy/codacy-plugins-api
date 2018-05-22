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

case class LineComplexity(line: Int, value: Int)

