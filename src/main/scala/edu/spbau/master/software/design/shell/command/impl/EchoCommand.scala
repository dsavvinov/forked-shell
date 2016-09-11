package edu.spbau.master.software.design.shell.command.impl

import edu.spbau.master.software.design.shell.command.Command
import edu.spbau.master.software.design.shell.command.Command.ReturnType
import edu.spbau.master.software.design.shell.model.CommandModel

/**
  * Print args
  *
  * @author Baidin Dima
  */
class EchoCommand extends Command {
  override def execute(command: CommandModel): ReturnType = {
    command.args.map(_.value).mkString(" ")
  }
}
