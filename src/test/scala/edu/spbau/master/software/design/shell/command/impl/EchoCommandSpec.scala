package edu.spbau.master.software.design.shell.command.impl

import edu.spbau.master.software.design.shell.model.{CommandArg, CommandModel, CommandName}
import org.junit.runner.RunWith
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.junit.JUnitRunner

/**
  * @author Baidin Dima
  */
@RunWith(classOf[JUnitRunner])
class EchoCommandSpec extends FlatSpec with Matchers {

  it should "Should return all args" in {
    new EchoCommand().execute(
      CommandModel(CommandName("echo"), CommandArg("value"), CommandArg("1999"))
    ) shouldEqual "value 1999"
  }

  it should "Should return no args if no args" in {
    new EchoCommand().execute(
      CommandModel(CommandName("echo"))
    ) shouldEqual ""
  }

}
