package manage_service;

import manage_dao.ProdutoDAO;
import manage_model.Produto;

import java.util.Scanner;

public class ProdutoService {
    private static final ProdutoDAO produtoDAO = new ProdutoDAO();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Consultar Produto");
            System.out.println("3. Desativar Produto");
            System.out.println("4. Atualizar Produto");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    consultarProduto();
                    break;
                case 3:
                    desativarProdutoService();
                    break;
                case 4:
                    atualizarProdutoService();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);

        scanner.close();
    }

    private static void inativarProduto( ) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inativarProduto'");
    }

    private static void cadastrarProduto() {
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o preço do produto: ");
        double preco = 0;

        try {
            preco = Double.parseDouble(scanner.nextLine()); // Lê a linha como string e converte para double
        } catch (NumberFormatException e) {
            System.out.println("Preço inválido! Tente novamente.");
            return;
        }

        Produto produto = new Produto(nome, preco);
        produtoDAO.create(produto);

        System.out.println("Produto cadastrado com sucesso!");
    }


    private static void consultarProduto( ) {
        System.out.print("Digite o ID do produto que deseja consultar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        Produto produto = produtoDAO.get(id);
        if (produto != null) {
            System.out.println("Produto encontrado: " + produto);
        } else {
            System.out.println("Produto com ID " + id + " não encontrado.");
        }
    }

    private static void desativarProdutoService() {
        System.out.print("Digite o ID do produto que deseja desativar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha deixada após o nextInt()

        // Desativa o produto pelo ID
        produtoDAO.delete(id);

    }

    private static void atualizarProdutoService() {
        System.out.print("Digite o ID do produto que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        // Consulta o produto pelo ID para verificar se ele existe
        Produto produtoExistente = produtoDAO.get(id);
        if (produtoExistente == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        // Solicita os novos dados
        System.out.print("Digite o novo nome do produto: ");
        String novoNome = scanner.nextLine();

        System.out.print("Digite o novo preço do produto: ");
        double novoPreco = scanner.nextDouble();
        scanner.nextLine(); // Consumir a quebra de linha

        // Cria um novo objeto Produto com os novos dados
        Produto produtoAtualizado = new Produto(novoNome, novoPreco);

        // Chama o método de atualização do DAO
        produtoDAO.update(id, produtoAtualizado);

        System.out.println("Produto atualizado com sucesso!");
    }


}