package manage_model;

import java.util.Date;

public class Pedido {
    private int id;
    private int numero;
    private int idUsuario;
    private Date dtInsercao;
    private double total;
    private boolean status;

    public Pedido(int numero, int idUsuario, double total) {
        this.numero = numero;
        this.idUsuario = idUsuario;
        this.total = total;
        this.status = true;
    }

    public Pedido(int id, int numero, int idUsuario, Date dtInsercao, double total, boolean status) {
        this.id = id;
        this.numero = numero;
        this.idUsuario = idUsuario;
        this.dtInsercao = dtInsercao;
        this.total = total;
        this.status = status;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getDtInsercao() {
        return dtInsercao;
    }

    public void setDtInsercao(Date dtInsercao) {
        this.dtInsercao = dtInsercao;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pedido [id=" + id + ", numero=" + numero + ", idUsuario=" + idUsuario + ", dtInsercao=" + dtInsercao +
                ", total=" + total + ", status=" + status + "]";
    }
}
