package manage_model;

import java.util.Date;

public class Pedido {

    private int id;
    private int numero;
    private int idUsuario;
    private Date dtinsercao;
    private double total;
    private boolean status;

    public Pedido(int id, int numero, int idUsuario, Date dtinsercao, double total, boolean status) {
        this.id = id;
        this.numero = numero;
        this.idUsuario = idUsuario;
        this.dtinsercao = dtinsercao;
        this.total = total;
        this.status = status;
    }

    public Pedido(int numero, int idUsuario, double total) {
        this.numero = numero;
        this.idUsuario = idUsuario;
        this.total = total;
    }

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

    public Date getDtinsercao() {
        return dtinsercao;
    }

    public void setDtinsercao(Date dtinsercao) {
        this.dtinsercao = dtinsercao;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", numero=" + numero +
                ", idUsuario=" + idUsuario +
                ", dtinsercao=" + dtinsercao +
                ", total=" + total +
                ", status=" + status +
                '}';
    }
}