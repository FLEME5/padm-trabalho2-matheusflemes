package com.padmtrabalho2.dao.personagem;

public interface SQLitePersonagemMetaDados {

    public static final String TABLE = "PERSONAGEM";

    public static final String[] PK = {"PERSONAGEMID"};

    public static String[] METADADOSUPDATE = {"PERSONAGEMID", "NOME", "NOTA", "DESCRICAO"};

    public static String METADADOSSELECT
            = TABLE + ".PERSONAGEMID, "
            + TABLE + ".NOME, "
            + TABLE + ".NOTA, "
            + TABLE + ".DESCRICAO";

    public static String METADADOSCREATE
            = "create table IF NOT EXISTS " + TABLE + " " +
            "(" + PK[0] + " integer, " +
            "NOME varchar(100), " +
            "NOTA varchar(2), " +
            "DESCRICAO varchar(200), " +
            "CONSTRAINT PK_PERSONAGEM PRIMARY KEY (" + PK[0] + "))";
}
