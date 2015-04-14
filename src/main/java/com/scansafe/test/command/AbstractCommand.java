/**
 * com.scansafe.test.command.AbstractCommand.java
 * Aug 20, 2012
 * scansafe
 *
 */
package com.scansafe.test.command;

import java.io.File;
import java.io.PrintWriter;
import java.util.regex.Pattern;

/**
 * @author yyaremchuk
 *
 */
public abstract class AbstractCommand implements Command {
	private static final Pattern PATH_REGEXP = Pattern.compile("\\\\");

	@Override
	public File execute(PrintWriter context, String[] params, File current) {
		return executeInternal(context, params, current);
	}

	/**
	 * Command specific implementation
	 * @param context
	 * @param params
	 * @param current
	 * @return
	 */
	protected abstract File executeInternal(PrintWriter context, String[] params, File current);

	/**
	 * Transform current absolute path into new absolute path 
	 * @param newPath
	 * @param currentPath
	 * @return
	 */
	public String transformPath(String newPath, String currentPath) {
		final String from = PATH_REGEXP.matcher(currentPath).replaceAll("/");
		final String to = PATH_REGEXP.matcher(newPath).replaceAll("/");

		if ((to != null) &&
				to.startsWith("..")) {
			final String[] currentPathParts = from.split("/");
			String[] newPathParts = to.split("/");

			if (to.equals("..")) {
				newPathParts = new String[1];
				newPathParts[0] = "..";
			}

			if ((newPathParts != null) &&
					(currentPathParts != null)) {
				int endIndex = currentPathParts.length - 1;
				int startIndex = 0;

				for (String part: newPathParts) {

					if ("..".equals(part)) {
						endIndex --;
						startIndex++;
					} else {
						break;
					}
				}

				if (endIndex < 0) {
					throw new IllegalArgumentException("Cannot change current folder from '" + currentPath + 
							"' to '" + newPath + "'");
				}

				final StringBuilder builder = new StringBuilder();

				for (int i = 0; i <= endIndex; i++) {
					builder.append(currentPathParts[i]).append("/");
				}

				for (int i = startIndex; i < newPathParts.length; i++) {
					builder.append(newPathParts[i]).append("/");
				}
				
				return builder.toString();
			} else {
				throw new IllegalArgumentException("Cannot change current folder from '" + currentPath + 
						"' to '" + newPath + "'");
			}
		} else {

			if (to.startsWith("/")) {
				return to;
			} else {
				return from + (from.endsWith("/") ? "" : "/") + to + (to.endsWith("/") ? "" : "/");
			}
		}
	}
}
