package classes.itens;

public class Revista extends Item {
    private int volume;
    private int numero;

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

    @Override
    public String toString() {
        return "Revista";
    }
}
