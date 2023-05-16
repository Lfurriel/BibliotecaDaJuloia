package furriel.biblioteca.gui.controllers;

import furriel.biblioteca.classes.Biblioteca;
import furriel.biblioteca.classes.itens.Item;
import furriel.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class BuscaItemController implements Initializable {

    @FXML
    private TextField input;
    @FXML
    private Label classe;

    @FXML
    private Label id;

    @FXML
    private Label titulo;

    @FXML
    private Label ano;

    @FXML
    private Label autor;

    @FXML
    private Label disponibilidade;

    @FXML
    private Label alerta;

    @FXML
    private Button procurar;

    @FXML
    private Button sair;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        procurar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Biblioteca biblioteca = DBUtils.getDisplayBiblioteca().getMinhaBiblioteca();
                if(input.getText().equals("") || input.getText() == null) {
                    setAlerta("Preencha o campo de busca!");
                } else {
                    Item item = biblioteca.buscarItemGUI(input.getText());
                    if (item != null)
                        setItem(item);
                    else
                        setAlerta("Item não encontrado");
                }
            }
        });

        sair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "menu-usuario.fxml", "MENU");
            }
        });
    }

    private void setItem(Item item) {
        alerta.setText("");
        classe.setText(item.toString());
        id.setText(String.valueOf(item.getId()));
        ano.setText(String.valueOf(item.getAnoDePublicacao()));
        autor.setText(item.getAutor());
        if(item.getQuantidadeDisponivel() > 0)
            disponibilidade.setText("Disponível");
        else
            disponibilidade.setText("Indisponível");
        titulo.setText(item.getTitulo());
    }

    private void setAlerta(String msg) {
        alerta.setText(msg);
        classe.setText("");
        id.setText("");
        ano.setText("");
        autor.setText("");
        disponibilidade.setText("");
        titulo.setText("");
    }
}
