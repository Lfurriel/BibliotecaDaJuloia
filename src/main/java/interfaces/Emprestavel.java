package interfaces;

import exceptions.IndisponivelException;
import exceptions.NaoEmprestadoException;

public interface Emprestavel {
    void emprestarItem() throws IndisponivelException;
    void devolverItem() throws NaoEmprestadoException;
}
