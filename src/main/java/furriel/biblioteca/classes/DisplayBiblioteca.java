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
