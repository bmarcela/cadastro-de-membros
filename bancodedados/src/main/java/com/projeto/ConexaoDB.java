package com.projeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    @SuppressWarnings("exports")
    public static Connection getConexao() {

        final String url = "jdbc:mysql://localhost:3306/cadastro";
        final String user = "root";
        final String senha = "senhadb";

        try { 
            return DriverManager.getConnection(url, user, senha);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
