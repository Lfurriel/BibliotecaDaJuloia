package furriel.biblioteca.classes.usuarios;

import furriel.biblioteca.classes.Emprestimo;

public class Administrador extends Usuario{
    public Administrador(String nome, String matricula, String cpf, String senha) {
        super(nome, matricula, cpf, senha);
    }

    @Override
    public double verificaMulta(Emprestimo emprestimo) {
        return 0;
    }
}
