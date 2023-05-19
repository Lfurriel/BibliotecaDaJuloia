package furriel.biblioteca.interfaces;

import furriel.biblioteca.exceptions.IndisponivelException;
import furriel.biblioteca.exceptions.NaoEmprestadoException;

/**
 * Interface que empresta/devolve um item. Implementada na classe Item
 */
public interface Emprestavel {
    void emprestarItem() throws IndisponivelException;
    void devolverItem() throws NaoEmprestadoException;
}
