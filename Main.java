import java.io.*;
import java.util.*;
import java.net.*;

public class Main {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(55555);
		System.out.println("Server has been started, waiting for client connections...");
		int count = 0;
		Socket conn = ss.accept();
		count += 1;
		System.out.println(String.format("Client has connected ~> [%d]", count));
		client_input(conn);
	}

	public static void server_input(Socket conn, PrintWriter output, String input) throws IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("\rServer: ");
		String server = scanner.nextLine();
		output.println(String.format("From the server ~> [%s]", server));
	}

	public static void client_input(Socket conn) throws IOException {
		InputStreamReader in = new InputStreamReader(conn.getInputStream());
		BufferedReader reader = new BufferedReader(in);
		PrintWriter output = new PrintWriter(conn.getOutputStream());
		output.println("Hello, begin chatting with the server!");
		while(true) {
			output.print("\rClient: ");
			output.flush();
			String input = reader.readLine();
			if (input == null) {
				System.out.println("Client has disconnected");
				System.exit(0);
			}
			System.out.print(String.format("From client ~> %s\n", input));
			System.out.print("Server: ");
			server_input(conn, output, input);
		}
	}
}
