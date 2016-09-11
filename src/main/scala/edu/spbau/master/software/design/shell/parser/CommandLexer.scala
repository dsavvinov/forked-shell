package edu.spbau.master.software.design.shell.parser

import edu.spbau.master.software.design.shell.utils.Logging
import RegexpUtil._

import scala.language.postfixOps
import scala.util.Try
import scala.util.{Failure => TryFailure, Success => TrySuccess}
import scala.util.parsing.combinator.RegexParsers

/**
  * @author Baidin Dima
  */
object CommandLexer extends RegexParsers with Logging {
  private def singleQuotedTerm: Parser[SingleQuotBlockTerm] = "\'" ~> blockRegexp <~ "\'" ^^ {
    case block ⇒ SingleQuotBlockTerm(block)
  }

  private def doubleQuotedTerm: Parser[DoubleQuotBlockTerm] = "\"" ~> blockRegexp <~ "\"" ^^ {
    case block ⇒ DoubleQuotBlockTerm(block)
  }

  private def nonQuotedTerm: Parser[NonQuotBlockTerm] = wordRegexp ^^ {
    case word ⇒ NonQuotBlockTerm(word)
  }

  private def term: Parser[Term] = singleQuotedTerm | doubleQuotedTerm | nonQuotedTerm

  private def assigning: Parser[AssigningTerm] = nonQuotedTerm ~ "=" ~ term ^^ {
    case name ~ "=" ~ value ⇒ AssigningTerm(name, value)
  }

  private def command: Parser[CommandTerm] = nonQuotedTerm ~ rep(term) ^^ {
    case command ~ termList ⇒ CommandTerm(command, termList)
  }

  private def expression: Parser[Expression] = assigning | command

  private def expressions: Parser[Seq[Expression]] = expression ~ rep(pipeRegexp ~ expression) ^^ {
    case command ~ commandList ⇒ command +: commandList.map { case (pipe ~ commandTerm) ⇒
      commandTerm
    }
  }

  private[parser] def apply(input: String): Try[Seq[Expression]] = {
    log.debug("Start of processing")

    parseAll(expressions, input) match {
      case Success(result, _) ⇒
        log.debug("End of processing. Result is {}", result)
        TrySuccess(result)
      case failure: NoSuccess ⇒
        val ex = ParserException(input, failure.msg)
        log error ex.cause
        TryFailure(ex)
    }
  }
}

