package gui.controllers;

import classes.DisplayBiblioteca;
import exceptions.InformacaoInvalidaException;
import gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaInicialController implements Initializable {

    @FXML
    private TextField tf_cpf;

    @FXML
    private PasswordField tf_senha;

    @FXML
    private Button button_login;

    @FXML
    private Button button_cadastrar;

    @FXML
    private TextField erro;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DisplayBiblioteca displayBiblioteca = DBUtils.getDisplayBiblioteca();

                if(tf_cpf.getText() != "" && tf_senha.getText() != "") {
                   try {
                       displayBiblioteca.login(event, tf_cpf.getText(), tf_senha.getText());
                   }  catch (InformacaoInvalidaException e) {
                       erro.setText(e.getMessage());
                   }
                } else {
                    erro.setText("*Necessário preencher todos os campos");
                }
            }
        });

    }
}
