package furriel.biblioteca.gui.controllers;

import furriel.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller de op-sign-up.fxml
 */
public class OpSignUpController implements Initializable {

    @FXML
    private Button aluno;
    @FXML
    private Button professor;
    @FXML
    private Button acessor;
    @FXML
    private Button button_entrar;
    @FXML
    private Button administrador;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        aluno.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar o botão aluno -> Muda para a tela de cadastro de aluno
             * @param actionEvent Botão pressionado
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "sign-up-aluno.fxml", "Cadastrar | Aluno");
            }
        });

        professor.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar o botão professor -> Muda para a tela de cadastro de professor
             * @param actionEvent Botão pressionado
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "sign-up-professor.fxml", "Cadastrar | Professor");
            }
        });

        acessor.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar o botão acessor -> Muda para a tela de cadastro de acessor
             * @param actionEvent Botão pressionado
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "sign-up-acessor.fxml", "Cadastrar | Acessor");
            }
        });

        administrador.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar o botão administrador -> Muda para a tela de cadastro de administrador
             * @param actionEvent Botão pressionado
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "sign-up-administrador.fxml", "Cadastrar | ADM");
            }
        });

        button_entrar.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar o button_entrar -> Muda para a tela de login
             * @param event Botão pressionado
             */
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "tela-inicial.fxml", "Tela Inicial");
            }
        });

    }
}
