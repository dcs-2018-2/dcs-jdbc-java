package br.pro.ramon.dcs.jdbc.hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Program {

    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://facenac.database.windows.net;database=facenac";
    private static final String USER = "";
    private static final String PASS = "";

    public static void main(String[] args) {
        try {
            Class.forName(DRIVER);
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                    PreparedStatement stmt = conn.prepareStatement("select * from usuario");
                    ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String nome = rs.getString("nome");
                    String email = rs.getString("email");
                    String senha = rs.getString("senha");
                    byte[] foto = rs.getBytes("foto");

                    System.out.println("ID...: " + id);
                    System.out.println("NOME.: " + nome);
                    System.out.println("EMAIL: " + email);
                    System.out.println("SENHA: " + senha);
                    System.out.println("FOTO.: " + foto);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
