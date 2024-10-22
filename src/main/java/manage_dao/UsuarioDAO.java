package manage_dao;

import database.DatabaseConnection;
import manage_model.Usuario;

import java.sql.*;

public class UsuarioDAO implements GenericDAO<Usuario>{

    private final DatabaseConnection databaseConnection;

    public UsuarioDAO() {

        databaseConnection = new DatabaseConnection();

        try (Connection conn = databaseConnection.getConnection()) {
            String sqlCreateTable = """
                    create table IF NOT EXISTS usuario(
                        id serial primary key,
                        nome varchar(255) not null,
                        email varchar(255) not null unique,
                        dtinsercao date default CURRENT_TIMESTAMP,
                        endereco varchar(255) not null,
                        telefone varchar(255) not null,
                        profissao varchar(255) not null) """;
            Statement stmt = conn.createStatement();
            stmt.execute(sqlCreateTable);
        } catch (SQLException e) {
            System.out.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }

    @Override
    public void create(Usuario user) {
        String sqlInsert = "INSERT INTO usuario(nome, email, endereco, telefone, profissao) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {

            pstmt.setString(1, user.getNome());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getEndereco());
            pstmt.setString(4, user.getTelefone());
            pstmt.setString(5, user.getProfissao());

            pstmt.executeUpdate();
            System.out.println("Usuario cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuario: " + e.getMessage());
        }
    }

    @Override
    public Usuario get(int id) {
        String sqlSelect = "SELECT * FROM usuario WHERE id = ?";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlSelect)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getDate("dtinsercao"),
                        rs.getString("endereco"),
                        rs.getString("telefone"),
                        rs.getString("profissao")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar o usuario: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void update(int id, Usuario obj) {

    }

    @Override
    public void delete(int id) {

    }
}
