package codacy.docker.api.duplication

//This was declared in the seed along with the other two case classes.
//I can see it being used in the worker nor the seed, should I remove it?
case class DuplicationRequest(directory: String, files: List[String])

case class DuplicationCloneFile(filePath: String, startLine: Int, endLine: Int)

case class DuplicationClone(cloneLines: String, nrTokens: Int, nrLines: Int, files: Seq[DuplicationCloneFile])


