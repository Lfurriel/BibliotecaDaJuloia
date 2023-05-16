package furriel.biblioteca.classes;

import furriel.biblioteca.classes.itens.CD;
import furriel.biblioteca.classes.itens.Item;
import furriel.biblioteca.classes.itens.Livro;
import furriel.biblioteca.classes.itens.Revista;
import furriel.biblioteca.classes.usuarios.AcessorTecnico;
import furriel.biblioteca.classes.usuarios.Aluno;
import furriel.biblioteca.classes.usuarios.Professor;
import furriel.biblioteca.classes.usuarios.Usuario;
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

    public void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void logarUsuario() throws InformacaoInvalidaException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite seu CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = sc.nextLine();

        for (Usuario u : usuarios) {
            if (u.getCpf().equals(cpf)) {
                u.validarSenha(senha);
                contaLogada = u;
                return;
            }
        }

        throw new InformacaoInvalidaException("Usuário não encontrado");
    }

    public void cadastrarUsuario() {
        try {
            Scanner sc = new Scanner(System.in);
            int op;
            do {
                System.out.println("Você é:");
                System.out.println("\t1. Aluno:");
                System.out.println("\t2. Professor:");
                System.out.println("\t3. Assessor Técnico:");
                System.out.println("\t0. Cancela:");
                System.out.print("\n\tOpção: ");

                try {
                    op = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Entrada inválida! Digite 1, 2, 3 ou 0");
                    sc.nextLine();
                    op = -1;
                }
            } while (op < 0 || op > 3);

            sc.nextLine(); //Limpa buffer

            if (op == 1) { //Novo Aluno
                System.out.print("Digite seu nome: ");
                String nome = sc.nextLine();
                System.out.print("Digite seu cpf: ");
                String cpf = sc.nextLine();
                System.out.print("Digite sua matrícula: ");
                String matrcula = sc.nextLine();
                System.out.print("Digite sua senha: ");
                String senha = sc.nextLine();
                System.out.print("Digite seu curso: ");
                String curso = sc.nextLine();
                System.out.print("Digite seu período: ");
                String periodo = sc.nextLine();

                Aluno aluno = new Aluno(nome, matrcula, cpf, senha, curso, periodo);
                addUsuario(aluno);
                contaLogada = aluno;
            } else if (op == 2) { //Novo Professor
                System.out.print("Digite seu nome: ");
                String nome = sc.nextLine();
                System.out.print("Digite seu cpf: ");
                String cpf = sc.nextLine();
                System.out.print("Digite sua matrícula: ");
                String matrcula = sc.nextLine();
                System.out.print("Digite sua senha: ");
                String senha = sc.nextLine();
                System.out.print("Digite seu departamento: ");
                String departamento = sc.nextLine();
                System.out.print("Digite sua titulação: ");
                String titulacao = sc.nextLine();

                Professor professor = new Professor(nome, matrcula, cpf, senha, departamento, titulacao);
                addUsuario(professor);
                contaLogada = professor;
            } else if (op == 3) {
                System.out.print("Digite seu nome: ");
                String nome = sc.nextLine();
                System.out.print("Digite seu cpf: ");
                String cpf = sc.nextLine();
                System.out.print("Digite sua matrícula: ");
                String matrcula = sc.nextLine();
                System.out.print("Digite sua senha: ");
                String senha = sc.nextLine();
                System.out.print("Digite sua seção acadêmica: ");
                String secao = sc.nextLine();

                AcessorTecnico acessor = new AcessorTecnico(nome, matrcula, cpf, senha, secao);
                addUsuario(acessor);
                contaLogada = acessor;
            }
        } catch (Exception e) {
            System.out.println("Falha ao criar usuário");
        }

    }

    public void deslogar() {
        contaLogada = null;
    }

    public void apresentarRevistas() {
        System.out.println("--------------------------------------------");
        for (Item i : itens) {
            if (i instanceof Revista) {
                System.out.println("ID: " + i.getId() + " || Título: " + i.getTitulo());
                System.out.println("Autor: " + i.getAutor() + " || Ano de publicação: " + i.getAnoDePublicacao());
                System.out.println("Número: " + ((Revista) i).getNumero() + " || Volume: " + ((Revista) i).getVolume());
                System.out.println("Quantidade disponível: " + i.getQuantidadeDisponivel());
                System.out.println("--------------------------------------------");
            }
        }
    }

    public void apresentarCDs() {
        System.out.println("--------------------------------------------");
        for (Item i : itens) {
            if (i instanceof CD) {
                System.out.println("ID: " + i.getId() + " || Título: " + i.getTitulo());
                System.out.println("Autor: " + i.getAutor() + " || Ano de publicação: " + i.getAnoDePublicacao());
                System.out.println("Volume: " + ((CD) i).getVolume() + " Gravadora: " + ((CD) i).getGravadora());
                System.out.println("Quantidade disponível: " + i.getQuantidadeDisponivel());
                System.out.println("--------------------------------------------");
            }
        }
    }

    public void apresentarLivros() {
        System.out.println("--------------------------------------------");
        for (Item i : itens) {
            if (i instanceof Livro) {
                System.out.println("ID: " + i.getId() + " || Título: " + i.getTitulo());
                System.out.println("Autor: " + i.getAutor() + " || Ano de publicação: " + i.getAnoDePublicacao());
                System.out.println(" Editora: " + ((Livro) i).getEditora() + " ISBN: " + ((Livro) i).getIsbn());
                System.out.println("Quantidade disponível: " + i.getQuantidadeDisponivel());
                System.out.println("--------------------------------------------");
            }
        }
    }

    public void buscarItem(String busca) {
        int id = -1;
        boolean naoEncontrado = true;
        if (Utils.verificaNumero(busca))
            id = Integer.parseInt(busca);

        for (Item i : itens) {
            if (i.getId() == id || i.getTitulo().equals(busca)) {
                naoEncontrado = false;
                System.out.println(i + ": ");
                System.out.println("ID: " + i.getId() + " || Título: " + i.getTitulo());
                System.out.println("Autor: " + i.getAutor() + " || Ano de publicação: " + i.getAnoDePublicacao());
                if (i instanceof Revista)
                    System.out.println("Número: " + ((Revista) i).getNumero() + " || Volume: " + ((Revista) i).getVolume());
                else if (i instanceof CD)
                    System.out.println(((CD) i).getVolume() + " " + ((CD) i).getGravadora());
                else
                    System.out.println(((Livro) i).getEditora() + " " + ((Livro) i).getIsbn());

                if (i.getQuantidadeDisponivel() > 0)
                    System.out.println("Disponível!");
                else
                    System.out.println("Indisponível!");
            }
        }

        if (naoEncontrado)
            System.out.println("Item " + busca + " não encontrado no acervo");
    }

    public void buscarRevista(int input) {
        for (Item i : itens) {
            if (i.getId() == input || (i instanceof Revista && ((Revista) i).getNumero() == input)) {
                System.out.println(i.getId() + " " + i.getTitulo() + " " + i.getAutor() + " " + i.getAnoDePublicacao() +
                        " " + ((Revista) i).getNumero() + " " + ((Revista) i).getVolume());
                if (i.getQuantidadeDisponivel() > 0)
                    System.out.println("Disponível!");
                else
                    System.out.println("Indisponível!");
            }
        }
    }

    public void buscarCD(String input) {
        int id = -1;
        if (Utils.verificaNumero(input))
            id = Integer.parseInt(input);

        for (Item i : itens) {
            if (i.getId() == id || (i instanceof CD && ((CD) i).getGravadora().equalsIgnoreCase(input))) {
                System.out.println(i.getId() + " " + i.getTitulo() + " " + i.getAutor() + " " + i.getAnoDePublicacao() +
                        " " + ((CD) i).getVolume() + " " + ((CD) i).getGravadora());
                if (i.getQuantidadeDisponivel() > 0)
                    System.out.println("Disponível!");
                else
                    System.out.println("Indisponível!");
            }
        }
    }

    public void buscarLivro(String input) {
        int id = -1;
        if (Utils.verificaNumero(input))
            id = Integer.parseInt(input);

        for (Item i : itens) {
            if (i.getId() == id || (i instanceof Livro && ((Livro) i).getIsbn().equals(input))) {
                System.out.println(i.getId() + " " + i.getTitulo() + " " + i.getAutor() + " " + i.getAnoDePublicacao() +
                        " " + ((Livro) i).getEditora() + " " + ((Livro) i).getIsbn());
                if (i.getQuantidadeDisponivel() > 0)
                    System.out.println("Disponível!");
                else
                    System.out.println("Indisponível!");
            }
        }
    }

    public void emprestarItem() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o id do item que gostaria de pegar emprestado: ");
        int id;
        try {
            id = sc.nextInt();
        } catch (Exception e) {
            id = -1;
        }

        Item item = null;
        for (Item i : itens) {
            if (i.getId() == id) {
                item = i;
                break;
            }
        }

        if (item == null)
            System.out.println("Item não encontrado no acervo");
        else {
            try {
                item.emprestarItem();
                contaLogada.adicionarEmprestimo(item);
            } catch (IndisponivelException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void devolverItem() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o id do item a ser devolvido: ");

        try {

            Emprestimo emprestimo = buscarDevolucao(sc.nextInt());

            emprestimo.getItem().devolverItem();
            emprestimo.setDevolvido(true);

            double multa = contaLogada.verificaMulta(emprestimo);

            if (multa > 0)
                System.out.println("Multa à pagar: R$" + multa);

            contaLogada.setMulta(contaLogada.getMulta() + multa);

        } catch (NaoEmprestadoException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Entrada inválida! Por favor digite um valor numérico");
            devolverItem();
        }
    }

    private Emprestimo buscarDevolucao(int id) throws NaoEmprestadoException {
        for (Emprestimo e : contaLogada.getEmprestimos()) {
            if (e.getItem().getId() == id && !e.isDevolvido())
                return e;
        }

        throw new NaoEmprestadoException("Id " + id + " não encontrado na sua lista de empréstimos!");
    }

    public void listarEmprestimos() {
        System.out.println("-----------------------------");
        for (Emprestimo e : contaLogada.getEmprestimos()) {
            if(!e.isDevolvido()) {
                printEmprestimo(e);
            }
        }
    }

    public void historicoEmprestimos() {
        System.out.println("-----------------------------");
        for (Emprestimo e : contaLogada.getEmprestimos()) {
            if(e.isDevolvido()) {
                printEmprestimo(e);
            }
        }
    }

    private void printEmprestimo(Emprestimo e) {
        Item i = e.getItem();
        System.out.println(i + ": ");
        if (i instanceof Revista) {
            System.out.println("ID: " + i.getId() + " || Título: " + i.getTitulo());
            System.out.println("Autor: " + i.getAutor() + " || Ano de publicação: " + i.getAnoDePublicacao());
            System.out.println("Número: " + ((Revista) i).getNumero() + " || Volume: " + ((Revista) i).getVolume());
        } else if (i instanceof CD) {
            System.out.println("ID: " + i.getId() + " || Título: " + i.getTitulo());
            System.out.println("Autor: " + i.getAutor() + " || Ano de publicação: " + i.getAnoDePublicacao());
            System.out.println("Volume: " + ((CD) i).getVolume() + " Gravadora: " + ((CD) i).getGravadora());
        } else {
            System.out.println("ID: " + i.getId() + " || Título: " + i.getTitulo());
            System.out.println("Autor: " + i.getAutor() + " || Ano de publicação: " + i.getAnoDePublicacao());
            System.out.println(" Editora: " + ((Livro) i).getEditora() + " ISBN: " + ((Livro) i).getIsbn());
        }
        System.out.println("Data empréstimo: " + Utils.converteData(e.getDataEmprestimo()));
        System.out.println("Data devolução prevista: " + Utils.converteData(e.getDevolucaoPrevista()));
        if(e.isDevolvido())
            System.out.println("Data devolução: " + Utils.converteData(e.getDevolucaoReal()));
        System.out.println("-----------------------------");
    }
    public void estenderEmprestimos() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o id do item que gostaria de esntender: ");
        try {
            Emprestimo emprestimo = buscarDevolucao(sc.nextInt());
            emprestimo.setDevolucaoPrevista(Utils.calcularDiaDepoisDeUmMes(emprestimo.getDevolucaoPrevista()));

            System.out.println("Emprestimo estendido");
            System.out.println("Nova data: " + Utils.converteData(emprestimo.getDevolucaoPrevista()));
        } catch (NaoEmprestadoException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Entrada inválida! Por favor digite um valor numérico");
            estenderEmprestimos();
        }
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
}
