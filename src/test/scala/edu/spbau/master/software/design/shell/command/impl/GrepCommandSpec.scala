package edu.spbau.master.software.design.shell.command.impl

import edu.spbau.master.software.design.shell.model.{CommandArg, CommandModel, CommandName}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}


/**
  * @author Baidin Dima
  */
@RunWith(classOf[JUnitRunner])
class GrepCommandSpec extends FlatSpec with Matchers {

  val filePath = "../../src/test/resources/random_text.txt"

  it should "find lines with matching text" in {
    (new GrepCommand).execute(
      CommandModel(
        CommandName("grep"),
        CommandArg("test"),
        CommandArg(filePath)
      )) shouldEqual "test"
  }

  it should "find lines with matching text with no case sensitive" in {
    (new GrepCommand).execute(
      CommandModel(
        CommandName("grep"),
        CommandArg("-i"),
        CommandArg("test"),
        CommandArg(filePath)
      )).split("\n") should contain allOf("test", "TEST")
  }

  it should "find lines with matching text with only whole words" in {
    (new GrepCommand).execute(
      CommandModel(
        CommandName("grep"),
        CommandArg("-w"),
        CommandArg("tes"),
        CommandArg(filePath)
      )) shouldEqual ""
  }

  it should "find lines with matching text with max occurrence" in {
    (new GrepCommand).execute(
      CommandModel(
        CommandName("grep"),
        CommandArg("-i"),
        CommandArg("-n"),
        CommandArg("1"),
        CommandArg("tes"),
        CommandArg(filePath)
      )) shouldEqual "test"
  }

  it should "find lines with matching text in string" in {
    (new GrepCommand).execute(
      CommandModel(
        CommandName("grep"),
        CommandArg("test"),
        CommandArg(
          "hello\n1 2 3 4 5\nfirst second\ntest\nTEST"
        )
      )) shouldEqual "test"
  }

}
