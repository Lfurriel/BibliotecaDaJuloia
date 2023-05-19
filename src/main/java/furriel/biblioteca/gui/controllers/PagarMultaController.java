package furriel.biblioteca.gui.controllers;

import furriel.biblioteca.classes.Biblioteca;
import furriel.biblioteca.exceptions.InformacaoInvalidaException;
import furriel.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller de pagar-multa.fxml
 */
public class PagarMultaController implements Initializable {

    @FXML
    private Label valor;
    @FXML
    private PasswordField senha;
    @FXML
    private Label alerta;
    @FXML
    private Button sair;
    @FXML
    private Button ok;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Biblioteca biblioteca = DBUtils.getDisplayBiblioteca().getMinhaBiblioteca();
        valor.setText("R$" + biblioteca.getContaLogada().getMulta());
        ok.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar botão ok -> valida a senha do usuário e zera a multa da conta logada
             * @param event Botão pressionado
             */
            @Override
            public void handle(ActionEvent event) {
                Biblioteca biblioteca = DBUtils.getDisplayBiblioteca().getMinhaBiblioteca();
                if (senha.getText().equals("")) {
                    alerta.setText("* insira sua senha!");
                } else {
                    try {
                        biblioteca.pagarMultaGUI(senha.getText());
                        alerta.setText("MULTA PAGA COM SUCESSO!");
                        valor.setText("R$" + biblioteca.getContaLogada().getMulta());
                    } catch (InformacaoInvalidaException e) {
                        alerta.setText("* " + e.getMessage());
                    }
                }
            }
        });

        sair.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar botão sair -> volta para a tela de usuário
             * @param event Botão pressionado
             */
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "menu-usuario.fxml", "MENU");
            }
        });

    }
}
