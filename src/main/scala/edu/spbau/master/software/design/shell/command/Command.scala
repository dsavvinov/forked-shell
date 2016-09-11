package edu.spbau.master.software.design.shell.command

import edu.spbau.master.software.design.shell.command.Command.ReturnType
import edu.spbau.master.software.design.shell.model.CommandModel

/**
  * @author Baidin Dima
  */
trait Command {

  def execute(command: CommandModel): ReturnType

}

object Command {
  type ReturnType = String
}