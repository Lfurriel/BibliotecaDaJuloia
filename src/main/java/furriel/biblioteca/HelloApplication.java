package furriel.biblioteca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Classe principal do projeto -> Starta a cena inicial do JavaFX
 *
 * @author Lucas Furriel Rodrigues
 */
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tela-inicial.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600,400);
        stage.setTitle("Biblioteca Unesp");
        stage.getIcons().add(new Image("https://img.freepik.com/vetores-premium/desenho-para-colorir-desenho-de-gato-lendo-livro-de-desenho-de-personagens_51194-266.jpg?w=2000"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}