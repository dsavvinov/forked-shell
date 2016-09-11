package edu.spbau.master.software.design.shell.command.impl

import edu.spbau.master.software.design.shell.app.Environment
import edu.spbau.master.software.design.shell.model.{CommandArg, CommandModel, CommandName, VariableName}
import org.junit.runner.RunWith
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.junit.JUnitRunner

/**
  * @author Baidin Dima
  */
@RunWith(classOf[JUnitRunner])
class AssignmentCommandSpec extends FlatSpec with Matchers {

  it should "Add variable value to environment" in {
    val environment = new Environment(Map.empty)
    val assignmentCommand = new AssignmentCommand(environment)
    assignmentCommand.execute(
      CommandModel(CommandName("="), CommandArg("x"), CommandArg("100"))
    )

    environment.getVariableValue(VariableName("x")).value shouldEqual "100"
  }

  it should "Reset variable value in environment" in {
    val environment = new Environment(Map.empty)
    val assignmentCommand = new AssignmentCommand(environment)
    assignmentCommand.execute(
      CommandModel(CommandName("="), CommandArg("x"), CommandArg("100"))
    )
    assignmentCommand.execute(
      CommandModel(CommandName("="), CommandArg("x"), CommandArg("200"))
    )

    environment.getVariableValue(VariableName("x")).value shouldEqual "200"
  }

}
