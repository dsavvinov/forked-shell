package edu.spbau.master.software.design.shell.command.impl

import java.io.{File, PrintWriter}

import scala.io.Source

/**
  * @author Baidin Dima
  */
object TestUtils {

  def readFromFile(file: File): String = Source.fromFile(file).getLines.mkString

  def writeInTmpFile(value: String): File = {
    val tmpFile = createTmpFile()
    val printWriter = new PrintWriter(tmpFile)
    printWriter.write(value)
    printWriter.close()
    tmpFile
  }

  def createTmpFile(): File = {
    val file = File.createTempFile("cat", "tmp")
    file.deleteOnExit()
    file
  }

}
