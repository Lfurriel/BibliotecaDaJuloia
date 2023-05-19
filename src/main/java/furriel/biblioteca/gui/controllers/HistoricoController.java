package furriel.biblioteca.gui.controllers;

import furriel.biblioteca.classes.Biblioteca;
import furriel.biblioteca.classes.Emprestimo;
import furriel.biblioteca.classes.itens.Item;
import furriel.biblioteca.classes.usuarios.Usuario;
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

/**
 * Controller de historico.fxml
 */
public class HistoricoController implements Initializable {
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
    private Label dataDevolucao;

    @FXML
    private Button proximo;

    @FXML
    private Button sair;

    private static int index;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Biblioteca biblioteca = DBUtils.getDisplayBiblioteca().getMinhaBiblioteca();
        Usuario contaLogada = biblioteca.getContaLogada();
        iniciar(contaLogada);
        proximo.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Ao pressionar o botão próximo -> Avança, se possível, na lista de empréstimos e mostra na tela
             * @param event Botão pressionado
             */
            @Override
            public void handle(ActionEvent event) {
                index ++;
                if (index < biblioteca.getContaLogada().getEmprestimos().size() && contaLogada.getEmprestimos().get(index).isDevolvido())
                    setEmprestimo(biblioteca.getContaLogada().getEmprestimos().get(index));
                else {
                    while (index < contaLogada.getEmprestimos().size() && !contaLogada.getEmprestimos().get(index).isDevolvido()) {
                        index++;
                    }
                    if (index < biblioteca.getContaLogada().getEmprestimos().size() && contaLogada.getEmprestimos().get(index).isDevolvido())
                        setEmprestimo(biblioteca.getContaLogada().getEmprestimos().get(index));
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
     * Busca na lista de histórico o primeiro que já foi devolvido
     * @param contaLogada Conta logada em biblioteca
     */
    private void iniciar(Usuario contaLogada) {
        index = 0;
        while (index < contaLogada.getEmprestimos().size() && !contaLogada.getEmprestimos().get(index).isDevolvido()) {
            index++;
        }
        if (index < contaLogada.getEmprestimos().size() && contaLogada.getEmprestimos().get(index).isDevolvido())
            setEmprestimo(contaLogada.getEmprestimos().get(index));
    }

    /**
     * Printa na tela o objeto de emprestimo
     * @param emprestimo Objeto a ser exibido
     */
    private void setEmprestimo(Emprestimo emprestimo) {

        Item item = emprestimo.getItem();

        classe.setText(item.toString());
        id.setText(String.valueOf(item.getId()));
        titulo.setText(item.getTitulo());
        autor.setText(item.getAutor());

        dataEmprestimo.setText(Utils.converteData(emprestimo.getDataEmprestimo()));
        dataDevolucao.setText(Utils.converteData(emprestimo.getDevolucaoReal()));

    }
}
