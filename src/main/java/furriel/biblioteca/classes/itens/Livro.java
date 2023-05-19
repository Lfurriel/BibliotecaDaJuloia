package furriel.biblioteca.classes.itens;

public class Livro extends Item{
    private String editora;
    private String isbn;

    /**
     * Método Construtor
     * @param titulo Título do Livro
     * @param autor Autor do Livro
     * @param anoDePublicacao Ano de Publicação do Livro
     * @param quantidadeDisponivel Quantidade de Livros disponíveis
     * @param editora Editora do Livro
     * @param isbn ISBN do Livro
     */
    public Livro(String titulo, String autor, int anoDePublicacao, int quantidadeDisponivel,
                 String editora, String isbn) {
        super(titulo, autor, anoDePublicacao, quantidadeDisponivel);
        this.editora = editora;
        this.isbn = isbn;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return Nome da Classe
     */
    @Override
    public String toString() {
        return "Livro";
    }
}
