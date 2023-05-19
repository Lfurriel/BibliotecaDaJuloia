package furriel.biblioteca.classes.usuarios;

import furriel.biblioteca.classes.Emprestimo;
import furriel.biblioteca.utils.Utils;

import java.util.Date;

public class Professor extends Usuario {
    private String departamento;
    private String titulacao;

    /**
     * Método Contrutor
     * @param nome Nome do usuário
     * @param matricula Matícula do usuário
     * @param cpf CPF do usuário
     * @param senha Senha do usuário
     * @param departamento Departamento do professor
     * @param titulacao Titulação do professor
     */
    public Professor(String nome, String matricula, String cpf, String senha, String departamento, String titulacao) {
        super(nome, matricula, cpf, senha);
        this.departamento = departamento;
        this.titulacao = titulacao;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getTitulacao() {
        return titulacao;
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

        if(diferencaDias > 0)
            return diferencaDias * 5.00 * 1.25; // 5 reais por dia

        return 0;
    }

    /**
     * @return Nome da Classe
     */
    @Override
    public String toString() {
        return "Professor";
    }
}
