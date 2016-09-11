package edu.spbau.master.software.design.shell.model

/**
  * @author Baidin Dima
  */
case class CommandModel(name: CommandName,
                        args: CommandArg*) {
  require(name != null, "Command name is null!")
  require(args != null, "Command args is null!")
}

case class CommandName(name: String) extends AnyVal
case class CommandArg(value: String) extends AnyVal