/**
 * com.scansafe.test.CommandFactory.java
 * Aug 20, 2012
 * scansafe
 *
 */
package com.scansafe.test;

import com.scansafe.test.command.CdCommand;
import com.scansafe.test.command.Command;
import com.scansafe.test.command.HelpCommand;
import com.scansafe.test.command.LsCommand;
import com.scansafe.test.command.MkdirCommand;
import com.scansafe.test.command.PwdCommand;

/**
 * @author yyaremchuk
 *
 */
public class CommandFactory {
	private static final String HELP_COMMAND = "help";
	private static final String CD_COMMAND = "cd";
	private static final String LS_COMMAND = "ls";
	private static final String PWD_COMMAND = "pwd";
	private static final String MKDIR_COMMAND = "mkdir";

	private CommandFactory() {
	}

	private static final class CommandFactoryHolder {
		public static final CommandFactory FACTORY = new CommandFactory(); 
	}

	public static final CommandFactory getInstanse() {
		return CommandFactoryHolder.FACTORY;
	}

	public Command getCommand(String name) {
		Command command = null;
		
		if (HELP_COMMAND.equals(name)) {
			command = new HelpCommand();
		} else if (CD_COMMAND.equals(name)) {
			command = new CdCommand();
		} else if (LS_COMMAND.equals(name)) {
			command = new LsCommand();
		} else if (PWD_COMMAND.equals(name)) {
			command = new PwdCommand();
		} else if (MKDIR_COMMAND.equals(name)) {
			command = new MkdirCommand();
		}

		if (command == null) {
			throw new IllegalArgumentException("Command '" + name + "' is not supported");
		}

		return command;
	}
}
