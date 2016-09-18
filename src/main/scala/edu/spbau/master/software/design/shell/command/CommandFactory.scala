package edu.spbau.master.software.design.shell.command

import edu.spbau.master.software.design.shell.app.{Backend, Environment}
import edu.spbau.master.software.design.shell.app.signals.ExitSignalUpdater
import edu.spbau.master.software.design.shell.command.impl._
import edu.spbau.master.software.design.shell.model.CommandName

/**
  * Creates command by command name
  *
  * @author Baidin Dima
  */
class CommandFactory(environment: Environment,
                     signalUpdater: ExitSignalUpdater) {

  private val knownCommands: Map[CommandName, Command] = Map(
    CommandName("cat") → {
      new CatCommand
    },
    CommandName("echo") → {
      new EchoCommand
    },
    CommandName("wc") → {
      new WcCommand
    },
    CommandName("pwd") → {
      new PwdCommand
    },
    CommandName("exit") → {
      new ExitCommand(exitSignalUpdater = signalUpdater)
    },
    CommandName("=") → {
      new AssignmentCommand(environment)
    },
    CommandName("grep") → {
      new GrepCommand
    }
  )

  private def getDefaultCommand: Command = {
    new ProcessCommand
  }

  private[command] def newCommand(name: CommandName): Command = {
    knownCommands.getOrElse(name, getDefaultCommand)
  }

}
