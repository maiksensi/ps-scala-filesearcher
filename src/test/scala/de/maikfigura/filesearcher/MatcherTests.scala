package de.maikfigura.filesearcher


import java.io.File
import java.nio.file.Paths

import org.scalatest.FlatSpec

import scala.language.postfixOps

class MatcherTests extends FlatSpec {
  val filePath = Paths get "./testfiles" toString

  "Matcher that is passed a file matching the filter" should
    "return a list with that file name" in {
    // dont use infix, as this will have side effects (accessing filesystem)
    val matcher = new Matcher("fake", "fakePath") // filter and filePath

    val results = matcher.execute()

    assert(results == List("fakePath"))
  }

  // this test actually needs a directory called testfiles with one file contained
  // called readme.txt
  "Matcher using a directory containing one file matching the filter" should
    "return a list with that file name" in {
    // create filter txt with a file but as file use a path (directory)
    val matcher = new Matcher("txt", filePath)
    val results = matcher.execute()

    assert(results == List("readme.txt"))
  }

  "Matcher that is not passed a root file location" should
    "use the current location" in {
    val matcher = new Matcher("filter")
    assert(matcher.rootLocation == (Paths get "." toString))
  }

  "Matcher with sub folder checking matching a root location with two subtree files matching" should
    "return a list with those file names" in {
    val searchSubDirectories = true
    val matcher = new Matcher("txt", filePath, searchSubDirectories)

    val results = matcher.execute()

    assert(results == List("notes.txt", "readme.txt"))
  }

  "Matcher given a path that has one file that matches file filter and content filter" should
    "return a list with that file name" in {
    val matcher = new Matcher("data", new File(Paths get "." toString) getCanonicalPath, true, Some("pluralsight"))
    val matchedFiles = matcher.execute()
    assert(matchedFiles == List("pluralsight.data"))
  }

  "Matcher given a path that has NO file that matches file filter and content filter" should
    "return an empty list" in {
    val matcher = new Matcher("txt", new File(Paths get "." toString) getCanonicalPath, true, Some("pluralsight"))
    val matchedFiles = matcher.execute()
    assert(matchedFiles == List())
  }
}
