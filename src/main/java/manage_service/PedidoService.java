package manage_service;

import manage_dao.PedidoDAO;
import manage_model.Pedido;

import java.util.Scanner;

public class PedidoService {

    private static final PedidoDAO pedidoDAO = new PedidoDAO();

    public void menuPedido(Scanner scanner) {
        int opcao;

        do {
            System.out.println("\n--- Menu Pedido ---");
            System.out.println("1. Cadastrar Pedido");
            System.out.println("2. Consultar Pedido");
            System.out.println("3. Cancelar Pedido");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrar(scanner);
                        break;
                    case 2:
                        consultar(scanner);
                        break;
                    case 3:
                        cancelar(scanner);
                        break;
                    case 4:
                        System.out.println("Voltando ao Menu Principal...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine();
                opcao = -1;
            }
        } while (opcao != 4);
    }

    private void cadastrar(Scanner scanner) {
        System.out.print("Digite o numero: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o ID do usuário: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o total do pedido: ");
        double total = scanner.nextDouble();
        scanner.nextLine();

        Pedido pedido = new Pedido(numero, idUsuario, total);
        pedidoDAO.create(pedido);
        System.out.println("Pedido cadastrado com sucesso!");
    }

    private void consultar(Scanner scanner) {
        System.out.print("Digite o ID do Pedido que deseja consultar: ");

        if (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            scanner.nextLine();

            Pedido pedido = pedidoDAO.get(id);
            if (pedido != null) {
                System.out.println("Pedido encontrado: " + pedido);
            } else {
                System.out.println("Pedido com ID " + id + " não encontrado.");
            }
        } else {
            System.out.println("Entrada inválida. O ID deve ser um número.");
            scanner.nextLine();
        }
    }

    private void cancelar(Scanner scanner) {
        System.out.print("Digite o ID do Pedido que deseja cancelar: ");

        if (scanner.hasNextInt()) {
            int idPedido = scanner.nextInt();
            scanner.nextLine();

            pedidoDAO.cancelarPedido(idPedido);
            System.out.println("Pedido cancelado com sucesso!");
        } else {
            System.out.println("Entrada inválida. O ID deve ser um número.");
            scanner.nextLine();
        }
    }
}
