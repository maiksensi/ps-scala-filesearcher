import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "de.maikfigura.filesearcher",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Scala file checker",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += scalaLogger
  )
