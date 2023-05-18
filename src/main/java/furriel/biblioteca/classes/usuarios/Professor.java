package furriel.biblioteca.classes.usuarios;

import furriel.biblioteca.classes.Emprestimo;
import furriel.biblioteca.utils.Utils;

import java.util.Date;

public class Professor extends Usuario {
    private String departamento;
    private String titulacao;

    public Professor(String nome, String matricula, String cpf, String senha, String departamento, String titulacao) {
        super(nome, matricula, cpf, senha);
        this.departamento = departamento;
        this.titulacao = titulacao;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    @Override
    public double verificaMulta(Emprestimo emprestimo) {

        Date dataReal = emprestimo.getDevolucaoReal();
        Date dataPrevista = emprestimo.getDevolucaoPrevista();

        long diferencaDias = Utils.calculaDiferencaDias(dataPrevista, dataReal);

        if(diferencaDias > 0)
            return diferencaDias * 5.00 * 1.25; // 5 reais por dia

        return 0;
    }

    @Override
    public String toString() {
        return "Professor";
    }
}
