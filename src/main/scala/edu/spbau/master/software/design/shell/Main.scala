package edu.spbau.master.software.design.shell

import edu.spbau.master.software.design.shell.app.signals.ExitSignalListener
import edu.spbau.master.software.design.shell.app.{BackendBuilder, ConsoleReader}
import edu.spbau.master.software.design.shell.command.CommandManager
import edu.spbau.master.software.design.shell.utils.Logging

/**
  * @author Baidin Dima
  */
object Main extends App with Logging {

  log.info("Start")

  val backend = BackendBuilder.build()

  val commandManager = new CommandManager(backend.commandFactory) {
    override def isExit: Boolean = backend.signalUpdater.isExit
  }

  val consoleReader = new ConsoleReader(backend.commandParser,
    commandManager) with ExitSignalListener {

    override def isExit: Boolean = backend.signalUpdater.isExit
  }

  consoleReader.startRead()

}
