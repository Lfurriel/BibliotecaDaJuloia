package furriel.biblioteca.gui.controllers;

import furriel.biblioteca.classes.DisplayBiblioteca;
import furriel.biblioteca.exceptions.InformacaoInvalidaException;
import furriel.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller de tela-inicial.fxml
 */
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
    private Label erro;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_login.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar button_login -> Tenta logar o usuário na biblioteca e se obtiver sucesso muda para
             * a tela de menu usuário ou menu de administrador
             * @param event Botão pressionado
             */
            @Override
            public void handle(ActionEvent event) {
                DisplayBiblioteca displayBiblioteca = DBUtils.getDisplayBiblioteca();

                if(!tf_cpf.getText().equals("") && !tf_senha.getText().equals("")) {
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

        button_cadastrar.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar button_cadastrar -> Muda para a tela de opção de cadastro
             * @param event Botão pressionado
             */
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "op-sign-up.fxml", "Cadastro");
            }
        });

    }
}
