package edu.spbau.master.software.design.shell.command.impl

import edu.spbau.master.software.design.shell.app.Environment
import edu.spbau.master.software.design.shell.command.Command
import edu.spbau.master.software.design.shell.command.Command.ReturnType
import edu.spbau.master.software.design.shell.model.{CommandModel, VariableName, VariableValue}

/**
  * Set variable value in environment
  *
  * @author Baidin Dima
  */
class AssignmentCommand(environment: Environment) extends Command {
  override def execute(command: CommandModel): ReturnType = {
    require(command.args.length == 2, "Assign must have 2 args")

    environment.updateVariableValue(VariableName(command.args.head.value),
      VariableValue(command.args(1).value))

    command.args(1).value
  }

}
