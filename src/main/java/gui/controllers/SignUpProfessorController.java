package gui.controllers;

import classes.Biblioteca;
import classes.usuarios.Aluno;
import classes.usuarios.Professor;
import gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpProfessorController implements Initializable {

    @FXML
    private TextField tf_nome;
    @FXML
    private TextField tf_matricula;
    @FXML
    private TextField tf_cpf;
    @FXML
    private TextField tf_departamento;
    @FXML
    private TextField tf_titulacao;
    @FXML
    private PasswordField tf_senha;
    @FXML
    private Button button_cadastrar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_cadastrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(tf_nome.getText().equals("") || tf_matricula.getText().equals("") || tf_cpf.getText().equals("")
                        || tf_departamento.getText().equals("") || tf_titulacao.getText().equals("") || tf_senha.getText().equals("")) {
                    // preencher todos os campos
                } else {
                    Biblioteca biblioteca = DBUtils.getDisplayBiblioteca().getMinhaBiblioteca();
                    Professor professor = new Professor(tf_nome.getText(), tf_matricula.getText(),
                            tf_cpf.getText(), tf_senha.getText(),
                            tf_departamento.getText(), tf_titulacao.getText());
                    biblioteca.addUsuario(professor);
                    biblioteca.setContaLogada(professor);
                    DBUtils.changeScene(actionEvent, "menu-usuario.fxml", "MENU");
                }
            }
        });

    }
}
