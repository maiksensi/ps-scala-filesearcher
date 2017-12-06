package de.maikfigura.filesearcher

import org.scalatest.FlatSpec

class MatcherTests extends FlatSpec {
  "Matcher that is passed a file matching the filter" should
  "return a list with that file name" in {
    // dont use infix, as this will have side effects (accessing filesystem)
    val matcher = new Matcher("fake", "fakePath") // filter and filePath

    val results = matcher.execute()

    assert(results == List("fakePath"))
  }

}
