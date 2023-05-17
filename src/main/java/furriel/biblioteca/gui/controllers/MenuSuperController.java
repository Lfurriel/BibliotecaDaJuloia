package furriel.biblioteca.gui.controllers;

import furriel.biblioteca.classes.Biblioteca;
import furriel.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuSuperController implements Initializable {

    @FXML
    private Button usuarios;
    @FXML
    private Button itens;
    @FXML
    private Button  cadastrar;
    @FXML
    private Label alerta;
    @FXML
    private Button button_sair;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        usuarios.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "listar-usuario.fxml", "Usuários"); //todo: falta
            }
        });

        itens.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "mostra-itens.fxml", "Itens");
            }
        });

        cadastrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "cadastrar-item.fxml", "Cadastrar Item"); //todo: falta
            }
        });

        button_sair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "menu-super.fxml", "MENU | ADM");
            }
        });

    }
}
