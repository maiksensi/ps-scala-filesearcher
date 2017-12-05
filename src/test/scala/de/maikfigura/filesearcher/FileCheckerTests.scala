package de.maikfigura.filesearcher

import org.scalatest.FlatSpec

class FileCheckerTests extends FlatSpec {
  "FilterChecker passed a list where one file matches the filter" should
  "return a list with that file" in {

    // create a new file object with name 'match'
    val matchingFile = new FileObject("match")

    // create a list of files, one file matches the filter
    val listOfFiles = List(new FileObject("random"), matchingFile)

    // call actual business logic and test whether we find the file matching the filter
    val matchedFiles = new FilterChecker("match").findMatchedFiles(listOfFiles)
    assert(matchedFiles == List(matchingFile)) // object equality not ref. equality!
  }
}
