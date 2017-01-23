package Server;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {

	private static final int PORT = 9001;

	public static void main(String[] args) throws Exception {
		System.out.println("Chat starting....");
		final Socket socket = new Socket("localhost", PORT);
		final PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

	

	Thread t2 = new Thread() {
		@Override
		public void run() {
			try {
				while (true) {
					out.println(getMessage());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	};

	 Thread t1 = new Thread() {

		@Override
		public void run() {
			try {

				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

				while (true) {
					String line = in.readLine();
					if (line.startsWith("SUBMITNAME")) {
						out.println(getNamez());
					} else if (line.startsWith("NAMEACCEPTED")) {
						System.out.println("Name Accepted");
					} else if (line.startsWith("MESSAGE")) {
						System.out.println(line);
					}

				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	};
	t1.start();
	t2.start();
	}
	private static String getNamez() {
		Scanner input = new Scanner(System.in);
		System.out.print("Username:");
		return input.nextLine();
	}

	private static String getMessage() {
		Scanner input = new Scanner(System.in);
		return input.nextLine();
	}
}
