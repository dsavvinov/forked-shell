package edu.spbau.master.software.design.shell.command.impl

import java.io.File

import edu.spbau.master.software.design.shell.model.{CommandModel, CommandName}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

/**
  * @author Baidin Dima
  */
@RunWith(classOf[JUnitRunner])
class PwdCommandSpec extends FlatSpec with Matchers {

  it should "should return current directory" in {
    new PwdCommand().execute(
      CommandModel(CommandName("pwd"))) shouldEqual new File(".").getAbsolutePath
  }

}
