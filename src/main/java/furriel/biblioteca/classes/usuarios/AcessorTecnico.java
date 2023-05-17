package furriel.biblioteca.classes.usuarios;

import furriel.biblioteca.classes.Emprestimo;
import furriel.biblioteca.utils.Utils;

import java.util.Date;

public class AcessorTecnico extends Usuario {
    private String secao;

    public AcessorTecnico(String nome, String matricula, String cpf, String senha, String secao) {
        super(nome, matricula, cpf, senha);
        this.secao = secao;
    }

    public String getSecao() {
        return secao;
    }

    public void setSecao(String secao) {
        this.secao = secao;
    }

    @Override
    public double verificaMulta(Emprestimo emprestimo) {
        Date dataReal = emprestimo.getDevolucaoReal();;
        Date dataPrevista = emprestimo.getDevolucaoPrevista();

        long diferencaDias = Utils.calculaDiferencaDias(dataPrevista, dataReal);

        if(diferencaDias > 0)
            return diferencaDias * 5.00 * 1.15; // 5 reais por dia

        return 0;
    }
}
