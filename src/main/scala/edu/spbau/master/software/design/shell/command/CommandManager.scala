package edu.spbau.master.software.design.shell.command

import edu.spbau.master.software.design.shell.app.signals.ExitSignalListener
import edu.spbau.master.software.design.shell.model.{CommandArg, CommandModel}
import edu.spbau.master.software.design.shell.utils.Logging

import scala.util.{Failure, Success, Try}


/**
  * Execute commands
  *
  * @author Baidin Dima
  */
abstract class CommandManager(commandFactory: CommandFactory)
  extends Logging with ExitSignalListener {

  private def loop(commands: List[CommandModel],
                   arg: Option[Command.ReturnType]): Try[Command.ReturnType] = commands match {
    case commandModel :: rest ⇒
      val command = commandFactory.newCommand(commandModel.name)
      val args = commandModel.args ++ arg.map(CommandArg(_))
      val newCommandModel = CommandModel(commandModel.name, args: _*)
      Try(command.execute(newCommandModel)) flatMap {
        case value ⇒
          log.debug(s"Command {} returned value $value", newCommandModel)
          if (rest.isEmpty || isExit) {
            Success(value)
          } else {
            loop(rest, Some(value))
          }
      }
    case Nil ⇒
      Failure(new RuntimeException("Empty command list"))
  }

  def executeWithPipe(commands: Seq[CommandModel]): Command.ReturnType = {
    log.debug("Start execute commands")

    loop(commands.toList, None) match {
      case Success(value) ⇒ value
      case Failure(ex) ⇒
        log.error(ex.getMessage)
        ex.getMessage
    }
  }


}
