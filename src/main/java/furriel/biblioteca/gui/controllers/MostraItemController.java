package furriel.biblioteca.gui.controllers;

import furriel.biblioteca.classes.Biblioteca;
import furriel.biblioteca.classes.itens.Item;
import furriel.biblioteca.classes.itens.Livro;
import furriel.biblioteca.classes.itens.Revista;
import furriel.biblioteca.classes.itens.CD;
import furriel.biblioteca.classes.usuarios.Administrador;
import furriel.biblioteca.classes.usuarios.Usuario;
import furriel.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller de mostra-itens.fxml
 */
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


    /**
     * Exibe na tela o primeiro item da biblioteca
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Biblioteca biblioteca = DBUtils.getDisplayBiblioteca().getMinhaBiblioteca();
        index = 0;
        if(index < biblioteca.getItens().size())
            setItem(biblioteca.getItens().get(index));
        proximo.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar proximo -> Avança e, se possível, mostra o item na tela
             * @param event Botão pressionado
             */
            @Override
            public void handle(ActionEvent event) {
                index++;
                if(index < biblioteca.getItens().size())
                    setItem(biblioteca.getItens().get(index));
            }
        });

        sair.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar botão sair -> Muda para a tela inicial validando se conta logada é um
             * administrador ou usuário comum
             * @param event
             */
            @Override
            public void handle(ActionEvent event) {
                Usuario contaLogada = biblioteca.getContaLogada();
                if(contaLogada instanceof Administrador)
                    DBUtils.changeScene(event, "menu-super.fxml", "MENU | ADM");
                else
                    DBUtils.changeScene(event, "menu-usuario.fxml", "MENU");
            }
        });
    }

    /**
     * Exibe um item na tela
     * @param item Objeto a ser exibido
     */
    private void setItem(Item item) {
        classe.setText(item.toString());
        id.setText(String.valueOf(item.getId()));
        titulo.setText(item.getTitulo());
        autor.setText(item.getAutor());
        ano.setText(String.valueOf(item.getAnoDePublicacao()));
        
        if(item instanceof Revista) {
            extra1.setText("Número: " + ((Revista) item).getNumero());
            extra2.setText("Volume: " + ((Revista) item).getVolume());
        } else if (item instanceof Livro) {
            extra1.setText("Editora: " + ((Livro) item).getEditora());
            extra2.setText("ISBN: " + ((Livro) item).getIsbn());
        } else {
            extra1.setText("Gravadora: " + ((CD) item).getGravadora());
            extra2.setText("Volume: " + ((CD) item).getVolume());
        }
    }
}
