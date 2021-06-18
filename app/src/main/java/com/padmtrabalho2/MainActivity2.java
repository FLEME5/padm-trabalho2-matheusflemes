package com.padmtrabalho2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.padmtrabalho2.dao.DAOFactory;
import com.padmtrabalho2.entidade.Personagem;
import com.padmtrabalho2.utils.CustomMovementMethod;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private Button buttonEditarLista;
    private Button buttonVoltar;
    private EditText EditTextListaPersonagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        buttonEditarLista = findViewById(R.id.buttonEditarLista);
        buttonVoltar = findViewById(R.id.buttonVoltar);
        EditTextListaPersonagens = findViewById(R.id.EditTextListaPersonagens);
        DAOFactory.setContext(getApplicationContext());
        Personagem personagem = new Personagem();
        List lista = personagem.aplicarFiltro();
        String saida = "ID do Personagem - Nome - Nota - Descrição - Link da wiki epic7x\n\n";
        for (int i = 0; i < lista.size(); i++) {
            Personagem persona = (Personagem) lista.get(i);
            saida = saida +
                    persona.getPersonagemId() +
                    " - " + persona.getNome() +
                    " - " + persona.getNota() +
                    " - " + persona.getDescricao() +
                    " - https://epic7x.com/character/" +
                    persona.getNome().replaceAll(" ", "-") + "\n\n";
        }
        EditTextListaPersonagens.setText(saida);
        EditTextListaPersonagens.setLinksClickable(true);
        EditTextListaPersonagens.setAutoLinkMask(Linkify.WEB_URLS);
        EditTextListaPersonagens.setMovementMethod(CustomMovementMethod.getInstance());
        Linkify.addLinks(EditTextListaPersonagens, Linkify.WEB_URLS);
    }

    @Override
    public void onResume(){
        super.onResume();
        DAOFactory.setContext(getApplicationContext());
        Personagem personagem = new Personagem();
        List lista = personagem.aplicarFiltro();
        String saida = "ID do Personagem - Nome - Nota - Descrição - Link da wiki epic7x\n\n";
        for (int i = 0; i < lista.size(); i++) {
            Personagem persona = (Personagem) lista.get(i);
            saida = saida +
                    persona.getPersonagemId() +
                    " - " + persona.getNome() +
                    " - " + persona.getNota() +
                    " - " + persona.getDescricao() +
                    " - https://epic7x.com/character/" +
                    persona.getNome().replaceAll(" ", "-") + "\n\n";
        }
        EditTextListaPersonagens.setText(saida);
        EditTextListaPersonagens.setLinksClickable(true);
        EditTextListaPersonagens.setAutoLinkMask(Linkify.WEB_URLS);
        EditTextListaPersonagens.setMovementMethod(CustomMovementMethod.getInstance());
        Linkify.addLinks(EditTextListaPersonagens, Linkify.WEB_URLS);
    }

    public void onClickButtonEditarLista(View v) {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivityForResult(intent, 0);
    }
    public void onClickBotaoVoltar(View v) {
        System.exit(0);
    }

}