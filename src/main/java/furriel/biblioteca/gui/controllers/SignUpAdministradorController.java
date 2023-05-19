package furriel.biblioteca.gui.controllers;

import furriel.biblioteca.classes.Biblioteca;
import furriel.biblioteca.classes.usuarios.AcessorTecnico;
import furriel.biblioteca.classes.usuarios.Administrador;
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
 * Controller de sign-up-administrador.fxml
 */
public class SignUpAdministradorController implements Initializable {

    @FXML
    private TextField tf_nome;
    @FXML
    private TextField tf_matricula;
    @FXML
    private TextField tf_cpf;
    @FXML
    private TextField chave;
    @FXML
    private PasswordField tf_senha;
    @FXML
    private Button button_cadastrar;
    @FXML
    private Label alerta;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_cadastrar.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar button_cadastrar -> Cadastra um novo Administrador, adiciona a lista de usuários, loga o usuário
             * e ja muda para tela inicial
             * @param actionEvent Botão pressionado
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                if (tf_nome.getText().equals("") || tf_matricula.getText().equals("") || tf_cpf.getText().equals("")
                        || chave.getText().equals("") || tf_senha.getText().equals("")) {
                    alerta.setText("*Preencha todos os campos");
                } else {
                    Biblioteca biblioteca = DBUtils.getDisplayBiblioteca().getMinhaBiblioteca();

                    try {
                        biblioteca.addAdm(new Administrador(tf_nome.getText(), tf_matricula.getText(),
                                tf_cpf.getText(), tf_senha.getText()), chave.getText());
                        biblioteca.setContaLogada(biblioteca.getContaLogada());
                        DBUtils.changeScene(actionEvent, "menu-super.fxml", "MENU | ADM");
                    } catch (InformacaoInvalidaException e) {
                        alerta.setText(e.getMessage());
                    }
                }
            }
        });
    }
}
