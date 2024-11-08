package manage_service;

import manage_dao.ProdutoDAO;
import manage_model.Produto;

import java.util.Scanner;

public class ProdutoService {

    private static final ProdutoDAO produtoDAO = new ProdutoDAO();

    public void menuProduto(Scanner scanner) {
        int opcao;

        do {
            System.out.println("\n--- Menu Produto ---");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Consultar Produto");
            System.out.println("3. Desativar Produto");
            System.out.println("4. Atualizar Produto");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarProduto(scanner);
                        break;
                    case 2:
                        consultarProduto(scanner);
                        break;
                    case 3:
                        desativarProdutoService(scanner);
                        break;
                    case 4:
                        atualizarProdutoService(scanner);
                        break;
                    case 5:
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
        } while (opcao != 5);
    }

    private void cadastrarProduto(Scanner scanner) {
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();

        System.out.println("Digite sua marca do produto: ");
        String marca = scanner.nextLine();

        System.out.print("Digite o preço do produto: ");
        double preco = 0;

        try {
            preco = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Preço inválido! Tente novamente.");
            return;
        }

        Produto produto = new Produto(nome, preco,marca);
        produtoDAO.create(produto);
        System.out.println("Produto cadastrado com sucesso!");
    }

    private void consultarProduto(Scanner scanner) {
        System.out.print("Digite o ID do produto que deseja consultar: ");

        if (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            scanner.nextLine();

            Produto produto = produtoDAO.get(id);
            if (produto != null) {
                System.out.println("Produto encontrado: " + produto);
            } else {
                System.out.println("Produto com ID " + id + " não encontrado.");
            }
        } else {
            System.out.println("Entrada inválida. O ID deve ser um número.");
            scanner.nextLine();
        }
    }

    private void desativarProdutoService(Scanner scanner) {
        System.out.print("Digite o ID do produto que deseja desativar: ");

        if (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            scanner.nextLine();

            produtoDAO.delete(id);
            System.out.println("Produto desativado com sucesso!");
        } else {
            System.out.println("Entrada inválida. O ID deve ser um número.");
            scanner.nextLine();
        }
    }

    private void atualizarProdutoService(Scanner scanner) {
        System.out.print("Digite o ID do produto que deseja atualizar: ");

        if (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            scanner.nextLine();

            Produto produtoExistente = produtoDAO.get(id);
            if (produtoExistente == null) {
                System.out.println("Produto não encontrado!");
                return;
            }

            System.out.print("Digite o novo nome do produto: ");
            String novoNome = scanner.nextLine();

            System.out.println("Digite sua marca do produto: ");
            String novoMarca = scanner.nextLine();

            System.out.print("Digite o novo preço do produto: ");
            double novoPreco = 0;

            try {
                novoPreco = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Preço inválido! Tente novamente.");
                return;
            }

            Produto produtoAtualizado = new Produto(novoNome, novoPreco, novoMarca);
            produtoDAO.update(id, produtoAtualizado);
            System.out.println("Produto atualizado com sucesso!");
        } else {
            System.out.println("Entrada inválida. O ID deve ser um número.");
            scanner.nextLine();
        }
    }
}
