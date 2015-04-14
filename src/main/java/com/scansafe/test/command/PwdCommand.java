/**
 * com.scansafe.test.command.PwdCommand.java
 * Aug 20, 2012
 * scansafe
 *
 */
package com.scansafe.test.command;

import java.io.File;
import java.io.PrintWriter;

/**
 * @author yyaremchuk
 *
 */
public class PwdCommand extends AbstractCommand {

	@Override
	protected File executeInternal(PrintWriter context, String[] params, File current) {
		context.println(current.getAbsolutePath());
		return current;
	}
}
