package furriel.biblioteca.classes.usuarios;

import furriel.biblioteca.classes.Emprestimo;
import furriel.biblioteca.utils.Utils;

import java.util.Date;

public class Aluno extends Usuario {

    private String curso;
    private String periodo;

    public Aluno(String nome, String matricula, String cpf, String senha, String curso, String periodo) {
        super(nome, matricula, cpf, senha);
        this.curso = curso;
        this.periodo = periodo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    @Override
    public double verificaMulta(Emprestimo emprestimo) {

        Date dataReal = emprestimo.getDevolucaoReal();
        Date dataPrevista = emprestimo.getDevolucaoPrevista();

        long diferencaDias = Utils.calculaDiferencaDias(dataPrevista, dataReal);

        if (diferencaDias > 0)
            return diferencaDias * 5.00; // 5 reais por dia

        return 0;
    }

    @Override
    public String toString() {
        return "Aluno";
    }
}
