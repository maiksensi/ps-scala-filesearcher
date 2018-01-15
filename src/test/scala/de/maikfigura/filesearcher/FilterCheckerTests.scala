package de.maikfigura.filesearcher

import java.io.File
import java.nio.file.Paths

import org.scalatest.FlatSpec

class FilterCheckerTests extends FlatSpec {
  "FilterChecker passed a list where one file matches the filter" should
  "return a list with that file" in {
    // create a list of files, one file matches the filter
    val listOfFiles = List(FileObject(new File("random")), FileObject(new File("match")))

    // call actual business logic and test whether we find the file matching the filter
    val matchedFiles = FilterChecker("match") findMatchedFiles listOfFiles
    assert(matchedFiles == List(FileObject(new File("match")))) // object equality not ref. equality!
  }

  "FilterChecker passed a list with a directory that matches the filter" should
  "not return the directory"
  val listOfIOObjects = List(FileObject(new File("random")), DirectoryObject(new File("match")))
  val matchedFiles: List[IOObject] = FilterChecker("match") findMatchedFiles listOfIOObjects
  assert(matchedFiles.isEmpty)

  "FilterChecker passed a file with content that matches the filter 3 times" should
  "return a 3" in {
    val isContentMatched = FilterChecker("pluralsight")
      .findMatchedContentCount(new File(Paths get "./testfiles/pluralsight.data" toString))
    assert(isContentMatched == 3)

  }

  "FilterChecker passed a file with content that does NOT match the filter" should
  "return a 0" in {
    val isContentMatched = FilterChecker("pluralsight")
      .findMatchedContentCount(new File(Paths get "./testfiles/readme.txt" toString))
    assert(isContentMatched == 3)
  }

}
