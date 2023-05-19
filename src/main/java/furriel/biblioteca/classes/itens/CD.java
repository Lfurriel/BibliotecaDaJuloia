package furriel.biblioteca.classes.itens;

/**
 * Classe que representa um CD, herda a classe abstrata Item
 */
public class CD extends Item{
    private int volume;
    private String gravadora;

    /**
     * Método construtor
     * @param titulo Título do CD
     * @param autor Autor do CD
     * @param anoDePublicacao Ano de Publicação do CD
     * @param quantidadeDisponivel Quantidade de CDs disponíveis
     * @param volume Volume do CD
     * @param gravadora Gravadora do CD
     */
    public CD(String titulo, String autor, int anoDePublicacao, int quantidadeDisponivel,
              int volume, String gravadora) {
        super(titulo, autor, anoDePublicacao, quantidadeDisponivel);
        this.volume = volume;
        this.gravadora = gravadora;
    }

    public int getVolume() {
        return volume;
    }

    public String getGravadora() {
        return gravadora;
    }

    /**
     * @return Nome da Classe
     */
    @Override
    public String toString() {
        return "CD";
    }
}
