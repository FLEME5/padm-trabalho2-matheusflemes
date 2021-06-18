package com.padmtrabalho2.entidade;

import com.padmtrabalho2.dao.DAOFactory;
import com.padmtrabalho2.dao.personagem.PersonagemDAO;

import java.util.List;

public class Personagem {

    private String personagemId;
    private String nome;
    private String nota;
    private String descricao;

    public Personagem() {
        this("", "", "", "");
    }

    public Personagem(String personagemId, String nome, String nota, String descricao) {
        setPersonagemId(personagemId);
        setNome(nome);
        setNota(nota);
        setDescricao(descricao);
    }

    public String getPersonagemId() {
        return personagemId;
    }

    public void setPersonagemId(String personagemId) {
        this.personagemId = personagemId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String paraString() {
        return ("personagemId:" +
                getPersonagemId() +
                " - Nome :" +
                getNome() +
                " - Nota :" +
                getNota() +
                " - Descrição :" +
                getDescricao());
    }

    public boolean incluir() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        PersonagemDAO personagemdao = factory.getPersonagemDAO();
        return personagemdao.incluir(this);
    }

    public int alterar() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        PersonagemDAO personagemdao = factory.getPersonagemDAO();
        return personagemdao.alterar(this);
    }

    public int excluir() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        PersonagemDAO personagemdao = factory.getPersonagemDAO();
        return personagemdao.excluir(this);
    }

    public List aplicarFiltro() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        PersonagemDAO personagemdao = factory.getPersonagemDAO();
        return personagemdao.aplicarFiltro(this);
    }

    public boolean abrir() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        PersonagemDAO personagemdao = factory.getPersonagemDAO();
        List lista = personagemdao.aplicarFiltro(this);
        if (!lista.isEmpty()) {
            Personagem oPersonagem = (Personagem) lista.iterator().next();
            setNome(oPersonagem.getNome());
            setNota(oPersonagem.getNota());
            setDescricao(oPersonagem.getDescricao());
            return true;
        } else {
            return false;
        }
    }

    public void apagarTabela() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        PersonagemDAO personagemdao = factory.getPersonagemDAO();
        personagemdao.apagarTabela();
    }

    public long getNumeroRegistros() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        PersonagemDAO personagemdao = factory.getPersonagemDAO();
        return personagemdao.getNumeroRegistros();
    }
}
