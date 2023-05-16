package furriel.biblioteca.interfaces;

import furriel.biblioteca.exceptions.IndisponivelException;
import furriel.biblioteca.exceptions.NaoEmprestadoException;

public interface Emprestavel {
    void emprestarItem() throws IndisponivelException;
    void devolverItem() throws NaoEmprestadoException;
}
