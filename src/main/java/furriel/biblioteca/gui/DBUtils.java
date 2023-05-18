package furriel.biblioteca.gui;



import furriel.biblioteca.HelloApplication;
import furriel.biblioteca.classes.DisplayBiblioteca;
import furriel.biblioteca.classes.usuarios.AcessorTecnico;
import furriel.biblioteca.classes.usuarios.Aluno;
import furriel.biblioteca.classes.usuarios.Professor;
import furriel.biblioteca.exceptions.InformacaoInvalidaException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class DBUtils {

    private static DisplayBiblioteca displayBiblioteca;
    public static DisplayBiblioteca getDisplayBiblioteca() {
        return displayBiblioteca;
    }


    static {
        try {
            displayBiblioteca = new DisplayBiblioteca();
        } catch (FileNotFoundException | InformacaoInvalidaException e) {
            throw new RuntimeException(e);
        }
    }

    public static void changeScene(ActionEvent event, String fxmlFile, String title) {

        Parent root = null;

        try {
            root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource(fxmlFile)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getIcons().add(new Image("https://img.freepik.com/vetores-premium/desenho-para-colorir-desenho-de-gato-lendo-livro-de-desenho-de-personagens_51194-266.jpg?w=2000"));
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
