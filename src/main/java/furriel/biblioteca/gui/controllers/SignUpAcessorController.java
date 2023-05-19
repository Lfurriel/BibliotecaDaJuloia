package furriel.biblioteca.gui.controllers;

import furriel.biblioteca.classes.Biblioteca;
import furriel.biblioteca.classes.usuarios.AcessorTecnico;
import furriel.biblioteca.exceptions.InformacaoInvalidaException;
import furriel.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller de sign-up-administrador.fxml
 */
public class SignUpAcessorController implements Initializable {

    @FXML
    private TextField tf_nome;
    @FXML
    private TextField tf_matricula;
    @FXML
    private TextField tf_cpf;
    @FXML
    private TextField tf_secao;
    @FXML
    private PasswordField tf_senha;
    @FXML
    private Button button_cadastrar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_cadastrar.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar button_cadastrar -> Cadastra um novo Acessor, adiciona a lista de usuários, loga o usuário
             * e ja muda para tela inicial
             * @param actionEvent Botão pressionado
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                if (tf_nome.getText().equals("") || tf_matricula.getText().equals("") || tf_cpf.getText().equals("")
                        || tf_secao.getText().equals("") || tf_senha.getText().equals("")) {
                    // preencher todos os campos
                } else {
                    Biblioteca biblioteca = DBUtils.getDisplayBiblioteca().getMinhaBiblioteca();
                    AcessorTecnico acessor = new AcessorTecnico(tf_nome.getText(), tf_matricula.getText(),
                            tf_cpf.getText(), tf_senha.getText(),
                            tf_secao.getText());
                    try {
                        biblioteca.addUsuario(acessor);
                        biblioteca.setContaLogada(acessor);
                    } catch (InformacaoInvalidaException e) {
                        System.out.println(e.getMessage());
                    }

                    DBUtils.changeScene(actionEvent, "menu-usuario.fxml", "MENU");
                }
            }
        });

    }
}
