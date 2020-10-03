package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class Server {
	
	private static DB db;
	
	public Server() {
		this.db = new DB();
		System.out.println("alo");
	}
	

	public void start  () throws UnknownHostException, IOException {
		System.out.println("Servidor iniciado pelo IP 192.168.2.2 e porta 9999");
		ServerSocket server = new ServerSocket(9999, 50, InetAddress.getByName("192.168.2.11"));

		Enumeration en = NetworkInterface.getNetworkInterfaces();
		while (en.hasMoreElements()) {
			NetworkInterface ni = (NetworkInterface) en.nextElement();
			Enumeration ee = ni.getInetAddresses();
			while (ee.hasMoreElements()) {
				InetAddress ia = (InetAddress) ee.nextElement();
			}
		}

		while (true) {
			try {
				Socket cliente = server.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
				PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream())),
						true);
				System.out.println("Cliente " + cliente.getInetAddress() + " conectado.");

				int opt = Integer.parseInt(in.readLine());
				String name;
				String author;
				int year;
				switch (opt) {
				case 1:
					int id = Integer.parseInt(in.readLine());
					name = in.readLine();
					insertBook(id,name);
					break;
				case 2:
					name = in.readLine();
					searchBookByName(name);
					break;
				case 3:
					year = Integer.parseInt(in.readLine());
					String edition = in.readLine();
					searchBookByYear(year,edition);
					break;
				case 4:
					author = in.readLine();
					searchBookByAuthor(author);
					break;
				case 5:
					name = in.readLine();
					removeBook(name);
					break;
				case 6:
					String oldName = in.readLine();
					String newName = in.readLine();
					author = in.readLine();
					year = Integer.parseInt(in.readLine());	
					edition = in.readLine();
					updateBook(oldName,newName,author,year,edition);
					break;
				}
				cliente.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	private static void insertBook(int id, String name) {
		db.insert_book(id, name);
	}

	private static void searchBookByName(String name) {
		db.find_book(name);
	}

	private static void searchBookByAuthor(String author) {
		db.list_books_by_author(author);
	}

	private static void searchBookByYear(int year, String edition) {
		db.list_books_per_year_edition(year, edition);
	}

	private static void removeBook(String name) {
		db.delete_book(name);
	}

	private static void updateBook(String oldName, String newName, String author, int year, String edition) {
		db.update_book(oldName, newName, year, edition);
	}
}
