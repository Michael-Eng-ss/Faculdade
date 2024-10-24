package manage_dao;

import database.DatabaseConnection;
import manage_model.Pedido;
import manage_model.Usuario;

import java.sql.*;

public class PedidoDAO implements GenericDAO<Pedido> {

    private final DatabaseConnection  databaseConnection ;

    public PedidoDAO() {
        databaseConnection = new DatabaseConnection();

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
                );""";
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

            checksExistUsuario(pedido.getIdUsuario());

            pstmt.setInt(1, pedido.getNumero());
            pstmt.setInt(2, pedido.getIdUsuario());
            pstmt.setDouble(3, pedido.getTotal());

            pstmt.executeUpdate();
            System.out.println("Pedido cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar pedido: " + e.getMessage());
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
                // Utiliza o novo construtor completo
                return new Pedido(
                        rs.getInt("id"),
                        rs.getInt("numero"),
                        rs.getInt("id_usuario"),
                        rs.getDate("dtinsercao"),  // Pega a data de inserção
                        rs.getDouble("total"),
                        rs.getBoolean("status")   // Pega o status do pedido
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar o pedido: " + e.getMessage());
        }

        return null;  // Retorna null se não encontrar o pedido
    }


    @Override
    public void update(int id, Pedido pedido) {

        if (pedido == null) {
            throw new IllegalArgumentException("O pedido fornecido é inválido (null).");
        }

        String sqlUpdate = "UPDATE pedido SET numero = ?, id_usuario = ?, total = ?, status = ? WHERE id = ?";

        try (Connection conn = databaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {

                // Valida se o usuário existe antes de tentar atualizar o pedido
                checksExistUsuario(pedido.getIdUsuario());

                // Preenche os parâmetros da query
                pstmt.setInt(1, pedido.getNumero());
                pstmt.setInt(2, pedido.getIdUsuario());
                pstmt.setDouble(3, pedido.getTotal());
                pstmt.setBoolean(4, pedido.isStatus());
                pstmt.setInt(5, id);

                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Pedido atualizado com sucesso!");
                } else {
                    System.out.println("Nenhum pedido encontrado com o ID especificado.");
                }

                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Erro ao atualizar pedido (transação revertida): " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Erro de conexão ao banco de dados: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }


    public void cancelarPedido(int idPedido) {
        String sqlUpdateStatus = "UPDATE pedido SET status = false WHERE id = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlUpdateStatus)) {

            pstmt.setInt(1, idPedido);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pedido cancelado com sucesso!");
            } else {
                System.out.println("Pedido não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao cancelar o pedido: " + e.getMessage());
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

    public void checksExistUsuario(int id) throws IllegalArgumentException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.get(id);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado com ID: " + id);
        }
    }
}
