package com.codacy.plugins.api.duplication

case class DuplicationCloneFile(filePath: String, startLine: Int, endLine: Int)

case class DuplicationClone(cloneLines: String, nrTokens: Int, nrLines: Int, files: Seq[DuplicationCloneFile])
