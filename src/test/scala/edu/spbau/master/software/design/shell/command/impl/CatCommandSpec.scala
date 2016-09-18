package edu.spbau.master.software.design.shell.command.impl

import edu.spbau.master.software.design.shell.command.impl.TestUtils._
import edu.spbau.master.software.design.shell.model.{CommandArg, CommandModel, CommandName}
import org.junit.runner.RunWith
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.junit.JUnitRunner

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

}
