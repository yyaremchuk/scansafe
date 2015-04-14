/**
 * 
 */
package com.scansafe.test;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Yaremchuk_Y
 *
 */
public class ScansafeServer {
	private ExecutorService executor;
	private String userDir;
	private int port;

	/**
	 * Create and configure the server
	 * @param port
	 * @throws Exception
	 */
	public ScansafeServer(int port, int threads) {
		this.port = port;
		System.out.println("Initialising server...");
		userDir = System.getProperty("user.dir");
		System.out.println("user.dir = " + userDir);
		executor = Executors.newFixedThreadPool(threads);
	}

	public void run() {
		boolean stop = false;
		ServerSocket server = null;
		int i = 0;

		System.out.println("Starting server on port " + port);

		try {
			//Create object of Server Socket 
			server = new ServerSocket(port);

			while (!stop) {
				final Socket socket = server.accept();
				System.out.println("Open new connection for " + socket.getInetAddress());
				executor.submit(new ConnectionHandler(i++, socket, new File(userDir), CommandFactory.getInstanse()));
			}
		} catch(Exception e) {
			System.out.print(e);
		} finally {
			System.out.println("Stopping the server");

			if (server != null) {

				try {
					server.close();
				} catch (Exception e) {
					System.err.println("Cannot close server socket: " + e);
				}
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length < 2) {
			System.out.println("Configuration parameters are missing. To start the server run the following command:\n" +
					"com.scansafe.test.ScansafeServer <port> <max_number_of_threads>");
			return;
		}

		final int port = Integer.valueOf(args[0]);
		final int threads = Integer.valueOf(args[1]);

		if (threads < 1) {
			System.out.println("Please specifiy resonable amount of simultaniously running threads. " + 
					"It's recommended to keep it equal to number of cores in system's CPU(s)");
			return;
		}

		final ScansafeServer server = new ScansafeServer(port, threads);
		server.run();
	}
}
