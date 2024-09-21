package com.projeto;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CriarBanco {

    public static void main(String[] args) {
        final String url = "jdbc:mysql://localhost:3306";
        final String user = "root";
        final String senha = "senhadb";

        try {
            Connection conexao = DriverManager.getConnection(url, user, senha);
            if (conexao != null) {
                String sql = "CREATE DATABASE IF NOT EXISTS cadastro";
                Statement stmt = conexao.createStatement();
                stmt.execute(sql);

                conexao.close();
            }
        } catch (SQLException e) {
            System.out.println("Não foi possível conectar: " + e.getMessage());
        }
        
    }
}
