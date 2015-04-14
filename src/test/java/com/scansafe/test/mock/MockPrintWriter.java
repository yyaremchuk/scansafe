/**
 * com.scansafe.test.mock.MockPrintWriter.java
 * Aug 20, 2012
 * scansafe
 *
 */
package com.scansafe.test.mock;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * @author yyaremchuk
 *
 */
public class MockPrintWriter extends PrintWriter {

	public MockPrintWriter(OutputStream out) {
		super(out);
	}

	
}
