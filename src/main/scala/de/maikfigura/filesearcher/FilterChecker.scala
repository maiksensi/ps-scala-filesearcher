package de.maikfigura.filesearcher

/**
  * This class is private as `filter` is not a val
  *
  * @param filter the filter to be used
  */
class FilterChecker(filter: String) {
  /**
    * Does the file contain the search filter defined?
    * @param content the content (name of file) of a file
    * @return True if filter is contained in content, else False.
    */
  def matches(content: String): Boolean = content.contains(filter)

  /**
    * Iterate over a list of files and yield all matching files, that contain
    * the specified filter.
    * @param fileObjects
    * @return
    */
  def findMatchedFiles(fileObjects: List[FileObject]): List[FileObject] = {
    for (fileObject <- fileObjects
         if matches(fileObject.name))
      yield fileObject
  }
}
