package edu.spbau.master.software.design.shell.command.impl

import java.nio.file.{Files, Path, Paths}
import java.util.stream.Collectors

import edu.spbau.master.software.design.shell.command.Command
import edu.spbau.master.software.design.shell.command.Command.ReturnType
import edu.spbau.master.software.design.shell.model.CommandModel

/**
  * Created by dsavvinov on 20.10.16.
  */
class LsCommand extends Command {
  override def execute(command: CommandModel): ReturnType = {
    val curFolder: Path = Paths.get(System.getProperty("user.dir"))
    curFolder.toFile.list().mkString("\n")
  }
}
