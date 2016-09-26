package edu.spbau.master.software.design.shell.command.impl

import java.nio.file.{Files, Paths}

import edu.spbau.master.software.design.shell.command.Command
import edu.spbau.master.software.design.shell.command.Command.ReturnType
import edu.spbau.master.software.design.shell.command.impl.GrepCommand.GrepOptions
import edu.spbau.master.software.design.shell.model.CommandModel

import scala.io.Source
import scala.util.Try
import scala.util.matching.Regex

/**
  * @author Baidin Dima
  */
class GrepCommand extends Command {

  import GrepCommand._

  override def execute(command: CommandModel): ReturnType = {
    require(command.args.size >= 2, "Grep must have more than 2 param")

    val optionsArgs = command.args.dropRight(2).map(_.value)
    val patternAndFileName = command.args.takeRight(2).map(_.value)

    parser.parse(optionsArgs, GrepOptions()) match {
      case Some(options) ⇒
        val Seq(pattern, fileName) = patternAndFileName

        val regex: Regex = (new OptionTransformer(options)
          with OnlyWholeWordsTransform with CaseSensitiveTransformer).transform(pattern).r

        val textTransformer =
          new OptionTransformer(options) with CaseSensitiveTransformer

        val lines: Iterator[String] = if (
          Try(
            Paths.get(command.args.last.value)
          )
            .toOption
            .map(Files.exists(_))
            .exists(p ⇒ p)
        ) {
          Source.fromFile(fileName).getLines()
        } else {
          command.args.last.value.split("\n").toIterator
        }

        val matchingLinesIterator = lines.withFilter { line ⇒
          regex.findFirstIn(textTransformer.transform(line)).isDefined
        }

        options.maxOccurrence.map(max ⇒ matchingLinesIterator.take(max))
          .getOrElse(matchingLinesIterator.toSeq).mkString("\n")
      case None ⇒
        ""
    }

  }
}

private class OptionTransformer(val options: GrepOptions) {
  def transform(pattern: String): String = pattern
}

private trait CaseSensitiveTransformer extends OptionTransformer {
  override def transform(pattern: String): String = options.caseSensitive match {
    case true ⇒ super.transform(pattern)
    case false ⇒ super.transform(pattern.toLowerCase)
  }
}

private trait OnlyWholeWordsTransform extends OptionTransformer {
  override def transform(pattern: String): String = options.onlyWholeWords match {
    case true ⇒ super.transform(s"(^|\\s)$pattern($$|\\s)")
    case false ⇒ super.transform(pattern)
  }
}

object GrepCommand {

  case class GrepOptions(caseSensitive: Boolean = true,
                         onlyWholeWords: Boolean = false,
                         maxOccurrence: Option[Int] = None)

  val parser = new scopt.OptionParser[GrepOptions]("grep") {
    head("grep", "1.x")

    opt[Unit]('i', "ignore-case").action((_, c) ⇒
      c.copy(caseSensitive = false))
      .text("Ignore  case  distinctions  in  both  the  PATTERN and the input files.")

    opt[Unit]('w', "whole-words").action((_, c) ⇒
      c.copy(onlyWholeWords = true))
      .text(" Select only those lines containing matches that form whole words. " +
        "The test is that the matching substring must either be at the beginning of the line, " +
        "or preceded by a non-word constituent character. Similarly," +
        " it must be either at the end of the line or followed by a non-word " +
        "constituent character. Word-constituent characters are letters, " +
        "digits, and the underscore.")

    opt[Int]('n', "line-number").action((x, c) ⇒
      c.copy(maxOccurrence = Some(x)))
      .validate(x ⇒
        if (x > 0) success
        else failure("line number must be >0"))
      .text("Max count of printing lines")
  }
}