import manage_dao.PedidoDAO;
import manage_model.Pedido;

import java.util.Scanner;

public class PedidoService {

    private static final PedidoDAO pedidoDAO = new PedidoDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Cadastrar Pedido");
            System.out.println("2. Consultar Pedido");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    cadastrar(scanner);
                    break;
                case 2:
                    consultar(scanner);
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 3);

        scanner.close();
    }

    private static void inativarProduto(Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inativarProduto'");
    }

    private static void cadastrar(Scanner scanner) {
        System.out.print("Digite o numero: ");
        int numero = scanner.nextInt();
        System.out.print("Digite o id do usuario: ");
        int idUsuario = scanner.nextInt();
        System.out.print("Digite o total do pedido: ");
        double total = scanner.nextDouble();

        Pedido pedido = new Pedido(numero, idUsuario, total);
        pedidoDAO.create(pedido);
    }

    private static void consultar(Scanner scanner) {
        System.out.print("Digite o ID do Pedido que deseja consultar: ");
        int id = scanner.nextInt();

        Pedido pedido = pedidoDAO.get(id);
        if (pedido != null) {
            System.out.println("Pedido encontrado: " + pedido);
        } else {
            System.out.println("Pedido com ID " + id + " não encontrado.");
        }
    }
}
