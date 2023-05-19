package furriel.biblioteca.gui.controllers;

import furriel.biblioteca.classes.Biblioteca;
import furriel.biblioteca.classes.usuarios.*;
import furriel.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ListarUsuariosController implements Initializable {
    @FXML
    private Label tipo;

    @FXML
    private Label nome;

    @FXML
    private Label matricula;

    @FXML
    private Label cpf;

    @FXML
    private Label extra1;

    @FXML
    private Label extra2;

    @FXML
    private Button proximo;

    @FXML
    private Button sair;

    private static int index;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Biblioteca biblioteca = DBUtils.getDisplayBiblioteca().getMinhaBiblioteca();
        index = 0;
        if (index < biblioteca.getUsuarios().size())
            setItem(biblioteca.getUsuarios().get(index));
        proximo.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar o botão proximo -> Avança, se possível, na lista de usuários e printa na tela
             * @param event Botão pressionado
             */
            @Override
            public void handle(ActionEvent event) {
                index++;
                if (index < biblioteca.getUsuarios().size())
                    setItem(biblioteca.getUsuarios().get(index));
            }
        });

        sair.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar o botão sair -> volta para a tela inicial
             * @param event Botão pressionado
             */
            @Override
            public void handle(ActionEvent event) {
                Usuario contaLogada = biblioteca.getContaLogada();
                if (contaLogada instanceof Administrador)
                    DBUtils.changeScene(event, "menu-super.fxml", "MENU | ADM");
                else
                    DBUtils.changeScene(event, "menu-usuario.fxml", "MENU");
            }
        });
    }

    /**
     * Mostra na tela um usuário
     * @param usuario Objeto a ser exibido
     */
    private void setItem(Usuario usuario) {
        tipo.setText(usuario.toString());
        nome.setText(usuario.getNome());
        matricula.setText(usuario.getMatricula());
        cpf.setText(usuario.getCpf());

        if(usuario instanceof Aluno) {
            extra1.setText(((Aluno) usuario).getCurso());
            extra2.setText(((Aluno) usuario).getPeriodo());
        } else if (usuario instanceof Professor) {
            extra1.setText(((Professor) usuario).getDepartamento());
            extra2.setText(((Professor) usuario).getTitulacao());
        } else if (usuario instanceof AcessorTecnico) {
            extra1.setText(((AcessorTecnico) usuario).getSecao());
            extra2.setText("");
        } else {
            extra1.setText("");
            extra2.setText("");
        }
    }
}
