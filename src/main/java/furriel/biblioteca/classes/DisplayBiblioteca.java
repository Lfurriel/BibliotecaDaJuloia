package furriel.biblioteca.classes;

import furriel.biblioteca.classes.itens.CD;
import furriel.biblioteca.classes.itens.Item;
import furriel.biblioteca.classes.itens.Livro;
import furriel.biblioteca.classes.itens.Revista;
import furriel.biblioteca.classes.usuarios.*;
import furriel.biblioteca.exceptions.InformacaoInvalidaException;
import furriel.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DisplayBiblioteca {

    private Biblioteca minhaBiblioteca;

    /**
     * Método construtor -> Faz a leitura de arquivos para criar o objeto de biblioteca e adicionar itens e usuários
     * @throws FileNotFoundException Erro caso não leia o arquivo
     * @throws InformacaoInvalidaException Erro
     */
    public DisplayBiblioteca() throws FileNotFoundException, InformacaoInvalidaException {
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

    /**
     * Cria um novo item
     * @param campos Linha do arquivo splitada
     * @return Objeto criado
     */
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

    /**
     * Cria novo usuário
     * @param campos Linha do arquivo splitada
     * @return Objeto criado
     */
    private Usuario criaNovoUsuario(String[] campos) {
        return switch (campos[0]) {
            case "Aluno" -> new Aluno(campos[1], campos[2], campos[3], campos[4], campos[5], campos[6]);
            case "Professor" -> new Professor(campos[1], campos[2], campos[3], campos[4], campos[5], campos[6]);
            case "Acessor" -> new AcessorTecnico(campos[1], campos[2], campos[3], campos[4], campos[5]);
            default -> new Administrador(campos[1], campos[2], campos[3], campos[4]);
        };
    }

    public Biblioteca getMinhaBiblioteca() {
        return minhaBiblioteca;
    }

    /**
     * Login do usuário
     * @param event Botão clicado
     * @param cpf CPF do usuário
     * @param senha Senha do usuário
     * @throws InformacaoInvalidaException Erro caso usuário não tenha sido encontrado ou senha inserida seja inválida
     */
    public void login(ActionEvent event, String cpf, String senha) throws InformacaoInvalidaException {
        minhaBiblioteca.logarGUI(cpf, senha);
        if(minhaBiblioteca.getContaLogada() instanceof Administrador)
            DBUtils.changeScene(event, "menu-super.fxml", "MENU | ADM");
        else
            DBUtils.changeScene(event, "menu-usuario.fxml", "MENU");
    }

    /**
     * Desloga o usuário
     */
    public void deslogarGUI() {
        minhaBiblioteca.deslogar();
    }
}
