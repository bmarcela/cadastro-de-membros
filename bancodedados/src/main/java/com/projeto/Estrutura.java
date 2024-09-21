package com.projeto;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

public class Estrutura {

    public static void main(String[] args) throws SQLException {
        
        Connection conexao = ConexaoDB.getConexao();
        if (conexao != null) {
            String sql = "CREATE TABLE IF NOT EXISTS homens ("
            + "cod INT AUTO_INCREMENT PRIMARY KEY,"
            + "nome VARCHAR(255) NOT NULL UNIQUE KEY,"
            + "dataNasc Date,"
            + "endereco VARCHAR(255) NOT NULL,"
            + "telefone VARCHAR(14) NOT NULL,"
            + "ministerio VARCHAR(255)"
            + ")";

            Statement stmt = conexao.createStatement();
            stmt.execute(sql);

        } else {
            System.out.println("A conexão não foi estabelecida");
        }

        conexao.close();
    }
}
