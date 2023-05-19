package furriel.biblioteca.classes.usuarios;

import furriel.biblioteca.classes.Emprestimo;
import furriel.biblioteca.utils.Utils;

import java.util.Date;

public class AcessorTecnico extends Usuario {
    private String secao;

    /**
     * Método Contrutor
     * @param nome Nome do usuário
     * @param matricula Matícula do usuário
     * @param cpf CPF do usuário
     * @param senha Senha do usuário
     * @param secao Seção do Acessor Técnico
     */
    public AcessorTecnico(String nome, String matricula, String cpf, String senha, String secao) {
        super(nome, matricula, cpf, senha);
        this.secao = secao;
    }

    public String getSecao() {
        return secao;
    }

    /**
     * Calculo de multa de acordo com a quantidade de dias de atraso
     * @param emprestimo Objeto Empréstimo a ser devolvido
     * @return Valor da multa
     */
    @Override
    public double verificaMulta(Emprestimo emprestimo) {
        Date dataReal = emprestimo.getDevolucaoReal();;
        Date dataPrevista = emprestimo.getDevolucaoPrevista();

        long diferencaDias = Utils.calculaDiferencaDias(dataPrevista, dataReal);

        if(diferencaDias > 0)
            return diferencaDias * 5.00 * 1.15; // 5 reais por dia

        return 0;
    }

    /**
     * @return Nome da Classe
     */
    @Override
    public String toString() {
        return "Acessor técnico";
    }
}
