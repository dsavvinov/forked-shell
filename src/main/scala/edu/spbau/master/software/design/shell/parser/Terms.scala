package edu.spbau.master.software.design.shell.parser

/**
  * @author Baidin Dima
  */

private[parser] sealed trait Term {
  def value: String
}

private[parser] case class SingleQuotBlockTerm(value: String) extends Term

private[parser] case class DoubleQuotBlockTerm(value: String) extends Term

private[parser] case class NonQuotBlockTerm(value: String) extends Term

private[parser] sealed trait Expression

private[parser] case class AssigningTerm(name: NonQuotBlockTerm, value: Term) extends Expression

private[parser] case class CommandTerm(name: NonQuotBlockTerm, args: Seq[Term]) extends Expression

