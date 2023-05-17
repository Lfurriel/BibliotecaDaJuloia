package furriel.biblioteca.classes;

import furriel.biblioteca.classes.itens.CD;
import furriel.biblioteca.classes.itens.Item;
import furriel.biblioteca.classes.itens.Livro;
import furriel.biblioteca.classes.itens.Revista;
import furriel.biblioteca.classes.usuarios.*;
import furriel.biblioteca.exceptions.IndisponivelException;
import furriel.biblioteca.exceptions.InformacaoInvalidaException;
import furriel.biblioteca.exceptions.NaoEmprestadoException;
import furriel.biblioteca.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Biblioteca {
    private String nome;
    private String cnpj;
    private Usuario contaLogada;
    private List<Item> itens = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();

    private static String chaveSecreta = "Biblioteca@Adm12#89";

    public Biblioteca(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.contaLogada = null;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public List<Usuario> getUsuarios(){
        return usuarios;
    }

    public Usuario getContaLogada() {
        return contaLogada;
    }

    public void setContaLogada(Usuario contaLogada) {
        this.contaLogada = contaLogada;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void addItem(Item item) {
        itens.add(item);
    }

    public void addUsuario(Usuario usuario) throws InformacaoInvalidaException {
        for (Usuario u : usuarios) {
            if(u.getCpf().equals(usuario.getCpf()))
                throw new InformacaoInvalidaException("Usuário ja cadastrado");
        }
        usuarios.add(usuario);
    }

    public void deslogar() {
        contaLogada = null;
    }


    //METODOS PARA GUI
    public void logarGUI(String cpf, String senha) throws InformacaoInvalidaException {

        for (Usuario u : usuarios) {
            if (u.getCpf().equals(cpf)) {
                u.validarSenha(senha);
                contaLogada = u;
                return;
            }
        }

        throw new InformacaoInvalidaException("Usuário não encontrado");

    }

    public Item buscarItemGUI(String busca) {
        int id = -1;
        if (Utils.verificaNumero(busca))
            id = Integer.parseInt(busca);

        for (Item i : itens) {
            if (i.getId() == id || i.getTitulo().equals(busca)) {
                return i;
            }
        }

        return null;
    }

    public void adicionarEmprestimoGUI (Item item, String dia, String mes, String ano) throws InformacaoInvalidaException {
        try {
            int d = Integer.parseInt(dia);
            int m = Integer.parseInt(mes);
            int a = Integer.parseInt(ano);

            m--;
            a -= 1900;

            Date date = new Date(a, m, d);
            contaLogada.adicionarEmprestimo(item, date);
        } catch (Exception e) {
            throw new InformacaoInvalidaException("Erro ao ler data");
        }
    }

    public void pagarMultaGUI(String senha) throws InformacaoInvalidaException {

        contaLogada.validarSenha(senha);
        contaLogada.setMulta(0);
    }

    public Emprestimo buscaEmprestimoGUI(String busca) throws NaoEmprestadoException {
        int id = -1;
        if (Utils.verificaNumero(busca))
            id = Integer.parseInt(busca);
        for (Emprestimo e : contaLogada.getEmprestimos()) {
            if(e.getItem().getId() == id || e.getItem().getTitulo().equals(busca)) {
                return e;
            }
        }
        throw new NaoEmprestadoException("Item não emprestado");
    }

    public void devolverGUI(Emprestimo emprestimo, String dia, String mes, String ano) throws NaoEmprestadoException, InformacaoInvalidaException {
        emprestimo.getItem().devolverItem();
        try {
            int d = Integer.parseInt(dia);
            int m = Integer.parseInt(mes);
            int a = Integer.parseInt(ano);

            m--;
            a -= 1900;

            Date date = new Date(a, m, d);
            emprestimo.setDevolvido(true);
            emprestimo.setDevolucaoReal(date);

        } catch (Exception e) {
            throw new InformacaoInvalidaException("Erro ao ler data");
        }
    }

    public void addAdm(Administrador usuario, String chaveSecreta) throws InformacaoInvalidaException {
        if(Biblioteca.chaveSecreta.equals(chaveSecreta)) {
            for(Usuario u : usuarios) {
                if(u.getCpf().equals(usuario.getCpf()))
                    throw new InformacaoInvalidaException("Usuário com esse cpf já existe");
            }

            usuarios.add(usuario);
            contaLogada = usuario;
        }
        else
            throw new InformacaoInvalidaException("Chave secreta iválida");
    }
}
