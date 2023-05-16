package furriel.biblioteca.gui.controllers;

import furriel.biblioteca.classes.Biblioteca;
import furriel.biblioteca.classes.Emprestimo;
import furriel.biblioteca.classes.itens.Item;
import furriel.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import furriel.biblioteca.utils.Utils;

import java.net.URL;
import java.util.ResourceBundle;

public class ListarEmprestimosController implements Initializable {

    @FXML
    private Label classe;

    @FXML
    private Label id;

    @FXML
    private Label titulo;

    @FXML
    private Label autor;

    @FXML
    private Label dataEmprestimo;

    @FXML
    private Label dataPrevisao;

    @FXML
    private Button proximo;

    @FXML
    private Button sair;

    private static int index;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Biblioteca biblioteca = DBUtils.getDisplayBiblioteca().getMinhaBiblioteca();
        index = 0;
        if(index <=biblioteca.getContaLogada().getEmprestimos().size())
            setEmprestimo(biblioteca.getContaLogada().getEmprestimos().get(index));
        proximo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                do {
                    index++;
                } while (biblioteca.getContaLogada().getEmprestimos().get(index).isDevolvido());

                if(index <=biblioteca.getContaLogada().getEmprestimos().size())
                    setEmprestimo(biblioteca.getContaLogada().getEmprestimos().get(index));
            }
        });

        sair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "menu-usuario.fxml", "MENU");
            }
        });
    }

    private void setEmprestimo(Emprestimo emprestimo) {

        Item item = emprestimo.getItem();

        classe.setText(item.toString());
        id.setText(String.valueOf(item.getId()));
        titulo.setText(item.getTitulo());
        autor.setText(item.getAutor());

        dataEmprestimo.setText(Utils.converteData(emprestimo.getDataEmprestimo()));
        dataPrevisao.setText(Utils.converteData(emprestimo.getDevolucaoPrevista()));

    }
}
