package edu.spbau.master.software.design.shell.command.impl

import edu.spbau.master.software.design.shell.app.signals.{ExitSignalListener, ExitSignalUpdater}
import edu.spbau.master.software.design.shell.model.{CommandModel, CommandName}
import org.junit.runner.RunWith
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.junit.JUnitRunner

/**
  * @author Baidin Dima
  */
@RunWith(classOf[JUnitRunner])
class ExitCommandSpec extends FlatSpec with Matchers {

  it should "set exit signal to true" in {
    val signalUpdater = new ExitSignalUpdater
    val exitSignalListener = new ExitSignalListener {
      override def isExit: Boolean = signalUpdater.isExit
    }
    new ExitCommand(signalUpdater).execute(CommandModel(CommandName("exit")))
    exitSignalListener.isExit shouldEqual true
  }

}
