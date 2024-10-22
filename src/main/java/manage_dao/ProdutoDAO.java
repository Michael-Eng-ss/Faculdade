package manage_dao;

import database.DatabaseConnection ;
import manage_model.Produto;

import java.sql.*;

public class ProdutoDAO implements GenericDAO<Produto>{

    private final DatabaseConnection  databaseConnection;

    public ProdutoDAO() {

        databaseConnection = new DatabaseConnection ();

        try (Connection conn = databaseConnection.getConnection()) {
            String sqlCreateTable = """
                CREATE TABLE IF NOT EXISTS produto (
                    id SERIAL PRIMARY KEY,
                    nome TEXT NOT NULL,
                    preco REAL NOT NULL) """;
            Statement stmt = conn.createStatement();
            stmt.execute(sqlCreateTable);
        } catch (SQLException e) {
            System.out.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }

    @Override
    public void create(Produto produto) {
        String sqlInsert = "INSERT INTO produto(nome, preco) VALUES(?, ?)";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
            pstmt.setString(1, produto.getNome());
            pstmt.setDouble(2, produto.getPreco());
            pstmt.executeUpdate();
            System.out.println("Produto cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    @Override
    public Produto get(int id) {
        String sqlSelect = "SELECT * FROM produto WHERE id = ?";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlSelect)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Produto(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar produto: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void update(int id, Produto obj) {

    }

    @Override
    public void delete(int id) {

    }
}
