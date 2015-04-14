/**
 * com.scansafe.test.command.HelpCommandTest.java
 * Aug 20, 2012
 * scansafe
 *
 */
package com.scansafe.test.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * @author yyaremchuk
 *
 */
public class HelpCommandTest {
	
	// TODO: check Windows style & mixed style pathes

	@Test
	public void testChangeFolder() {
		final HelpCommand command = new HelpCommand();

		assertEquals("/users/home/", command.transformPath("..", "/users/home/project"));

		assertEquals("/users/home/project/tmp/", command.transformPath("tmp", "/users/home/project"));

		// /users/home/project -> /opt/tmp/others = /opt/tmp/others/
		assertEquals("/users/tmp/others/", command.transformPath("../../tmp/others", "/users/home/project"));
		assertEquals("/users/tmp/others/", command.transformPath("../../tmp/others/", "/users/home/project"));
		assertEquals("/users/tmp/others/", command.transformPath("../../tmp/others", "/users/home/project/"));
		assertEquals("/users/tmp/others/", command.transformPath("../../tmp/others/", "/users/home/project/"));

		// /users/home/project -> ../.. = /users/
		assertEquals("/users/", command.transformPath("../..", "/users/home/project"));
		assertEquals("/users/", command.transformPath("../../", "/users/home/project"));
		assertEquals("/users/", command.transformPath("../..", "/users/home/project/"));
		assertEquals("/users/", command.transformPath("../../", "/users/home/project/"));
		
		// /users/home/project -> ../../temp/others = /users/tmp/others/
		assertEquals("/users/tmp/others/", command.transformPath("../../tmp/others/", "/users/home/project/"));
		
		// /users/home/project -> ../../../temp/others = /users/tmp/others/
		assertEquals("/tmp/others/", command.transformPath("../../../tmp/others/", "/users/home/project/"));

		try {
			// /users/home/project -> ../../../../temp/others = /users/tmp/others/
			command.transformPath("../../../../tmp/others/", "/users/home/project/");
			fail();
		} catch (Exception e) {}
	}
}
