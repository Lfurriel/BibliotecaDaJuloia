package furriel.biblioteca.gui.controllers;

import furriel.biblioteca.classes.Biblioteca;
import furriel.biblioteca.classes.Emprestimo;
import furriel.biblioteca.classes.itens.Item;
import furriel.biblioteca.classes.usuarios.Usuario;
import furriel.biblioteca.exceptions.InformacaoInvalidaException;
import furriel.biblioteca.exceptions.NaoEmprestadoException;
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

/**
 * Controller de devolver-item.fxml
 */
public class DevolverItemController implements Initializable {

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
    private Label alerta;
    @FXML
    private Button procurar;
    @FXML
    private Button sair;
    @FXML
    private Button devolver;
    @FXML
    private TextField dia;
    @FXML
    private TextField mes;
    @FXML
    private TextField tf_ano;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        alerta.setText("");
        classe.setText("");
        id.setText("");
        ano.setText("");
        autor.setText("");
        titulo.setText("");

        procurar.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar o botão procurar -> Procura na lista de Emprestimos um da lista do usuário logado e printa
             * na tela caso encontrado
             * @param event Botão pressionado
             */
            @Override
            public void handle(ActionEvent event) {
                Biblioteca biblioteca = DBUtils.getDisplayBiblioteca().getMinhaBiblioteca();
                if(input.getText().equals("") || input.getText() == null) {
                    setAlerta("Preencha o campo de busca!");
                } else {
                    try {
                        Emprestimo emprestimo = biblioteca.buscaEmprestimoGUI(input.getText());
                        setItem(emprestimo.getItem());
                    } catch (NaoEmprestadoException e) {
                        setAlerta(e.getMessage());
                    }
                }
            }
        });

        devolver.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar o botão devolver -> Procura na lista de Emprestimos um da lista do usuário logado e
             * printa na tela caso encontrado e devolve o item
             * @param event Botão pressionado
             */
            @Override
            public void handle(ActionEvent event) {
                Biblioteca biblioteca = DBUtils.getDisplayBiblioteca().getMinhaBiblioteca();
                Usuario contaLogada = biblioteca.getContaLogada();
                if(input.getText().equals("") || dia.getText().equals("") || mes.getText().equals("") || tf_ano.getText().equals("")) {
                    setAlerta("Preencha o campo de busca e data!");
                } else {
                    try {
                        Emprestimo emprestimo = biblioteca.buscaEmprestimoGUI(input.getText());
                        biblioteca.devolverGUI(emprestimo, dia.getText(), mes.getText(), tf_ano.getText());
                        double multa = contaLogada.verificaMulta(emprestimo);
                        if(multa > 0)
                            contaLogada.setMulta(contaLogada.getMulta() + multa);
                        setAlerta("Devolvido!");
                    } catch (NaoEmprestadoException | InformacaoInvalidaException e) {
                        setAlerta(e.getMessage());
                    }
                }
            }
        });

        sair.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar o botão sair -> volta para a tela inicial
             * @param event Botão pressionado
             */
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "menu-usuario.fxml", "MENU");
            }
        });
    }

    /**
     * Mostra o item na tela
     * @param item Iem a ser exibido
     */
    private void setItem(Item item) {
        alerta.setText("");
        classe.setText(item.toString());
        id.setText(String.valueOf(item.getId()));
        ano.setText(String.valueOf(item.getAnoDePublicacao()));
        autor.setText(item.getAutor());
        titulo.setText(item.getTitulo());
    }

    /**
     * Mostra uma mensagem na tela
     * @param msg mensagem de erro
     */
    private void setAlerta(String msg) {
        alerta.setText(msg);
        classe.setText("");
        id.setText("");
        ano.setText("");
        autor.setText("");
        titulo.setText("");
    }
}
