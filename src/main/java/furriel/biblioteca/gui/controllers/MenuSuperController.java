package furriel.biblioteca.gui.controllers;

import furriel.biblioteca.classes.DisplayBiblioteca;
import furriel.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuSuperController implements Initializable {

    @FXML
    private Button usuarios;
    @FXML
    private Button itens;
    @FXML
    private Button  cadastrarLivro;
    @FXML
    private Button  cadastrarCd;
    @FXML
    private Button  cadastrarRevista;
    @FXML
    private Button button_sair;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        usuarios.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "listar-usuarios.fxml", "Usuários");
            }
        });

        itens.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "mostra-itens.fxml", "Itens");
            }
        });

        cadastrarLivro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "cadastrar-livro.fxml", "Cadastrar Item");
            }
        });

        cadastrarCd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "cadastrar-cd.fxml", "Cadastrar Item");
            }
        });

        cadastrarRevista.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "cadastrar-revista.fxml", "Cadastrar Item");
        });

        button_sair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DisplayBiblioteca displayBiblioteca = DBUtils.getDisplayBiblioteca();
                displayBiblioteca.deslogarGUI();
                DBUtils.changeScene(actionEvent, "tela-inicial.fxml", "Tela Inicial");
            }
        });

    }
}
