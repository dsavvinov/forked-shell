package edu.spbau.master.software.design.shell.command.impl

import java.nio.file.{Path, Paths}

import edu.spbau.master.software.design.shell.command.Command
import edu.spbau.master.software.design.shell.command.Command.ReturnType
import edu.spbau.master.software.design.shell.model.CommandModel

/**
  * Created by dsavvinov on 20.10.16.
  */
class CdCommand extends Command {
  override def execute(command: CommandModel): ReturnType = {
    require(command.args.size == 1, "cd should have exactly one argument")
    val newFolderRelative: Path = Paths.get(command.args.head.value)
    val currentFolder: Path = Paths.get(System.getProperty("user.dir"))
    val newFolderAbsolute: Path = currentFolder.resolve(newFolderRelative)

    System.setProperty("user.dir", newFolderAbsolute.toString)

    ""
  }
}
