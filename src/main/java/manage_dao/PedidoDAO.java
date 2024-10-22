package manage_dao;

import database.DatabaseConnection;
import manage_model.Pedido;
import manage_model.Usuario;

import java.sql.*;

public class PedidoDAO implements GenericDAO<Pedido> {

    private final DatabaseConnection  databaseConnection ;

    public PedidoDAO() {
        databaseConnection = new DatabaseConnection();

        // Criação da tabela 'pedido' se não existir
        try (Connection conn = databaseConnection.getConnection()) {
            String sqlCreateTable = """
                CREATE TABLE IF NOT EXISTS pedido (
                    id serial primary key,
                    numero int not null,
                    id_usuario int not null,
                    dtinsercao date default CURRENT_TIMESTAMP,
                    total real not null,
                    status boolean not null default true,
                    foreign key (id_usuario) references usuario(id) ON DELETE CASCADE
                ); """;
            Statement stmt = conn.createStatement();
            stmt.execute(sqlCreateTable);
        } catch (SQLException e) {
            System.out.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }

    @Override
    public void create(Pedido pedido) {
        String sqlInsert = "INSERT INTO pedido(numero, id_usuario, total) VALUES(?, ?, ?)";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {

            // Valida se o usuário existe antes de tentar cadastrar o pedido
            checksExistUsuario(pedido.getIdUsuario());

            pstmt.setInt(1, pedido.getNumero());
            pstmt.setInt(2, pedido.getIdUsuario());
            pstmt.setDouble(3, pedido.getTotal());

            pstmt.executeUpdate();
            System.out.println("Pedido cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar pedido: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    @Override
    public Pedido get(int id) {
        String sqlSelect = "SELECT * FROM pedido WHERE id = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlSelect)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Pedido(
                        rs.getInt("id"),
                        rs.getInt("numero"),
                        rs.getInt("id_usuario"),
                        rs.getDate("dtinsercao"),
                        rs.getDouble("total"),
                        rs.getBoolean("status")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar o pedido: " + e.getMessage());
        }

        return null;
    }

    @Override
    public void update(int id, Pedido pedido) {
        String sqlUpdate = "UPDATE pedido SET numero = ?, id_usuario = ?, total = ?, status = ? WHERE id = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {

            // Valida se o usuário existe antes de tentar atualizar o pedido
            checksExistUsuario(pedido.getIdUsuario());

            pstmt.setInt(1, pedido.getNumero());
            pstmt.setInt(2, pedido.getIdUsuario());
            pstmt.setDouble(3, pedido.getTotal());
            pstmt.setBoolean(4, pedido.getStatus());
            pstmt.setInt(5, id);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Pedido atualizado com sucesso!");
            } else {
                System.out.println("Nenhum pedido encontrado com o ID especificado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar pedido: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sqlDelete = "DELETE FROM pedido WHERE id = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlDelete)) {

            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Pedido excluído com sucesso!");
            } else {
                System.out.println("Nenhum pedido encontrado com o ID especificado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir pedido: " + e.getMessage());
        }
    }

    // Verifica se o usuário existe no banco de dados
    public void checksExistUsuario(int id) throws IllegalArgumentException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.get(id);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado com ID: " + id);
        }
    }
}
