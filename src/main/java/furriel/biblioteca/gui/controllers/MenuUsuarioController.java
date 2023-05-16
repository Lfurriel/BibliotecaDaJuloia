package furriel.biblioteca.gui.controllers;

import furriel.biblioteca.classes.Biblioteca;
import furriel.biblioteca.classes.DisplayBiblioteca;
import furriel.biblioteca.classes.Emprestimo;
import furriel.biblioteca.gui.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuUsuarioController implements Initializable {

    @FXML
    private Button button_op1;

    @FXML
    private Button button_op2;

    @FXML
    private Button button_op3;

    @FXML
    private Button button_op4;

    @FXML
    private Button button_op5;

    @FXML
    private Button button_op6;

    @FXML
    private Button button_op7;

    @FXML
    private Button button_op8;

    @FXML
    private Button button_sair;

    @FXML
    private Label alerta;

    private ToggleGroup toggleGroup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_op1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "mostra-itens.fxml", "Itens");
            }
        });
        button_op2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DisplayBiblioteca displayBiblioteca = DBUtils.getDisplayBiblioteca();
                //TODO: Item específico
            }
        });
        button_op3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DisplayBiblioteca displayBiblioteca = DBUtils.getDisplayBiblioteca();
                //TODO: Pagar Multa
            }
        });
        button_op4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DisplayBiblioteca displayBiblioteca = DBUtils.getDisplayBiblioteca();
                //TODO: Pegar Empréstimo
            }
        });
        button_op5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DisplayBiblioteca displayBiblioteca = DBUtils.getDisplayBiblioteca();
                //TODO: Devolver
            }
        });
        button_op6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Biblioteca biblioteca = DBUtils.getDisplayBiblioteca().getMinhaBiblioteca();
                boolean temEmrestimo = false;
                for(Emprestimo e : biblioteca.getContaLogada().getEmprestimos()) {
                    if(!e.isDevolvido()) {
                        temEmrestimo = true;
                        break;
                    }
                }

                if(temEmrestimo)
                    DBUtils.changeScene(event, "listar-emprestimos.fxml", "Emprestimos");
                else
                    alerta.setText("SEM EMPRÉSTIMOS");

            }
        });
        button_op7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DisplayBiblioteca displayBiblioteca = DBUtils.getDisplayBiblioteca();
                //TODO: Estender Emprestimos
            }
        });
        button_op8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Biblioteca biblioteca = DBUtils.getDisplayBiblioteca().getMinhaBiblioteca();
                boolean temDevolvido = false;
                for(Emprestimo e : biblioteca.getContaLogada().getEmprestimos()) {
                    if(e.isDevolvido()) {
                        temDevolvido = true;
                        break;
                    }
                }

                if(temDevolvido)
                    DBUtils.changeScene(event, "historico.fxml", "Emprestimos");
                else
                    alerta.setText("SEM DEVOLUÇÃO");

            }
        });
        button_sair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DisplayBiblioteca displayBiblioteca = DBUtils.getDisplayBiblioteca();
                displayBiblioteca.deslogarGUI();
                DBUtils.changeScene(actionEvent, "tela-inicial.fxml", "Tela Inicial");
            }
        });
    }


}
