package edu.spbau.master.software.design.shell.app

import edu.spbau.master.software.design.shell.app.signals.ExitSignalListener
import edu.spbau.master.software.design.shell.command.CommandManager
import edu.spbau.master.software.design.shell.parser.CommandParser
import edu.spbau.master.software.design.shell.utils.Logging

import scala.io.StdIn._
import scala.util.{Failure, Success}

/**
  * Read console and print response
  *
  * @author Baidin Dima
  */
abstract class ConsoleReader(commandParser: CommandParser,
                             commandManager: CommandManager) extends Logging with ExitSignalListener {

  def startRead(): Unit = {
    while (!isExit) {
      val line = readLine
      log.debug("Read line: {}", line)
      commandParser(line).map(commandManager.executeWithPipe) match {
        case Success(value) ⇒
          println(value)
        case Failure(ex) ⇒
          println(ex.getMessage)
      }
    }
  }

}
