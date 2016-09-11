package edu.spbau.master.software.design.shell.parser

import edu.spbau.master.software.design.shell.model.{CommandModel, CommandArg, CommandName}
import edu.spbau.master.software.design.shell.utils.Logging

import scala.util.Try

/**
  * Parse string to commands
  *
  * @author Baidin Dima
  */
class CommandParser(variableResolver: VariableResolver) extends Logging {
  def apply(input: String): Try[Seq[CommandModel]] = {
    log.debug("Start of parsing {}", input)
    val result = CommandLexer(input).map(variableResolver(_).map {
      case CommandTerm(NonQuotBlockTerm(name), args) ⇒ CommandModel(
        CommandName(name),
        args.map(arg ⇒ CommandArg(arg.value)): _*)
      case AssigningTerm(NonQuotBlockTerm(name), value) ⇒
        CommandModel(
          CommandName("="),
          CommandArg(name),
          CommandArg(value.value)
        )
    })
    log.debug("End of parsing with result {}", result)
    result
  }
}
