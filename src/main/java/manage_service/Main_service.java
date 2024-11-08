package manage_service;

import java.util.Scanner;

public class Main_service {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int opcao;

        do {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Modulo Usuario");
            System.out.println("2. Modulo Produto");
            System.out.println("3. Modulo Pedido");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        UsuarioService usuarioService = new UsuarioService();
                        usuarioService.menuUsuario(scanner);
                        break;
                    case 2:
                        ProdutoService produtoService = new ProdutoService();
                        produtoService.menuProduto(scanner);
                        break;
                    case 3:
                        PedidoService pedidoService = new PedidoService();
                        pedidoService.menuPedido(scanner);
                        break;
                    case 4:
                        System.out.println("Saindo...");
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

        scanner.close();
    }
}
