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
            // Mensagem de erro mais detalhada
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
            System.out.println("Código SQLState: " + e.getSQLState());
            System.out.println("Código de erro: " + e.getErrorCode());

            // Opcional: lançar a exceção se quiser tratar em outro nível
            // throw new RuntimeException("Erro ao cadastrar produto", e);
        }
    }


    @Override
    public Produto get(int id) {
        String sqlSelect = "SELECT * FROM produto WHERE id = ? AND status = true";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlSelect)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Produto(rs.getString("nome"), rs.getDouble("preco"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar produto: " + e.getMessage());
        }
        return null;
    }


    @Override
    public void update(int id, Produto produto) {
        String sqlUpdate = "UPDATE produto SET nome = ?, preco = ? WHERE id = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {

            // Define os parâmetros da query
            pstmt.setString(1, produto.getNome());
            pstmt.setDouble(2, produto.getPreco());
            pstmt.setInt(3, id);

            // Executa a atualização
            pstmt.executeUpdate();

            System.out.println("Produto atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sqlUpdate = "UPDATE produto SET status = false WHERE id = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {

            // Define o parâmetro da query
            pstmt.setInt(1, id);

            // Executa a query
            pstmt.executeUpdate();

            System.out.println("Produto desativado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao desativar produto: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

}
