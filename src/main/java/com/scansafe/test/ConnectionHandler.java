/**
 * 
 */
package com.scansafe.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.scansafe.test.command.Command;

/**
 * @author Yaremchuk_Y
 *
 */
public class ConnectionHandler implements Runnable {
	private static final String QUIT_COMMAND = "quit";

	private Socket socket;
	private int id;
	private File current;
	private CommandFactory commandFactory;

	public ConnectionHandler(int id, Socket socket, File current, CommandFactory commandFactory) {
		this.id = id;
		this.socket = socket;
		this.current = current;
		this.commandFactory = commandFactory;
	}

	private void print(String message) {
		System.out.println(id + ": command = " + message);
	}

	@Override
	public void run() {
		boolean close = false;
		final String address = socket.getRemoteSocketAddress().toString();
		PrintWriter out = null;
		BufferedReader reader = null;

		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			//gets input stream object    
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String userInput = reader.readLine();

			while ((userInput != null) && !close) {
			    print("\"" + userInput + "\"");

			    if (QUIT_COMMAND.equals(userInput)) {
			    	close = true;
			    	print("Should exit after this!");
			    	out.println("Closing connection...");
			    } else {
				    final String[] params = userInput.split(" ");

				    try {
					    final Command command = commandFactory.getCommand(params[0]);
					    current = command.execute(out, params, current);
					    out.println("Done");
				    } catch (Exception e) {
				    	System.err.print(e);
				    	out.println("Error: " + e.getMessage());
					}

				    // reading new command
				    userInput = reader.readLine();
			    }
			}
		} catch (Exception e) {
			print("Exception happened: " + e);
			Thread.currentThread().interrupt();
		} finally {
			print("Closeing connection for " + address);

			if (out != null) {
				
				try {
					out.close();
				} catch (Exception e) {
					System.err.println("Cannot close writer = " + e);
				}
			}
			
			if (reader != null) {
				
				try {
					reader.close();
				} catch (Exception e) {
					System.err.println("Cannot close reader = " + e);
				}
			}
			
			if (socket != null) {

				try {
					socket.close();
				} catch (Exception e) {
					System.err.println("Cannot close socket = " + e);
				}
			}
		}
	}
}
