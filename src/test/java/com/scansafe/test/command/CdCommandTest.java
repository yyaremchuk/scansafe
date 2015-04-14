/**
 * com.scansafe.test.command.CdCommandTest.java
 * Aug 20, 2012
 * scansafe
 *
 */
package com.scansafe.test.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;

import com.scansafe.test.mock.MockFile;

/**
 * @author yyaremchuk
 *
 */
public class CdCommandTest {

	@Test
	public void testCdCommand() {
		final CdCommand command = new CdCommand();
		
		final MockFile file = new MockFile("/home/user/temp");
		file.setDirectory(true);
		file.setExist(true);
		
		final String[] params = {"cd", "../.."};
		File newLocation = command.execute(null, params, file);
		assertEquals("/home", newLocation.getAbsolutePath());

		try {
			file.setExist(false);
			newLocation = command.execute(null, params, file);
			fail();
		} catch (Exception e) {}

		try {
			file.setExist(true);
			file.setDirectory(false);
			newLocation = command.execute(null, params, file);
			fail();
		} catch (Exception e) {}

		try {
			file.setExist(true);
			file.setDirectory(true);
			newLocation = command.execute(null, new String[] {}, file);
			fail();
		} catch (Exception e) {}
	}
}
