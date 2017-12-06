package de.maikfigura.filesearcher

import java.io.File

class Matcher(filter: String, rootLocation: String) {
  val rootIOOBject = FileConverter.convertToIOObject(new File(rootLocation))

  def execute() = {
    val matchedFiles = rootIOOBject match {
      case file: FileObject if FilterChecker(filter) matches file.name => List(file)
      case directory: DirectoryObject => ???
      case _ => List()
    }

    matchedFiles map(iOObject => iOObject.name)
  }
}
