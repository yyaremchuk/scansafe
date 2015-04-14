/**
 * com.scansafe.test.Command.java
 * Aug 19, 2012
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
public interface Command {
	File execute(PrintWriter context, String[] params, File current);
}
