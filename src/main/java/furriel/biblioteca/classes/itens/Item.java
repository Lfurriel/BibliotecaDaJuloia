package furriel.biblioteca.classes.itens;

import furriel.biblioteca.exceptions.IndisponivelException;
import furriel.biblioteca.exceptions.NaoEmprestadoException;
import furriel.biblioteca.interfaces.Emprestavel;

/**
 * Classe abstrata de Item
 */
public abstract class Item implements Emprestavel {
    private String titulo;
    private String autor;
    private int anoDePublicacao;
    private int quantidadeDisponivel;
    private int quantidadeEmprestada;
    private final int id;
    private static int generator = 0;

    /**
     * Método Construtor
     * @param titulo Título do item
     * @param autor Autor do item
     * @param anoDePublicacao Ano de Publicação do itens
     * @param quantidadeDisponivel Quantidade de itens disponíveis
     */
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

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    /**
     * Implementação da interface Emprestável -> Valida se o item pode ser emprestado, caso positivo, diminui a quantidade
     * de itens disponivel e soma a quantidade de itens emprestado
     * @throws IndisponivelException Exceção caso o item esteja indisponível
     */
    @Override
    public void emprestarItem() throws IndisponivelException {

        if (quantidadeDisponivel <= 0)
            throw new IndisponivelException("Item " + id + " indisponível!");
        this.quantidadeDisponivel--;
        this.quantidadeEmprestada++;
    }

    /**
     * Implementação da interface Emprestável -> Valida se o item foi sim emprestado, caso positivo, diminui a quantidade
     * de emprestimos e soma a quantidade de itens dispiniveis
     * @throws NaoEmprestadoException Exceção caso o item não foi emprestado
     */
    @Override
    public void devolverItem() throws NaoEmprestadoException {

        if (quantidadeEmprestada == 0)
            throw new NaoEmprestadoException("Item não possui empréstimos");
        this.quantidadeEmprestada--;
        this.quantidadeDisponivel++;
    }
}
