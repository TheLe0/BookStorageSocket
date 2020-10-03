package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Client.connect();
	}

	public static void connect() throws UnknownHostException, IOException {

		Socket client = new Socket("192.168.2.12", 3321);
		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);

		try {
			//Criar livro
			out.println(1);
			out.println(1001);
			out.println("Boku no piko");
			
		/*	//Consultar livro por nome
			out.println(2);
			out.println("Dark Tales");
			
			//Consultar livro por ano
			out.println(3);
			out.println(2013);
			out.println("1");
			
			//Consultar livro por autor
			out.println(4);
			out.println("Roberto Nogueira");
			
			//Remover livro
			out.println(5);
			out.println("The Outsider");
			
			//Alterar livro
			out.println(6);
			out.println("Solvansku"); 
			out.println("Slovansky");
			out.println("Dekline");
			out.println(2013);
			out.println("2"); */
			
			//String str = in.readLine();
			client.close();
		} catch (Exception e) {
			System.out.println(e);

		}
	}
}
