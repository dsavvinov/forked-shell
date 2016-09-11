package edu.spbau.master.software.design.shell.utils

import org.slf4j.LoggerFactory

/**
  * @author Baidin Dima
  */
trait Logging {

  protected val log = LoggerFactory.getLogger(loggerClass)

  /**
    * Specifies class whose name will be used as logger name
    */
  protected def loggerClass: Class[_] = this.getClass

}
