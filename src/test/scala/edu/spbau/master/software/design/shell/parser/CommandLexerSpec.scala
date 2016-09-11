package edu.spbau.master.software.design.shell.parser

import edu.spbau.master.software.design.shell.parser.{CommandTerm => CT, DoubleQuotBlockTerm => DT, NonQuotBlockTerm => NT, SingleQuotBlockTerm => ST, Expression ⇒ E, AssigningTerm ⇒ AT}
import org.scalatest.FlatSpec
import org.junit.runner.RunWith
import TestUtils._
import org.scalatest.junit.JUnitRunner

import scala.util.{Failure, Success}

/**
  * @author Baidin Dima
  */
@RunWith(classOf[JUnitRunner])
class CommandLexerSpec extends FlatSpec {

  it should "parse single command" in {
    checkParsing(
      CT(NT("cd"), Seq.empty)
    )("cd")
  }

  it should "parse single command with non-quoted param" in {
    checkParsing(
      CT(NT("cd"), Seq(NT("/var/log")))
    )("cd /var/log")
  }

  it should "parse single command with single-quoted param" in {
    checkParsing(
      CT(NT("cd"), Seq(ST("/var/log")))
    )("cd \'/var/log\'")
  }

  it should "parse single command with double-quoted param" in {
    checkParsing(
      CT(NT("cd"), Seq(DT("/var/log")))
    )("cd \"/var/log\"")
  }

  it should "parse single command with different params" in {
    checkParsing(
      CT(NT("cd"), Seq(DT("/var/log"), NT("-f"), ST("hard")))
    )("cd \"/var/log\" -f 'hard'")
  }

  it should "parse several commands with different params" in {
    checkParsing(
      CT(NT("cd"), Seq(DT("/var/log"), NT("-f"), ST("hard"))),
      CT(NT("echo"), Seq(NT("$xy"), ST("oops | hard"))),
      CT(NT("man"), Seq(DT("$$echo asd")))
    )("cd \"/var/log\" -f 'hard' | echo $xy 'oops | hard' | man \"$$echo asd\"")
  }

  it should "parse assigning" in {
    checkParsing(
      AT(NT("cd"), DT("/var/log"))
    )("cd=\"/var/log\"")
  }

  it should "parse assigning with pipes" in {
    checkParsing(
      AT(NT("cd"), DT("/var/log")),
      CT(NT("echo"), Seq(NT("$xy"), ST("oops | hard")))
    )("cd=\"/var/log\" | echo $xy 'oops | hard' ")
  }

  private def checkParsing(expected: E*)(input: String): Unit = {
    CommandLexer(input) match {
      case Success(parseResult) ⇒
        checkCommandListEquals(expected: _*)(parseResult: _*)
      case Failure(ex) ⇒ fail(s"Failed parsing!", ex)
    }
  }

}
