/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package testecaixabranca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Education
 */
public class TesteCaixaBranca {

    /**
     * Método para conectar ao banco de dados.
     *
     * @return Conexão com o banco de dados.
     */
    public Connection conectarBD() {
        Connection conn = null;
        try {
            // Carrega o driver JDBC
            Class.forName("com.mysql.Driver.Manager").newInstance();
            // Configuração da URL do banco de dados
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            // Estabelece a conexão
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            // Em caso de erro, imprime a exceção
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Variável para armazenar o nome do usuário.
     */
    public String nome = "";
    /**
     * Variável para armazenar o resultado da verificação.
     */
    public boolean result = false;

    /**
     * Método para verificar se um usuário está registrado no banco de dados.
     *
     * @param login O login do usuário.
     * @param senha A senha do usuário.
     * @return True se o usuário foi encontrado, False caso contrário.
     */
    public boolean verificarUsuario(String login, String senha) {
        String sql = "";
        Connection conn = conectarBD();

        // Instrução SQL para buscar o usuário com o login e senha fornecidos
        sql += "select nome from usuarios";
        sql += " where login = " + "'" + login + "'";
        sql += " and senha = " + "'" + senha + "';";

        try {
            // Criação do Statement e execução da consulta SQL
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            // Verifica se há um resultado na consulta
            if (rs.next()) {
                // Se houver, atualiza o nome do usuário e define o resultado como true
                result = true;
                nome = rs.getString("nome");
            }
        } catch (Exception e) {
            // Em caso de erro, imprime a exceção
            e.printStackTrace();
        } finally {
            try {
                // Fecha a conexão com o banco de dados
                conn.close();
            } catch (Exception e) {
                // Em caso de erro ao fechar a conexão, imprime a exceção
                e.printStackTrace();
            }
        }
        // Retorna o resultado da verificação
        return result;
    }
}
