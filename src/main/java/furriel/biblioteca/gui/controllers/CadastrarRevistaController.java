package furriel.biblioteca.gui.controllers;

import furriel.biblioteca.classes.Biblioteca;
import furriel.biblioteca.classes.itens.Revista;
import furriel.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller de cadastrar-revista.fxml
 */
public class CadastrarRevistaController implements Initializable {

    @FXML
    private TextField titulo;

    @FXML
    private TextField autor;

    @FXML
    private TextField anoPublicacao;

    @FXML
    private TextField quantidade;

    @FXML
    private TextField volume;

    @FXML
    private TextField numero;

    @FXML
    private Button button_cadastrar;

    @FXML
    private Label alerta;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_cadastrar.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar o botão cadastrar -> Cria com os parâmetros preenchidos uma nova Revista e adicona a lista de itens
             * da biblioteca
             * @param event Botão pressionado
             */
            @Override
            public void handle(ActionEvent event) {
                Biblioteca biblioteca = DBUtils.getDisplayBiblioteca().getMinhaBiblioteca();
                if(titulo.getText().equals("") || autor.getText().equals("") || anoPublicacao.getText().equals("") ||
                        quantidade.getText().equals("") || volume.getText().equals("") || numero.getText().equals(""))
                    alerta.setText("Preencha campos!");
                else {
                    try {
                        Revista revista = new Revista(titulo.getText(), autor.getText(), Integer.parseInt(anoPublicacao.getText()),
                                Integer.parseInt(quantidade.getText()), Integer.parseInt(volume.getText()), Integer.parseInt(numero.getText()));
                        biblioteca.addItem(revista);
                        DBUtils.changeScene(event, "menu-super.fxml", "MENU | ADM");
                    } catch (Exception e) {
                        alerta.setText("ERRO");
                    }
                }
            }
        });

    }
}
