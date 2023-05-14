package classes.itens;

public class Livro extends Item{
    private String editora;
    private String isbn;

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

    @Override
    public String toString() {
        return "Livro";
    }
}
