/**
 * 
 */
package com.scansafe.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.scansafe.test.command.CdCommand;
import com.scansafe.test.command.HelpCommand;
import com.scansafe.test.command.LsCommand;
import com.scansafe.test.command.MkdirCommand;
import com.scansafe.test.command.PwdCommand;

/**
 * @author Yaremchuk_Y
 *
 */
public class CommandFactoryTest {

	@Test
	public void testGetInstance() {
		final CommandFactory factory1 = CommandFactory.getInstanse();

		assertTrue(factory1 != null);
		assertTrue(factory1 == CommandFactory.getInstanse());
	}

	@Test
	public void testGetCommand() {
		final CommandFactory factory = CommandFactory.getInstanse();

		assertTrue(HelpCommand.class == factory.getCommand("help").getClass());
		assertTrue(CdCommand.class == factory.getCommand("cd").getClass());
		assertTrue(LsCommand.class == factory.getCommand("ls").getClass());
		assertTrue(PwdCommand.class == factory.getCommand("pwd").getClass());
		assertTrue(MkdirCommand.class == factory.getCommand("mkdir").getClass());

		try {
			factory.getCommand(null);
			fail();
		} catch (Exception e) {
			assertTrue(IllegalArgumentException.class == e.getClass());
		}

		try {
			factory.getCommand("meaningless text");
			fail();
		} catch (Exception e) {
			assertTrue(IllegalArgumentException.class == e.getClass());
		}
	}
}
