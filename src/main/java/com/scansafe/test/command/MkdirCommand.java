/**
 * com.scansafe.test.command.MkdirCommand.java
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
public class MkdirCommand extends AbstractCommand {

	@Override
	protected File executeInternal(PrintWriter context, String[] params, File current) {

		if (params.length > 1) {
			final String newPath = transformPath(params[1], current.getAbsolutePath());
			final File newLocation = new File(newPath);

			if (!newLocation.exists() ||
					!newLocation.isDirectory()) {
				newLocation.mkdirs();
				return current;
			}
		} else {
			throw new IllegalArgumentException("Not enough paramaters to perform cd command");
		}

		throw new IllegalArgumentException("Cannot create folder " + params[1] + ". It might already exist");
	}
}
