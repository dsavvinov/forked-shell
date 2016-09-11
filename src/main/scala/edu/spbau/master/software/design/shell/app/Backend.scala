package edu.spbau.master.software.design.shell.app

import edu.spbau.master.software.design.shell.app.signals.ExitSignalUpdater
import edu.spbau.master.software.design.shell.command.CommandFactory
import edu.spbau.master.software.design.shell.parser.CommandParser

/**
  * @author Baidin Dima
  */
case class Backend(environment: Environment,
                   commandParser: CommandParser,
                   signalUpdater: ExitSignalUpdater,
                   commandFactory: CommandFactory) {
  require(environment != null, "Environment is null!")
  require(commandParser != null, "Command parser is null!")
  require(signalUpdater != null, "Signal updater is null!")
  require(commandFactory != null, "Command factory is null!")
}
