package edu.spbau.master.software.design.shell.app.signals

/**
  * @author Baidin Dima
  */
trait ExitSignalListener {
  def isExit: Boolean
}


class ExitSignalUpdater {

  var isExit: Boolean = false

}

