package edu.spbau.master.software.design.shell.parser

import edu.spbau.master.software.design.shell.app.Environment
import edu.spbau.master.software.design.shell.model._
import edu.spbau.master.software.design.shell.parser.RegexpUtil._
import edu.spbau.master.software.design.shell.utils.Logging

import scala.language.postfixOps

/**
  * @author Baidin Dima
  */
class VariableResolver(environment: Environment) extends Logging {
  private def getVariableMatches(source: String): Seq[Variable] = {
    (for {
      regexMatch ← variableRegex findAllIn source
    } yield {
      val variableName = VariableName(regexMatch.toString.substring(1))
      val variableValue = environment.getVariableValue(variableName)

      log.debug("Variable {} has value {}", variableName, variableValue)

      Variable(variableName, variableValue)
    }).toSeq
  }

  private def withVariablesSubstitution(source: String): String = {
    log.debug("Start of substitution of variables in {}", source)
    val result = (source /: getVariableMatches(source)) { case (s, variable) ⇒
      s.replace("$" + variable.name.name, variable.value value)
    }
    log.debug("End of substitution of variables with result {}", result)
    result
  }

  private def processTerm(term: Term): Term = term match {
    case term: DoubleQuotBlockTerm ⇒ term.copy(withVariablesSubstitution(term value))
    case term: SingleQuotBlockTerm ⇒ term
    case term: NonQuotBlockTerm ⇒ term.copy(withVariablesSubstitution(term value))
  }

  private[parser] def apply(commandTerms: Seq[Expression]): Seq[Expression] = {
    val result = commandTerms.map { case CommandTerm(name, args) ⇒
      val commandArgs = args.map(processTerm)
      val commandName = name.copy(withVariablesSubstitution(name value))
      CommandTerm(commandName, commandArgs)
    case AssigningTerm(name, value) ⇒
      AssigningTerm(NonQuotBlockTerm(withVariablesSubstitution(name value)),
        processTerm(value)
      )
    }
    log.debug("Total end of substitution of variables with result {}", result)
    result
  }
}
