package edu.spbau.master.software.design.shell.parser

import TestUtils._
import edu.spbau.master.software.design.shell.app.Environment
import edu.spbau.master.software.design.shell.model.{Variable => V, VariableName => VN, VariableValue => VV}
import edu.spbau.master.software.design.shell.parser.{CommandTerm => CT, DoubleQuotBlockTerm => DT, NonQuotBlockTerm => NT, SingleQuotBlockTerm => ST}
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

import scala.language.postfixOps


/**
  * @author Baidin Dima
  */
@RunWith(classOf[JUnitRunner])
class VariableResolverSpec extends FlatSpec {

  it should "Substitute empty values for unknown vars" in {
    checkCommandListEquals(
      getVariableResolver()(
        Seq(
          CT(NT("ec$x"), Seq(DT("loul $14  $8 $8  $")))
        )
      ): _*)(CT(NT("ec"), Seq(DT("loul      $"))))
  }

  it should "Substitute no values for single quoted blocks" in {
    checkCommandListEquals(
      getVariableResolver()(
        Seq(
          CT(NT("ec$x"), Seq(ST("loul $14  $8 $8  $")))
        )
      ): _*)(CT(NT("ec"), Seq(ST("loul $14  $8 $8  $"))))
  }

  it should "Substitute values " in {
    checkCommandListEquals(
      getVariableResolver(
        V(VN("x"), VV("ho")),
        V(VN("8"), VV("haha")),
        V(VN("14"), VV("hoho"))
      )(
        Seq(
          CT(NT("ec$x"), Seq(DT("loul $14  $8 $8  $")))
        )
      ): _*)(CT(NT("echo"), Seq(DT("loul hoho  haha haha  $"))))
  }


  private def getVariableResolver(variables: V*): VariableResolver = {
    new VariableResolver(
      new Environment(variables map (v ⇒ (v name, v value)) toMap)
    )
  }

}
