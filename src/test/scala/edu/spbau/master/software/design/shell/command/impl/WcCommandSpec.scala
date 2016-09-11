package edu.spbau.master.software.design.shell.command.impl

import edu.spbau.master.software.design.shell.model.{CommandArg, CommandModel, CommandName}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

/**
  * @author Baidin Dima
  */
@RunWith(classOf[JUnitRunner])
class WcCommandSpec extends FlatSpec with Matchers {

  it should "return lines count, words count and chars count" in {
    val text = "first word\nsecond word"
    new WcCommand().execute(
      CommandModel(CommandName("wc"), CommandArg(text))) shouldEqual "2 4 21"
  }

}
