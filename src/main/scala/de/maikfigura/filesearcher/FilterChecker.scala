package de.maikfigura.filesearcher

import java.io.File

import scala.io.Source
import scala.util.control.NonFatal

/**
  * This class is private as `filter` is not a val
  *
  * @param filter the filter to be used
  */
class FilterChecker(filter: String) {

  def findMatchedContentCount(file: File): Boolean = {
    try {
      val fileSource = Source.fromFile(file)
      try {
        fileSource.getLines() exists(line => matches(line))
      }
      catch {
        case NonFatal(_) => false
      }
      finally {
        fileSource.close()
      }
    }
    catch {
      case NonFatal(_) => false
    }
  }


  /**
    * Does the file contain the search filter defined
    *
    * @param content the content or (name of file) of a file
    * @return True if filter is contained in content, else False.
    */
  def matches(content: String): Boolean = content contains filter // infix expression

  /**
    * Iterate over a list of files and yield all matching files, that contain
    * the specified filter.
    * @param iOObjects the objects we encounter while matching
    * @return a List of IOObjects that match our filter
    */
  def findMatchedFiles(iOObjects: List[IOObject]): List[IOObject] =
    for (iOObject <- iOObjects
         if iOObject.isInstanceOf[FileObject]
         if matches(iOObject.name))
      yield iOObject
}

/**
  * Companion
  * This way we can remove the new keyword when instanciating the class.
  */
object FilterChecker {
  def apply(filter: String) = new FilterChecker(filter)
}
