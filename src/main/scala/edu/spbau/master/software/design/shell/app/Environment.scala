package edu.spbau.master.software.design.shell.app

import edu.spbau.master.software.design.shell.model.{VariableName, VariableValue}

/**
  * Stores variables values
  *
  * @author Baidin Dima
  */
class Environment(variables: Map[VariableName, VariableValue]) {

  private var privateVariables: Map[VariableName, VariableValue] = variables

  def updateVariableValue(variableName: VariableName,
                          variableValue: VariableValue): Unit =
    privateVariables += (variableName → variableValue)

  def getVariableValue(variableName: VariableName): VariableValue =
    privateVariables.getOrElse(variableName, VariableValue.empty)

}
