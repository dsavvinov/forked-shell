package edu.spbau.master.software.design.shell.command.impl

import java.nio.file.{Files, Paths}

import edu.spbau.master.software.design.shell.command.Command
import edu.spbau.master.software.design.shell.command.Command.ReturnType
import edu.spbau.master.software.design.shell.model.CommandModel

/**
  * Read file's text
  *
  * @author Baidin Dima
  */
class CatCommand extends Command {
  override def execute(command: CommandModel): ReturnType = {
    require(command.args.length == 1, " Cat must have 1 arg")
    require(Files.exists(Paths.get(command.args.head.value)), "No such file")

    scala.io.Source.fromFile(command.args.head.value).mkString
  }
}
