package furriel.biblioteca.gui.controllers;

import furriel.biblioteca.classes.Biblioteca;
import furriel.biblioteca.classes.itens.Item;
import furriel.biblioteca.classes.itens.Livro;
import furriel.biblioteca.classes.itens.Revista;
import furriel.biblioteca.classes.itens.CD;
import furriel.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MostraItemController implements Initializable {

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
        if(index <=biblioteca.getItens().size())
            setItem(biblioteca.getItens().get(index));
        proximo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                index++;
                if(index <=biblioteca.getItens().size())
                    setItem(biblioteca.getItens().get(index));
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
        classe.setText(item.toString());
        id.setText(String.valueOf(item.getId()));
        titulo.setText(item.getTitulo());
        autor.setText(item.getAutor());
        ano.setText(String.valueOf(item.getAnoDePublicacao()));
        
        if(item instanceof Revista) {
            extra1.setText(String.valueOf(((Revista) item).getNumero()));
            extra1.setText(String.valueOf(((Revista) item).getVolume()));
        } else if (item instanceof Livro) {
            extra1.setText(((Livro) item).getEditora());
            extra1.setText(((Livro) item).getIsbn());
        } else {
            extra1.setText(((CD) item).getGravadora());
            extra1.setText(String.valueOf(((CD) item).getVolume()));
        }
    }
}
