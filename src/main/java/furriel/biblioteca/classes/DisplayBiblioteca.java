package furriel.biblioteca.classes;

import furriel.biblioteca.classes.itens.CD;
import furriel.biblioteca.classes.itens.Item;
import furriel.biblioteca.classes.itens.Livro;
import furriel.biblioteca.classes.itens.Revista;
import furriel.biblioteca.classes.usuarios.AcessorTecnico;
import furriel.biblioteca.classes.usuarios.Aluno;
import furriel.biblioteca.classes.usuarios.Professor;
import furriel.biblioteca.classes.usuarios.Usuario;
import furriel.biblioteca.exceptions.InformacaoInvalidaException;
import furriel.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DisplayBiblioteca {

    private Biblioteca minhaBiblioteca;

    public DisplayBiblioteca(Biblioteca minhaBiblioteca) {
        this.minhaBiblioteca = minhaBiblioteca;
    }

    public DisplayBiblioteca() throws FileNotFoundException {
        Biblioteca biblioteca = new Biblioteca("Biblioteca da Juloia", "56.665.209/0001-29");
        File arquivo = new File("files\\itens.txt ");
        Scanner scanner;
        try {
            scanner = new Scanner(arquivo);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("sem arquivo itens");
        }

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] campos = linha.split("#");

            Item item = criaNovoItem(campos);
            biblioteca.addItem(item);
        }

        arquivo = new File("files\\usuarios.txt ");
        try {
            scanner = new Scanner(arquivo);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("sem arquivo usuarios");
        }

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] campos = linha.split("#");

            Usuario user = criaNovoUsuario(campos);
            biblioteca.addUsuario(user);
        }
        this.minhaBiblioteca = biblioteca;
    }

    private Item criaNovoItem(String[] campos) {
        if(campos[0].equals("Revista")) {
            //Revista # Case six or available. # Sheri Delacruz # 1904 # 42 #8 # 3
            return new Revista(
                    campos[1],
                    campos[2],
                    Integer.parseInt(campos[3]),
                    Integer.parseInt(campos[4]),
                    Integer.parseInt(campos[5]),
                    Integer.parseInt(campos[6]));

        } else if (campos[0].equals("CD")) {
            //CD # Paper away option. # Sierra Wise # 1996 # 9 # 1 # Hernandez-Harrell
            return new CD(
                    campos[1],
                    campos[2],
                    Integer.parseInt(campos[3]),
                    Integer.parseInt(campos[4]),
                    Integer.parseInt(campos[5]),
                    campos[6]);

        } else {
            //Livro # Those without establish our south notice really could. # Amy Clark# 1950 # 42#Ramos LLC#978-0-17-423110-3
            return new Livro(
                    campos[1],
                    campos[2],
                    Integer.parseInt(campos[3]),
                    Integer.parseInt(campos[4]),
                    campos[5],
                    campos[6]);
        }
    }

    private Usuario criaNovoUsuario(String[] campos) {
        if(campos[0].equals("Aluno")) {
            return new Aluno(campos[1], campos[2], campos[3], campos[4], campos[5], campos[6]);
        } else if (campos[0].equals("Professor")) {
            return new Professor(campos[1], campos[2], campos[3], campos[4], campos[5], campos[6]);
        } else {
            return new AcessorTecnico(campos[1], campos[2], campos[3], campos[4], campos[5]);
        }
    }

    public Biblioteca getMinhaBiblioteca() {
        return minhaBiblioteca;
    }

    /**
     * Tela inicial da Biblioteca, responsável por logar ou cadastrar um usuário novo no sitema
     */
    public void telaInicial() {

        Scanner sc = new Scanner(System.in);
        int op;

        do {
            do {
                System.out.println("******* BEM-VINDO A " + minhaBiblioteca.getNome() + " *******\n");
                System.out.println("\nDigite:");
                System.out.println("\t 1. Cadastro");
                System.out.println("\t 2. Entrar");
                System.out.print("\nOpção: ");
                try {
                    op = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Entrada inválida! Digite 1 ou 2");
                    op = -1;
                }
            } while (op != 1 && op != 2);

            sc.nextLine(); //Limpa buffer

            if (op == 1) {
                minhaBiblioteca.cadastrarUsuario();
            } else {
                try {
                    minhaBiblioteca.logarUsuario();
                } catch (InformacaoInvalidaException e) {
                    System.out.println(e.getMessage());
                }
            }
        } while (minhaBiblioteca.getContaLogada() == null);

        telaUsuario();
    }

    private void telaUsuario() {
        Scanner sc = new Scanner(System.in);
        int op;

        do {
            System.out.println();
            System.out.println();
            System.out.println("Olá, " + minhaBiblioteca.getContaLogada().getNome());

            System.out.println("\n\t1- Consultar itens na biblioteca");
            System.out.println("\t2- Pesquisar item específico");
            System.out.println("\t3- Pagar multa");
            System.out.println("\t4- Pegar item emprestado");
            System.out.println("\t5- Devolver item");
            System.out.println("\t6- Listar empréstimos");
            System.out.println("\t7- Estender empréstimos");
            System.out.println("\t8- Histórico empréstimos");
            System.out.println("\t0- SAIR");
            System.out.print("\n\tOpção: ");

            try {
                op = sc.nextInt();
            } catch (Exception e) {
                op = -1;
            }
            switch (op) {
                case 0 -> operacaoSair();
                case 1 -> mostrarItens();
                case 2 -> buscarItem();
                case 3 -> pagarMulta();
                case 4 -> emprestimo();
                case 5 -> devolucao();
                case 6 -> emprestimos();
                case 7 -> estender();
                case 8 -> historico();
                default -> System.out.println("OPÇÃO INVÁLIDA");
            }
        } while (op != 0);
    }

    private void mostrarItens() {
        Scanner sc = new Scanner(System.in);
        int op;

        do {
            System.out.println("Digite: ");
            System.out.println("1. Revistas");
            System.out.println("2. CDs");
            System.out.println("3. Livros");
            System.out.println("0. Cancela");
            System.out.print("\nOpção: ");

            try {
                op = sc.nextInt();
            } catch (Exception e) {
                op = -1;
            }

            if (op < 0 || op > 3)
                System.out.println("Opção inválida!\n");
        } while (op < 0 || op > 3);

        sc.nextLine();

        if (op == 1)
            minhaBiblioteca.apresentarRevistas();
        else if (op == 2)
            minhaBiblioteca.apresentarCDs();
        else if (op == 3)
            minhaBiblioteca.apresentarLivros();
    }

    private void buscarItem() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o título ou id do item que quer buscar: ");
        minhaBiblioteca.buscarItem(sc.nextLine());
    }

    private void pagarMulta() {

        Scanner sc = new Scanner(System.in);
        boolean senhaIncorreta = true;

        if (minhaBiblioteca.getContaLogada().getMulta() == 0) {
            System.out.println("Você não tem multas a pagar. Continue assim!");
        } else {
            do {
                System.out.print("Digite sua senha:");
                try {
                    minhaBiblioteca.getContaLogada().validarSenha(sc.nextLine());
                    senhaIncorreta = false;
                } catch (InformacaoInvalidaException e) {
                    System.out.println(e.getMessage());
                }
            } while (senhaIncorreta);

            minhaBiblioteca.getContaLogada().setMulta(0);
            System.out.println("Obrigado!");
        }

    }

    private void emprestimo() {
        if(minhaBiblioteca.getContaLogada().getMulta() > 0)
            System.out.println("Serviço indisponível, usuário tem multas pendentes");
        else
            minhaBiblioteca.emprestarItem();
    }

    private void devolucao() {
        minhaBiblioteca.devolverItem();
    }

    private void emprestimos() {
        minhaBiblioteca.listarEmprestimos();
    }
    private void estender() {
        minhaBiblioteca.estenderEmprestimos();
    }
    private void historico() {
        minhaBiblioteca.historicoEmprestimos();
    }

    private void operacaoSair() {
        minhaBiblioteca.deslogar();
        telaInicial();
    }

    //METÓDOS PRA GUI
    public void login(ActionEvent event, String cpf, String senha) throws InformacaoInvalidaException {
        minhaBiblioteca.logarGUI(cpf, senha);

        telaUsuarioGUI(event);
    }

    private void telaUsuarioGUI(ActionEvent event) {
        DBUtils.changeScene(event, "menu-usuario.fxml", "MENU");
    }

    public void deslogarGUI() {
        minhaBiblioteca.deslogar();
    }
}
