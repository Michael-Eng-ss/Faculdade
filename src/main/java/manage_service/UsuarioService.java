package manage_service;

import manage_dao.UsuarioDAO;
import manage_model.Usuario;

import java.util.Scanner;

public class UsuarioService {

    private static final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void menuUsuario(Scanner scanner) {
        int opcao;

        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Cadastrar Usuario");
            System.out.println("2. Consultar Usuario");
            System.out.println("3. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");


            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarUsuario(scanner);
                        break;
                    case 2:
                        consultarUsuario(scanner);
                        break;
                    case 3:
                        System.out.println("Voltando ao Menu Principal");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine();
                opcao = -1;
            }
        } while (opcao != 3);
    }

    private void cadastrarUsuario(Scanner scanner) {
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
        System.out.println("Usuário cadastrado com sucesso.");
    }

    private void consultarUsuario(Scanner scanner) {
        System.out.print("Digite o ID do usuario que deseja consultar: ");

        if (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            scanner.nextLine();

            Usuario usuario = usuarioDAO.get(id);
            if (usuario != null) {
                System.out.println("Usuario encontrado: " + usuario);
            } else {
                System.out.println("Usuario com ID " + id + " não encontrado.");
            }
        } else {
            System.out.println("Entrada inválida. O ID deve ser um número.");
            scanner.nextLine();
        }
    }
}
