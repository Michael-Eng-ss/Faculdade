package manage_service;

import manage_dao.UsuarioDAO;
import manage_model.Usuario;

import java.util.Scanner;

public class UsuarioService {

    private static final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Cadastrar Usuario");
            System.out.println("2. Consultar Usuario");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    cadastrarUsuario(scanner);
                    break;
                case 2:
                    consultarUsuario(scanner);
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

    private static void cadastrarUsuario(Scanner scanner) {
        System.out.print("Digite o seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o seu email: ");
        String email = scanner.nextLine();
        System.out.print("Digite o seu endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Digite o seu telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Digite a sua profissão: ");
        String profissao = scanner.nextLine();

        Usuario usuario = new Usuario(nome, email, endereco, telefone, profissao);
        usuarioDAO.create(usuario);
    }

    private static void consultarUsuario(Scanner scanner) {
        System.out.print("Digite o ID do usuario que deseja consultar: ");
        int id = scanner.nextInt();

        Usuario usuario = usuarioDAO.get(id);
        if (usuario != null) {
            System.out.println("Usuario encontrado: " + usuario);
        } else {
            System.out.println("Usuario com ID " + id + " não encontrado.");
        }
    }
}
