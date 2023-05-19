package furriel.biblioteca.classes;

import furriel.biblioteca.classes.itens.Item;
import furriel.biblioteca.classes.usuarios.*;
import furriel.biblioteca.exceptions.InformacaoInvalidaException;
import furriel.biblioteca.exceptions.NaoEmprestadoException;
import furriel.biblioteca.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe que representa uma biblioteca
 */
public class Biblioteca {
    private final String nome;
    private final String cnpj;
    private Usuario contaLogada;
    private List<Item> itens = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();

    private static final String chaveSecreta = "123";

    /**
     * Método construtor
     * @param nome
     * @param cnpj
     */
    public Biblioteca(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.contaLogada = null;
    }

    public String getNome() {
        return nome;
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

    /**
     * Adiciona a lista de itens um novo item
     * @param item Novo item
     */
    public void addItem(Item item) {
        itens.add(item);
    }

    /**
     * Adiciona um usuário a lista de usuários
     * @param usuario Novo usuário
     * @throws InformacaoInvalidaException Erro caso usuário já tenha sido cadastrado
     */
    public void addUsuario(Usuario usuario) throws InformacaoInvalidaException {
        for (Usuario u : usuarios) {
            if(u.getCpf().equals(usuario.getCpf()))
                throw new InformacaoInvalidaException("Usuário ja cadastrado");
        }
        usuarios.add(usuario);
    }

    /**
     * Desloga usuário
     */
    public void deslogar() {
        contaLogada = null;
    }

    /**
     * Método que busca um usuário na lista de usuários da biblioteca e seta a váriavel contaLogada para
     * o usuário que corresponde aos parâmetros
     * @param cpf CPF do usuário
     * @param senha Senha do usuário
     * @throws InformacaoInvalidaException Erro quando a senha inserída não é válida ou não foi encontrado usuário na lista
     */
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

    /**
     * Busca da lista de itens da biblioteca aquele que corresponde ao parâmetro
     * @param busca Nome ou Id do item
     * @return O Item correspondente a busca || null caso não encontrado
     */
    public Item buscarItemGUI(String busca) {
        int id = -1;
        if (Utils.verificaNumero(busca)) //Se o parâmetro apresenta apenas número, faço um parse int
            id = Integer.parseInt(busca);

        for (Item i : itens) {
            if (i.getId() == id || i.getTitulo().equals(busca)) {
                return i;
            }
        }

        return null;
    }

    /**
     * Adiciona um novo empréstimo a lista de empréstimos do usuário logado
     * @param item Item a ser emprestado
     * @param dia Dia do empréstimo
     * @param mes Mês do empréstimo
     * @param ano Ano do empréstimo
     * @throws InformacaoInvalidaException Retorna erro caso dê algum problema a ler a data
     */
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

    /**
     * Zera a multa da conta logada
     * @param senha Senha da conta logada
     * @throws InformacaoInvalidaException Erro caso a senha não corresponda
     */
    public void pagarMultaGUI(String senha) throws InformacaoInvalidaException {

        contaLogada.validarSenha(senha);
        contaLogada.setMulta(0);
    }

    /**
     * Busca na lista de empréstimo um específico
     * @param busca Nome ou Id do item
     * @return Retorna o empréstimo
     * @throws NaoEmprestadoException Erro caso não foi encontrado o Empréstimo
     */
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

    /**
     * Devolve um item -> seta a váriável de emprestimo isDevolvido para true e calcula uma possível multa
     * @param emprestimo Empréstimo a ser devolvido
     * @param dia Dia da devolução
     * @param mes Mês da devolução
     * @param ano Ano da devolução
     * @throws NaoEmprestadoException Erro caso item não foi emprestado
     * @throws InformacaoInvalidaException Erro caso dê problema a ler a data
     */
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

    /**
     * Adiciona um administrador a lista de usuários. Necessário passar por uma validação de chave secreta
     * @param usuario Usuário adm a ser adicionado
     * @param chaveSecreta Senha secreta vinda da criação
     * @throws InformacaoInvalidaException Erro caso a chave secreta esteja erra ou usuário já tenha sido cadastrado
     */
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
