package de.maikfigura.filesearcher

import java.io.File

/**
  * Trait (interface) for our different objects
  */
trait IOObject {
  val file: File
  val name: String = file.getName
}

/**
  * Concrete implementations (make sure to use `val` to implement)
  * Case class constructor arguments always public
  * Companion is built-in
  * @param file the file object that is a directory
  */
case class DirectoryObject(file: File) extends IOObject {
  def children(): List[IOObject] = {
    try {
      file.listFiles().toList map (file => FileConverter convertToIOObject file)
    }
    catch {
      case _: NullPointerException => List()
    }
  }
}

case class FileObject(file: File) extends IOObject
