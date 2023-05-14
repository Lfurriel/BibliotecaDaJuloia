package classes.itens;

import exceptions.IndisponivelException;
import exceptions.NaoEmprestadoException;
import interfaces.Emprestavel;

public abstract class Item implements Emprestavel {
    private String titulo;
    private String autor;
    private int anoDePublicacao;
    private int quantidadeDisponivel;
    private int quantidadeEmprestada;
    private final int id;
    private static int generator = 0;

    public Item(String titulo, String autor, int anoDePublicacao,
                int quantidadeDisponivel) {
        this.id = ++generator;
        this.titulo = titulo;
        this.autor = autor;
        this.anoDePublicacao = anoDePublicacao;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.quantidadeEmprestada = 0;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoDePublicacao() {
        return anoDePublicacao;
    }

    public void setAnoDePublicacao(int anoDePublicacao) {
        this.anoDePublicacao = anoDePublicacao;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public int getQuantidadeEmprestada() {
        return quantidadeEmprestada;
    }

    public void setQuantidadeEmprestada(int quantidadeEmprestada) {
        this.quantidadeEmprestada = quantidadeEmprestada;
    }

    @Override
    public void emprestarItem() throws IndisponivelException {

        if (quantidadeDisponivel <= 0)
            throw new IndisponivelException("Item " + id + " indisponível!");
        this.quantidadeDisponivel--;
        this.quantidadeEmprestada++;
    }

    @Override
    public void devolverItem() throws NaoEmprestadoException {

        if (quantidadeEmprestada == 0)
            throw new NaoEmprestadoException("Item não possui empréstimos");
        this.quantidadeEmprestada--;
        this.quantidadeDisponivel++;
    }
}
