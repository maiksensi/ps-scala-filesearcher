package de.maikfigura.filesearcher

import java.io.File

// static class, singleton
object FileConverter {
  def convertToIOObject(file: File): IOObject =
    // is file a directory?
    if(file.isDirectory) DirectoryObject(file) // yes then wrap it into a directory object
    else FileObject(file) // no wrap it into a file object

}
