package edu.spbau.master.software.design.shell.command.impl

import edu.spbau.master.software.design.shell.app.signals.ExitSignalUpdater
import edu.spbau.master.software.design.shell.command.Command
import edu.spbau.master.software.design.shell.command.Command.ReturnType
import edu.spbau.master.software.design.shell.model.CommandModel


/**
  * Exit from REPL
  *
  * @author Baidin Dima
  */
class ExitCommand(exitSignalUpdater: ExitSignalUpdater) extends Command {

  override def execute(command: CommandModel): ReturnType = {
    exitSignalUpdater.isExit = true
    ""
  }

}
