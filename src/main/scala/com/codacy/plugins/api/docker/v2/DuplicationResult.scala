package com.codacy.plugins.api.docker.v2

import com.codacy.plugins.api.docker.v2
import com.codacy.plugins.api.{ErrorMessage, Source}

sealed trait DuplicationResult extends Result

object DuplicationResult {

  case class CloneFile(filePath: String, startLine: Int, endLine: Int)

  case class Clone(cloneLines: String, nrTokens: Int, nrLines: Int, files: Seq[CloneFile]) extends DuplicationResult

  case class Problem(message: ErrorMessage, file: Option[Source.File], reason: v2.Problem.Reason)
      extends v2.Problem
      with DuplicationResult

}
