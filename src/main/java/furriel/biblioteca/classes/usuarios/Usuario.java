package furriel.biblioteca.classes.usuarios;

import furriel.biblioteca.classes.Emprestimo;
import furriel.biblioteca.classes.itens.Item;
import furriel.biblioteca.exceptions.InformacaoInvalidaException;
import furriel.biblioteca.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Usuario {
    private String nome;
    private String matricula;
    private String cpf;
    private String senha;

    private double multa;
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public Usuario(String nome, String matricula, String cpf, String senha) {
        this.nome = nome;
        this.matricula = matricula;
        this.cpf = cpf;
        this.senha = senha;
        this.multa = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public void validarSenha(String senha) throws InformacaoInvalidaException {
        if(!senha.equals(this.senha))
            throw new InformacaoInvalidaException("SENHA INCORRETA!");
    }

    public abstract double verificaMulta(Emprestimo emprestimo);

    public void adicionarEmprestimo(Item item) {
        Date dataEmprestimo = Utils.lerData();
        Date dataPrevista = Utils.calcularDiaDepoisDeUmMes(dataEmprestimo);

        emprestimos.add(new Emprestimo(item, dataEmprestimo, dataPrevista));
    }

    public void adicionarEmprestimo(Item item, Date dataEmprestimo) {
        Date dataPrevista = Utils.calcularDiaDepoisDeUmMes(dataEmprestimo);

        emprestimos.add(new Emprestimo(item, dataEmprestimo, dataPrevista));
    }
}
