/**
* com.scansafe.test.command.HelpCommand.java
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
public class HelpCommand extends AbstractCommand {

	@Override
	protected File executeInternal(PrintWriter context, String[] params, File current) {
		context.println("The following are valid commands for ScanSafe test server:");
		context.println("   help         - see this text");
		context.println("   cd <path>    - change current folder");
		context.println("   ls [<path>]  - show content of current folder");
		context.println("   pwd          - show the path for current folder");
		context.println("   mkdir <path> - create new folder");
		return current;
	}
}
