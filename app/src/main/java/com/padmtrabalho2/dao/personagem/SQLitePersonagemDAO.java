package com.padmtrabalho2.dao.personagem;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.padmtrabalho2.dao.SQLiteBDHelper;
import com.padmtrabalho2.dao.SQLiteDAOFactory;
import com.padmtrabalho2.entidade.Personagem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SQLitePersonagemDAO extends SQLiteDAOFactory implements PersonagemDAO, SQLitePersonagemMetaDados {

    private SQLiteBDHelper dbHelper;

    public SQLitePersonagemDAO() {
        dbHelper = new SQLiteBDHelper(getContext());
    }

    private List select(List<String> filtros, String ordem) {
        List lista = new LinkedList();
        String[] colunas = METADADOSSELECT.split(",");
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = dbHelper.getReadableDatabase();
            cursor = db.query(TABLE, colunas, montaFiltro(filtros, " and "), null, null, null, ordem, null);
            while (cursor.moveToNext()) {
                Personagem personagem = new Personagem();
                personagem.setPersonagemId(cursor.getString(cursor.getColumnIndex(PK[0])));
                personagem.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
                personagem.setNota(cursor.getString(cursor.getColumnIndex("NOTA")));
                personagem.setDescricao(cursor.getString(cursor.getColumnIndex("DESCRICAO")));
                lista.add(personagem);
            }
            cursor.close();
            cursor = null;
            db.close();
            db = null;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e) {
                    ;
                }
                cursor = null;
            }
            if (db != null) {
                try {
                    db.close();
                } catch (Exception e) {
                    ;
                }
                db = null;
            }
        }
        return lista;
    }

    public boolean incluir(Object obj) {
        if (obj != null) {
            Personagem personagem = (Personagem) obj;
            SQLiteDatabase db = null;
            boolean res = false;
            try {
                db = dbHelper.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(METADADOSUPDATE[0], preparaSQL(personagem.getPersonagemId()));
                valores.put(METADADOSUPDATE[1], preparaSQL(personagem.getNome()));
                valores.put(METADADOSUPDATE[2], preparaSQL(personagem.getNota()));
                valores.put(METADADOSUPDATE[3], preparaSQL(personagem.getDescricao()));
                db.insert(TABLE, null, valores);
                db.close();
                db = null;
                res = true;
            } catch (Exception e) {
                System.out.println(e);
                res = false;
            } finally {
                if (db != null) {
                    try {
                        db.close();
                    } catch (Exception e) {
                        ;
                    }
                    db = null;
                }
            }
            return res;
        }
        return false;
    }

    public int alterar(Object obj) {
        if (obj != null) {
            Personagem personagem = (Personagem) obj;
            SQLiteDatabase db = null;
            int res = 0;
            try {
                db = dbHelper.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(METADADOSUPDATE[0], preparaSQL(personagem.getPersonagemId()));
                valores.put(METADADOSUPDATE[1], preparaSQL(personagem.getNome()));
                valores.put(METADADOSUPDATE[2], preparaSQL(personagem.getNota()));
                valores.put(METADADOSUPDATE[3], preparaSQL(personagem.getDescricao()));
                String selecao = PK[0] + " = ? ";
                String[] selecaoArgumentos = {preparaSQL(personagem.getPersonagemId())};
                db.update(TABLE, valores, selecao, selecaoArgumentos);
                db.close();
                db = null;
                res = 1;
            } catch (Exception e) {
                System.out.println(e);
                res = 0;
            } finally {
                if (db != null) {
                    try {
                        db.close();
                    } catch (Exception e) {
                        ;
                    }
                    db = null;
                }
            }
            return res;
        }
        return 0;
    }

    public int excluir(Object obj) {
        if (obj != null) {
            Personagem personagem = (Personagem) obj;
            SQLiteDatabase db = null;
            StringBuilder sql = new StringBuilder();
            int res = 0;
            try {
                db = dbHelper.getWritableDatabase();
                String selecao = PK[0] + " = ? ";
                String[] selecaoArgumentos = {preparaSQL(personagem.getPersonagemId())};
                db.delete(TABLE, selecao, selecaoArgumentos);
                db.close();
                db = null;
                res = 1;
            } catch (Exception e) {
                System.out.println(e);
                res = 0;
            } finally {
                if (db != null) {
                    try {
                        db.close();
                    } catch (Exception e) {
                        ;
                    }
                    db = null;
                }
            }
            return res;
        }
        return 0;
    }

    public List aplicarFiltro(Object obj) {
        if (obj != null) {
            Personagem personagem = (Personagem) obj;

            List<String> filtros = new ArrayList<String>();

            if (personagem.getPersonagemId() != "") {
                filtros.add(TABLE + "." + PK[0] + "= '" + personagem.getPersonagemId() + "'");
            }

            if (!personagem.getNome().equals("")) {
                filtros.add(TABLE + ".NOME like '" + personagem.getNome() + "'");
            }

            if (!personagem.getNota().equals("")) {
                filtros.add(TABLE + ".NOTA like '" + personagem.getNota() + "'");
            }

            if (!personagem.getDescricao().equals("")) {
                filtros.add(TABLE + ".DESCRICAO like '" + personagem.getDescricao() + "'");
            }

            return select(filtros, PK[0]);
        } else {
            return null;
        }
    }

    public void apagarTabela() {
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();
            db.delete(TABLE, null, null);
            db.close();
            db = null;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (db != null) {
                try {
                    db.close();
                } catch (Exception e) {
                    ;
                }
                db = null;
            }
        }
    }

    public long getNumeroRegistros() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return DatabaseUtils.queryNumEntries(db, TABLE);
    }
}
