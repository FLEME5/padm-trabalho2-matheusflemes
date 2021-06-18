package com.padmtrabalho2.dao;

import android.content.Context;

import com.padmtrabalho2.dao.personagem.PersonagemDAO;

public abstract class DAOFactory {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        DAOFactory.context = context;
    }

    public static DAOFactory getDAOFactory() {
        return new SQLiteDAOFactory();
    }

    public abstract PersonagemDAO getPersonagemDAO();

}
