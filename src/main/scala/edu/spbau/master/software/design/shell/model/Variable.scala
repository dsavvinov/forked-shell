package edu.spbau.master.software.design.shell.model

import scala.language.implicitConversions

/**
  * @author Baidin Dima
  */
case class Variable(name: VariableName,
                    value: VariableValue) {

  require(name != null, "Variable name is null!")
  require(value != null, "Variable value is null!")
}

case class VariableName(name: String) extends AnyVal

case class VariableValue(value: String) extends AnyVal

object VariableValue {

  def empty = VariableValue("")

}