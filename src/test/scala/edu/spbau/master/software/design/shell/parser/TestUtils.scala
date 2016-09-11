package edu.spbau.master.software.design.shell.parser

import org.scalatest.FlatSpec

/**
  * @author Baidin Dima
  */
object TestUtils extends FlatSpec{

  private[parser] def checkCommandListEquals(expected: Expression*)(actual: Expression*): Unit = {
    if (expected.size != actual.size) {
      fail(s"Expected size doesn't match actual size! expected: $expected, actual: $actual")
    }

    expected.zip(actual).foreach { case (exp, act) ⇒ if (exp != act) {
      fail(s"Expected doesn't match actual! expected: $exp; actual: $act")
    }
    }
  }

}
