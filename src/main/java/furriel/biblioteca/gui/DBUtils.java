package furriel.biblioteca.gui;



import furriel.biblioteca.classes.DisplayBiblioteca;
import furriel.biblioteca.classes.usuarios.AcessorTecnico;
import furriel.biblioteca.classes.usuarios.Aluno;
import furriel.biblioteca.classes.usuarios.Professor;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DBUtils {

    private static DisplayBiblioteca displayBiblioteca;
    public static DisplayBiblioteca getDisplayBiblioteca() {
        return displayBiblioteca;
    }


    static {
        try {
            displayBiblioteca = new DisplayBiblioteca();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void changeScene(ActionEvent event, String fxmlFile, String title) {

        Parent root = null;

        try {
            root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void cadastrarAluno(ActionEvent event, String nome, String matricula, String cpf, String senha,
                                      String curso, String periodo) {
        displayBiblioteca.getMinhaBiblioteca().addUsuario(
                new Aluno(nome, matricula, cpf, senha, curso, periodo)
        );

        DBUtils.changeScene(event, "tela-inicial.fxml", "Tela Inicial");
    }
    public static void cadastrarProfessor(ActionEvent event, String nome, String matricula, String cpf, String senha,
                                      String departamento, String titulacao) {

        displayBiblioteca.getMinhaBiblioteca().addUsuario(
                new Professor(nome, matricula, cpf, senha, departamento, titulacao)
        );

        DBUtils.changeScene(event, "tela-inicial.fxml", "Tela Inicial");
    }
    public static void cadastrarAcessor(ActionEvent event, String nome, String matricula, String cpf, String senha,
                                      String secao) {
        displayBiblioteca.getMinhaBiblioteca().addUsuario(
                new AcessorTecnico(nome, matricula, cpf, senha, secao)
        );

        DBUtils.changeScene(event, "tela-inicial.fxml", "Tela Inicial");
    }
}
