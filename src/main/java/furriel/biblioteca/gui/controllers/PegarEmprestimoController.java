package furriel.biblioteca.gui.controllers;

import furriel.biblioteca.classes.Biblioteca;
import furriel.biblioteca.classes.itens.Item;
import furriel.biblioteca.exceptions.IndisponivelException;
import furriel.biblioteca.exceptions.InformacaoInvalidaException;
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

public class PegarEmprestimoController implements Initializable {

    @FXML
    private TextField input;
    @FXML
    private TextField dia;
    @FXML
    private TextField mes;
    @FXML
    private TextField ano;
    @FXML
    private Label alerta;
    @FXML
    private Button ok;
    @FXML
    private Button sair;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Biblioteca biblioteca = DBUtils.getDisplayBiblioteca().getMinhaBiblioteca();
                if(input.getText().equals("") && dia.getText().equals("") && mes.getText().equals("") && ano.getText().equals("")) {
                    alerta.setText("Preencha os campos");
                } else {
                    Item item = biblioteca.buscarItemGUI(input.getText());
                    if (item == null) {
                        alerta.setText("Item não encontrado");
                    } else {
                        try {
                            biblioteca.adicionarEmprestimoGUI(item, dia.getText(), mes.getText(), ano.getText());
                            item.emprestarItem();
                            input.setText("");
                            dia.setText("");
                            mes.setText("");
                            ano.setText("");
                            alerta.setText("OBRIGADO!");
                        } catch (InformacaoInvalidaException | IndisponivelException e) {
                            alerta.setText(e.getMessage());
                        }
                    }
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
}
