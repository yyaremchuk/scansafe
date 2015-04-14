/**
 * com.scansafe.test.command.LsCommand.java
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
public class LsCommand extends AbstractCommand {

	@Override
	protected File executeInternal(PrintWriter context, String[] params, File current) {
		final File[] files = current.listFiles();
		
		if (files != null) {
			
			for (File file: files) {
				context.printf("  %1$-40s   %2$s", file.getName(), !file.isFile() ? "folder" : file.length());
				context.println();
			}
		}

		return current;
	}
}
