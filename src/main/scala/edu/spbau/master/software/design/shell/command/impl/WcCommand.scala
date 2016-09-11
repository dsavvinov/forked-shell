package edu.spbau.master.software.design.shell.command.impl

import java.nio.file.{Files, Paths}

import edu.spbau.master.software.design.shell.command.Command
import edu.spbau.master.software.design.shell.command.Command.ReturnType
import edu.spbau.master.software.design.shell.model.CommandModel

/**
  *
  * @author Baidin Dima
  */
class WcCommand extends Command {
  override def execute(command: CommandModel): ReturnType = {
    require(command.args.length == 1, " Cat must have 1 arg")

    val lines = if (Files.exists(Paths.get(command.args.head.value))) {
      scala.io.Source.fromFile(command.args.head.value).getLines()
    } else {
      command.args.head.value.split("\n").toSeq
    }

    val lineCount = lines.size
    val wordCount = lines.flatMap(_.split("\\W+")).size
    val charCount = lines.flatMap(_.toCharArray).size

    Seq(lineCount, wordCount, charCount).mkString(" ")
  }
}
