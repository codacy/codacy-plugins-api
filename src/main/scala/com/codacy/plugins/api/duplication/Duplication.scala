package com.codacy.plugins.api.duplication

import com.codacy.plugins.api.{AnalysisProblem, ErrorMessage, Result, Source}

sealed trait DuplicationResult extends Result

case class DuplicationCloneFile(filePath: String, startLine: Int, endLine: Int)

case class DuplicationClone(cloneLines: String, nrTokens: Int, nrLines: Int, files: Seq[DuplicationCloneFile])
    extends DuplicationResult

case class DuplicationProblem(message: ErrorMessage, file: Option[Source.File], reason: AnalysisProblem.Reason)
    extends AnalysisProblem
    with DuplicationResult
