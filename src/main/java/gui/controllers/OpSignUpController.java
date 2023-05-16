package gui.controllers;

import gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class OpSignUpController implements Initializable {

    @FXML
    private Button aluno;
    @FXML
    private Button professor;
    @FXML
    private Button acessor;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        aluno.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "sign-up-aluno.fxml", "Cadastrar | Aluno");
            }
        });

        professor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "sign-up-prfessor.fxml", "Cadastrar | Professor");
            }
        });

        acessor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "sign-up-acessor.fxml", "Cadastrar | Acessor");
            }
        });

    }
}