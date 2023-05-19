package furriel.biblioteca.classes;

import furriel.biblioteca.classes.itens.Item;

import java.util.Date;

/**
 * Classe de emprestimo
 */
public class Emprestimo {
    private Item item;
    private Date dataEmprestimo;
    private Date devolucaoPrevista;
    private Date devolucaoReal;
    private boolean devolvido;

    /**
     * Método construntor
     * @param item Objeto Item do empréstimo
     * @param dataEmprestimo Data de empréstimo
     * @param devolucaoPrevista Data prevista da devolução (30 dias a mais da data de empréstimo)
     */
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
