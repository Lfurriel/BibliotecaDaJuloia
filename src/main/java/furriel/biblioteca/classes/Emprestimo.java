package furriel.biblioteca.classes;

import furriel.biblioteca.classes.itens.Item;

import java.util.Date;

public class Emprestimo {
    private Item item;
    private Date dataEmprestimo;
    private Date devolucaoPrevista;
    private Date devolucaoReal;
    private boolean devolvido;

    public Emprestimo(Item item, Date dataEmprestimo, Date devolucaoPrevista) {
        this.item = item;
        this.dataEmprestimo = dataEmprestimo;
        this.devolucaoPrevista = devolucaoPrevista;
        this.devolvido = false;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDevolucaoPrevista() {
        return devolucaoPrevista;
    }

    public void setDevolucaoPrevista(Date devolucaoPrevista) {
        this.devolucaoPrevista = devolucaoPrevista;
    }

    public Date getDevolucaoReal() {
        return devolucaoReal;
    }

    public void setDevolucaoReal(Date devolucaoReal) {
        this.devolucaoReal = devolucaoReal;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }
}
