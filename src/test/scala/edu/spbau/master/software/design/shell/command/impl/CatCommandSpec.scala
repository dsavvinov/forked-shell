package edu.spbau.master.software.design.shell.command.impl

import java.io.{File, FileOutputStream, PrintWriter}

import edu.spbau.master.software.design.shell.model.{CommandArg, CommandModel, CommandName}
import org.junit.runner.RunWith
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.junit.JUnitRunner

import scala.io.Source

/**
  * @author Baidin Dima
  */
@RunWith(classOf[JUnitRunner])
class CatCommandSpec extends FlatSpec with Matchers {

    it should "return file's data" in {
      val fileContent = "tmp value"
      val file = writeInTmpFile(fileContent)
      (new CatCommand).execute(
        CommandModel(CommandName("cat"), CommandArg(file.getAbsolutePath))
      ) shouldEqual fileContent
    }

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
