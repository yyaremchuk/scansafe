/**
 * com.scansafe.test.MockFile.java
 * Aug 20, 2012
 * scansafe
 *
 */
package com.scansafe.test.mock;

import java.io.File;

/**
 * @author yyaremchuk
 *
 */
public class MockFile extends File {
	private boolean directory;
	private boolean exist;

	public MockFile(String pathname) {
		super(pathname);
	}

	public boolean isDirectory() {
		return directory;
	}

	public void setDirectory(boolean directory) {
		this.directory = directory;
	}

	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}
}
