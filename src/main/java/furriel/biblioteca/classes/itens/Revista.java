package furriel.biblioteca.classes.itens;

public class Revista extends Item {
    private int volume;
    private int numero;

    /**
     * Método Construtor
     * @param titulo Título do Revista
     * @param autor Autor do Revista
     * @param anoDePublicacao Ano de Publicação do Revista
     * @param quantidadeDisponivel Quantidade de Revistas disponíveis
     * @param volume Volume do Revista
     * @param numero Número do Revista
     */
    public Revista(String titulo, String autor, int anoDePublicacao,
                   int quantidadeDisponivel, int volume, int numero) {
        super(titulo, autor, anoDePublicacao, quantidadeDisponivel);
        this.volume = volume;
        this.numero = numero;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return Nome da Classe
     */
    @Override
    public String toString() {
        return "Revista";
    }
}
