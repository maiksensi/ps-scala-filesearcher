package de.maikfigura.filesearcher

import java.io.File
import java.nio.file.Paths

import scala.annotation.tailrec

class Matcher(filter: String, val rootLocation: String = Paths get "." toString, val searchSubDirectories: Boolean = false,
              contentFilter: Option[String] = None) {
  val rootIOObject: IOObject = FileConverter.convertToIOObject(new File(rootLocation))

  def execute(): List[String] = {
    // helper method, only accessible from within execute
    // takes care of the recursive traversation for subdirectories
    @tailrec // marks this to be optimizable by the compiler, if this is not truly tail recursive we will get a error
    def recursiveMatch(files: List[IOObject], currentList: List[FileObject]): List[FileObject] =
      files match {
        case List() => currentList // terminator case
        case iOObject :: rest => // take first io object out of list
          iOObject match { // match against that ioobject
            case file: FileObject if FilterChecker(filter) matches file.name =>
              recursiveMatch(rest, file :: currentList) // call recursiveMatch with rest of list and add file to accumulator
            case directory: DirectoryObject =>
              recursiveMatch(rest ::: directory.children(), currentList) // call recursiveMatch with rest of list concatenated with further possible subdirectories and pass along the current accumulator
            case _ => recursiveMatch(rest, currentList)
          }
      }

    // match for file name or directory
    val matchedFiles = rootIOObject match {
      case file: FileObject if FilterChecker(filter) matches file.name => List(file)
      case directory: DirectoryObject =>
        if(searchSubDirectories) recursiveMatch(directory.children(), List())
        else FilterChecker(filter) findMatchedFiles directory.children()
      case _ => List()
    }

    // match for contents of files
    val contentFilteredFiles = contentFilter match {
      // case classes can be pattern matched directly, Some is "Optional idom"
      case Some(dataFilter) =>
        // use matchedFiles and check whether content matching data filter is present
        matchedFiles filter(iOObject => FilterChecker(dataFilter).findMatchedContentCount(iOObject.file))
      case None => matchedFiles
    }

    contentFilteredFiles map(iOObject => iOObject.name)
  }

}
