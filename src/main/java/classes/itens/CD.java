package classes.itens;

public class CD extends Item{
    private int volume;
    private String gravadora;

    public CD(String titulo, String autor, int anoDePublicacao, int quantidadeDisponivel,
              int volume, String gravadora) {
        super(titulo, autor, anoDePublicacao, quantidadeDisponivel);
        this.volume = volume;
        this.gravadora = gravadora;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getGravadora() {
        return gravadora;
    }

    public void setGravadora(String gravadora) {
        this.gravadora = gravadora;
    }

    @Override
    public String toString() {
        return "CD";
    }
}
