package furriel.biblioteca.classes.usuarios;

import furriel.biblioteca.classes.Emprestimo;
import furriel.biblioteca.utils.Utils;

import java.util.Date;

public class Aluno extends Usuario {

    private String curso;
    private String periodo;

    /**
     * Método Contrutor
     * @param nome Nome do usuário
     * @param matricula Matícula do usuário
     * @param cpf CPF do usuário
     * @param senha Senha do usuário
     * @param curso Curso do Aluno
     * @param periodo Periodo do Aluno
     */
    public Aluno(String nome, String matricula, String cpf, String senha, String curso, String periodo) {
        super(nome, matricula, cpf, senha);
        this.curso = curso;
        this.periodo = periodo;
    }

    public String getCurso() {
        return curso;
    }

    public String getPeriodo() {
        return periodo;
    }

    /**
     * Calculo de multa de acordo com a quantidade de dias de atraso
     * @param emprestimo Objeto Empréstimo a ser devolvido
     * @return Valor da multa
     */
    @Override
    public double verificaMulta(Emprestimo emprestimo) {

        Date dataReal = emprestimo.getDevolucaoReal();
        Date dataPrevista = emprestimo.getDevolucaoPrevista();

        long diferencaDias = Utils.calculaDiferencaDias(dataPrevista, dataReal);

        if (diferencaDias > 0)
            return diferencaDias * 5.00; // 5 reais por dia

        return 0;
    }

    /**
     * @return Nome da Classe
     */
    @Override
    public String toString() {
        return "Aluno";
    }
}
