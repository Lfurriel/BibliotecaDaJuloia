package furriel.biblioteca.classes.usuarios;

import furriel.biblioteca.classes.Emprestimo;

public class Administrador extends Usuario{

    /**
     * Método Contrutor
     * @param nome Nome do usuário
     * @param matricula Matícula do usuário
     * @param cpf CPF do usuário
     * @param senha Senha do usuário
     */
    public Administrador(String nome, String matricula, String cpf, String senha) {
        super(nome, matricula, cpf, senha);
    }

    /**
     * Calculo de multa de acordo com a quantidade de dias de atraso
     * @param emprestimo Objeto Empréstimo a ser devolvido
     * @return Valor da multa
     */
    @Override
    public double verificaMulta(Emprestimo emprestimo) {
        return 0;
    }

    /**
     * @return Nome da Classe
     */
    @Override
    public String toString() {
        return "Administrador";
    }
}
