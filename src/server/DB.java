package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB {

	private static Connection connect() {
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1554");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return c;
	}

	public void insert_book(int code, String name) {
		try {
			Connection c = DB.connect();
			Statement stmt = c.createStatement();
			String sql = "INSERT INTO livros (codigo, titulo) VALUES (" + (code) + ",'" + name + "');";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Registro adicionado com sucesso");
	}
	
	public void delete_book(String name) {
		try {
		Connection c = DB.connect();
		Statement stmt = c.createStatement();
		String sql = "DELETE FROM livros WHERE titulo = '"+ name + "';";
		stmt.executeUpdate(sql);
		stmt.close();
		c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Registro deletado com sucesso");
	}

	public void find_book(String name) {
		try {
		Connection c = DB.connect();
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM livros WHERE titulo like '%"+ name +"%';";
		ResultSet result = stmt.executeQuery(sql);
		while (result.next()) {
			 int code = result.getInt("codigo");
			 String titulo = result.getString("titulo");
	         System.out.println(code + ", "+ titulo);
	      }
		result.close();
		stmt.close();
		c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	
	public void list_books_by_author(String name) {
		try {
		Connection c = DB.connect();
		Statement stmt = c.createStatement();
		String sql = "SELECT  L.codigo,  L.titulo FROM livros L INNER JOIN livroautor LA on LA.codigolivro = L.codigo INNER JOIN autor A on LA.codigoautor = A.codigo WHERE A.nome like '%"+name+"%';";
		ResultSet result = stmt.executeQuery(sql);
		while (result.next()) {
			 int code = result.getInt("codigo");
			 String titulo = result.getString("titulo");
	         System.out.println(code + ", "+ titulo);
	      }
		result.close();
		stmt.close();
		c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public void list_books_per_year_edition(int year, String edition) {
		try {
		Connection c = DB.connect();
		Statement stmt = c.createStatement();
		String sql = "SELECT L.codigo,  L.titulo FROM livros L INNER JOIN edicao E ON E.codigolivro = L.codigo WHERE E.ano = " + year+ " AND E.numero like '" + edition + "';";
		ResultSet result = stmt.executeQuery(sql);
		while (result.next()) {
			 int code = result.getInt("codigo");
			 String titulo = result.getString("titulo");
	         System.out.println(code + ", "+ titulo);
	      }
		result.close();
		stmt.close();
		c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	//TODO Revisar a query de update
	public void update_book(String old_name, String new_name, int year, String edition) {
		try {
			Connection c = DB.connect();
			Statement stmt = c.createStatement();
			String sql = "UPDATE livros AS L SET titulo = '" + new_name + "' FROM edicao AS E WHERE E.codigolivro = L.codigo AND L.titulo LIKE '%" + old_name + "%' AND E.numero = '" + edition + "' AND E.ano = " + year + ";";
			System.out.println(sql);
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				 int code = result.getInt("codigo");
				 String titulo = result.getString("titulo");
		         System.out.println(code + ", "+ titulo);
		      }
			result.close();
			stmt.close();
			c.close();
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
		System.out.println("Registro atualizado");
		}
}

