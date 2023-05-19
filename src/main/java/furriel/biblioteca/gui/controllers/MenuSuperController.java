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

/**
 * Controller de menu-super.fxml
 */
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
            /**
             * Ao pressionar o botão usuarios -> Muda para tela de exibição de usuários
             * @param actionEvent Botão pressionado
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "listar-usuarios.fxml", "Usuários");
            }
        });

        itens.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar o botão itens -> Muda para tela de exibição de itens
             * @param actionEvent Botão pressionado
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "mostra-itens.fxml", "Itens");
            }
        });

        cadastrarLivro.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar o botão cadastrarLivro -> Muda para tela de cadastro de livro
             * @param actionEvent Botão pressionado
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "cadastrar-livro.fxml", "Cadastrar Item");
            }
        });

        cadastrarCd.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar o botão cadastrarCd -> Muda para tela de cadastro de cd
             * @param actionEvent Botão pressionado
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "cadastrar-cd.fxml", "Cadastrar Item");
            }
        });

        cadastrarRevista.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar o botão cadastrarRevista -> Muda para tela de cadastro de revista
             * @param actionEvent Botão pressionado
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "cadastrar-revista.fxml", "Cadastrar Item");
            }
        });

        button_sair.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar button_sair -> Desloga o usuário e volta para tela de login
             * @param actionEvent Botão pressionado
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                DisplayBiblioteca displayBiblioteca = DBUtils.getDisplayBiblioteca();
                displayBiblioteca.deslogarGUI();
                DBUtils.changeScene(actionEvent, "tela-inicial.fxml", "Tela Inicial");
            }
        });

    }
}
