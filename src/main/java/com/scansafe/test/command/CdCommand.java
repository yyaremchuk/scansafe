/**
 * com.scansafe.test.command.CdCommand.java
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
public class CdCommand extends AbstractCommand {

	@Override
	protected File executeInternal(PrintWriter context, String[] params, File current) {

		if (params.length > 1) {
			final File tmp = new File(params[1]);
			System.out.println("new = " + tmp.getAbsolutePath());

			final String newPath = transformPath(params[1], current.getAbsolutePath());
			final File newLocation = new File(newPath);

			if ((newLocation != null) &&
					newLocation.exists() &&
					newLocation.isDirectory() ) {
				return newLocation;
			}
		} else {
			throw new IllegalArgumentException("Not enough paramaters to perform cd command");
		}

		throw new IllegalArgumentException("Cannot change location from " + current.getAbsolutePath() + 
				" to " + params[1]);
	}
}
