package com.padmtrabalho2.dao.personagem;

import java.util.List;

public interface PersonagemDAO {

    public boolean incluir(Object obj);

    public int alterar(Object obj);

    public int excluir(Object obj);

    public List aplicarFiltro(Object obj);

    public void apagarTabela();

    public long getNumeroRegistros();
}
