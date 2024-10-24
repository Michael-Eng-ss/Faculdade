package manage_model;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private boolean status;

    public Produto(String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.status = true;
    }

    public Produto(String nome, double preco, int status) {
        this.nome = nome;
        this.preco = preco;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "Produto [ID=" + id + ", Nome=" + nome + ", Preço=" + preco + ", Status=" + status + "]";
    }
}
