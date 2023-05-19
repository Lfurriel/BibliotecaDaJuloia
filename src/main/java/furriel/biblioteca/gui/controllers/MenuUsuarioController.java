package furriel.biblioteca.gui.controllers;

import furriel.biblioteca.classes.Biblioteca;
import furriel.biblioteca.classes.DisplayBiblioteca;
import furriel.biblioteca.classes.Emprestimo;
import furriel.biblioteca.classes.usuarios.Usuario;
import furriel.biblioteca.gui.DBUtils;
import furriel.biblioteca.utils.Utils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Controller de menu-usuario.fxml
 */
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_op1.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar button_op1 -> Muda para a tela de listagem de itens
             * @param event Botão pressionado
             */
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "mostra-itens.fxml", "Itens");
            }
        });
        button_op2.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar button_op2 -> Muda para a tela de busca de item
             * @param event Botão pressionado
             */
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "busca-item.fxml", "Buscar item");
            }
        });
        button_op3.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar button_op3 -> Muda para a tela de pagar multa ou exibe na tela caso conta logada não possua uma multa
             * @param event Botão pressionado
             */
            @Override
            public void handle(ActionEvent event) {
                Biblioteca biblioteca = DBUtils.getDisplayBiblioteca().getMinhaBiblioteca();
                if (biblioteca.getContaLogada().getMulta() > 0)
                    DBUtils.changeScene(event, "pagar-multa.fxml", "Pagar multa");
                else
                    alerta.setText("SEM MULTA!");
            }
        });
        button_op4.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar button_op4 -> Checa a existência de multa, caso não possua, muda para a tela de empréstimo
             * @param event Botão pressionado
             */
            @Override
            public void handle(ActionEvent event) {
                Usuario contaLogada = DBUtils.getDisplayBiblioteca().getMinhaBiblioteca().getContaLogada();
                if(contaLogada.getMulta() > 0)
                    alerta.setText("MULTA PENDENTE");
                else
                    DBUtils.changeScene(event, "pegar-emprestimo.fxml", "Emprestimo");
            }
        });
        button_op5.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar button_op5 -> Muda para a tela de devolução
             * @param event Botão pressionado
             */
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "devolver-item.fxml", "Devolução");
            }
        });
        button_op6.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar button_op6 -> Valida a existência de um empréstimo não devolvido, caso possua muda para tela de listagem de empréstimos
             * pendentes, caso contrário exibe um alerta na tela
             * @param event Botão pressionado
             */
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
            /**
             * Ao pressionar button_op7 -> Valida a existência de um empréstimo não devolvido, caso possua chama função de estender empréstimo, caso
             * contrário exibe um alerta na tela
             * @param event Botão pressionado
             */
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

                if(temEmrestimo) {
                    estenderEmrprestimos(biblioteca);
                    alerta.setText("ESTENDIDO!");
                } else
                    alerta.setText("SEM EMPRÉSTIMOS");


            }
        });
        button_op8.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar button_op8 -> Valida a existência de um empréstimo devolvido, caso possua muda para tela de listagem de empréstimos
             * devolvidos, caso contrário exibe um alerta na tela
             * @param event Botão pressionado
             */
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
            /**
             * Desloga o usuário e volta para tela de login
             * @param actionEvent Botão pressionado
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                DisplayBiblioteca displayBiblioteca = DBUtils.getDisplayBiblioteca();
                displayBiblioteca.deslogarGUI();
                DBUtils.changeScene(actionEvent, "tela-inicial.fxml", "Tela Inicial");
            }
        });
    }

    /**
     * Passa pela lista de emprestimos do usuário logado adicionando um mês em cada data de devolução prevista
     * @param biblioteca Objeto da biblioteca
     */
    private void estenderEmrprestimos(Biblioteca biblioteca) {
        Usuario contaLogada = biblioteca.getContaLogada();
        for (Emprestimo e : contaLogada.getEmprestimos()) {
            if (!e.isDevolvido()) {
                Date novaData = Utils.calcularDiaDepoisDeUmMes(e.getDevolucaoPrevista());
                e.setDevolucaoPrevista(novaData);
            }
        }
    }


}
