package manage_model;
import java.util.Date;

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private Date dtinsercao;
    private String endereco;
    private String telefone;
    private String profissao;

    public Usuario(int id, String nome, String email, Date dtinsercao, String endereco, String telefone, String profissao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dtinsercao = dtinsercao;
        this.endereco = endereco;
        this.telefone = telefone;
        this.profissao = profissao;
    }

    public Usuario(String nome, String email, String endereco, String telefone, String profissao) {
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.profissao = profissao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDtinsercao() {
        return dtinsercao;
    }

    public void setDtinsercao(Date dtinsercao) {
        this.dtinsercao = dtinsercao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", dtinsercao=" + dtinsercao +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                ", profissao='" + profissao + '\'' +
                '}';
    }
}