package com.padmtrabalho2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.padmtrabalho2.dao.DAOFactory;
import com.padmtrabalho2.entidade.Personagem;

public class MainActivity3 extends AppCompatActivity {
    private EditText EditTextPersonagemId;
    private EditText EditTextNome;
    private EditText EditTextNota;
    private EditText EditTextDescricao;
    private TextView TextViewRegistros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        EditTextPersonagemId = findViewById(R.id.EditTextPersonagemId);
        EditTextNome = findViewById(R.id.EditTextNome);
        EditTextNota = findViewById(R.id.EditTextNota);
        EditTextDescricao = findViewById(R.id.EditTextDesc);
        TextViewRegistros = findViewById(R.id.TextViewRegistros);

        DAOFactory.setContext(getApplicationContext());
        atualizaRegistros();
    }

    public void onClickIncluir(View v) {
        if (!EditTextPersonagemId.getText().toString().equals("")) {
            Personagem personagem = new Personagem();
            personagem.setPersonagemId(EditTextPersonagemId.getText().toString());
            personagem.setNome(EditTextNome.getText().toString());
            personagem.setNota(EditTextNota.getText().toString());
            personagem.setDescricao(EditTextDescricao.getText().toString());
            boolean resultado = personagem.incluir();
            if (resultado == true) {
                Toast.makeText(MainActivity3.this, "Inclusão realizada com sucesso!", Toast.LENGTH_SHORT).show();
                atualizaRegistros();
            } else {
                Toast.makeText(MainActivity3.this, "Inclusão não realizada!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity3.this, "Digite um ID de personagem!", Toast.LENGTH_SHORT).show();
            EditTextPersonagemId.requestFocus();
        }
    }

    public void onClickAlterar(View v) {
        if (!EditTextPersonagemId.getText().toString().equals("")) {
            Personagem personagem = new Personagem();
            personagem.setPersonagemId(EditTextPersonagemId.getText().toString());
            boolean resultadoConsulta = personagem.abrir();
            if (resultadoConsulta == true) {
                personagem.setNome(EditTextNome.getText().toString());
                personagem.setNota(EditTextNota.getText().toString());
                personagem.setDescricao(EditTextDescricao.getText().toString());
                int resultadoAlteracao = personagem.alterar();
                if (resultadoAlteracao != 0) {
                    Toast.makeText(MainActivity3.this, "Alteração realizada com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity3.this, "Alteração não realizada!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity3.this, "Personagem não encontrado, digite um ID de personagem válido!", Toast.LENGTH_SHORT).show();
                EditTextPersonagemId.requestFocus();
            }
        } else {
            Toast.makeText(MainActivity3.this, "Digite um ID de personagem!", Toast.LENGTH_SHORT).show();
            EditTextPersonagemId.requestFocus();
        }

    }

    public void onClickConsultar(View v) {
        if (!EditTextPersonagemId.getText().toString().equals("")) {
            Personagem personagem = new Personagem();
            personagem.setPersonagemId(EditTextPersonagemId.getText().toString());
            boolean resultadoConsulta = personagem.abrir();
            if (resultadoConsulta == true) {
                EditTextNome.setText(personagem.getNome());
                EditTextNota.setText(personagem.getNota());
                EditTextDescricao.setText(personagem.getDescricao());
                Toast.makeText(MainActivity3.this, "Personagem encontrado!", Toast.LENGTH_SHORT).show();
                EditTextPersonagemId.requestFocus();
            } else {
                Toast.makeText(MainActivity3.this, "Personagem não encontrado!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity3.this, "Digite um ID de personagem!", Toast.LENGTH_SHORT).show();
            EditTextPersonagemId.requestFocus();
        }
    }

    public void onClickExcluir(View v) {
        if (!EditTextPersonagemId.getText().toString().equals("")) {
            Personagem personagem = new Personagem();
            personagem.setPersonagemId(EditTextPersonagemId.getText().toString());
            boolean resultadoConsulta = personagem.abrir();
            if (resultadoConsulta == true) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity3.this);
                dialogo.setTitle("Excluir personagem");
                dialogo.setMessage("Desejar excluir o registro?");
                dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int resultadoExclusao = personagem.excluir();
                        if (resultadoExclusao != 0) {
                            Toast.makeText(MainActivity3.this, "Exclusão realizada com sucesso!", Toast.LENGTH_SHORT).show();
                            atualizaRegistros();
                        } else {
                            Toast.makeText(MainActivity3.this, "Exclusão não realizada!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialogo.show();
            } else {
                Toast.makeText(MainActivity3.this, "Personagem não encontrado, digite um ID de personagem válido!", Toast.LENGTH_SHORT).show();
                EditTextPersonagemId.requestFocus();
            }
        } else {
            Toast.makeText(MainActivity3.this, "Digite um ID de personagem!", Toast.LENGTH_SHORT).show();
            EditTextPersonagemId.requestFocus();
        }
    }

    public void onClickEsvaziarBD(View v) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity3.this);
        dialogo.setTitle("Esvaziar BD");
        dialogo.setMessage("Deseja esvaziar a tabela Personagem?");
        dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Personagem personagem = new Personagem();
                personagem.apagarTabela();
                Toast.makeText(MainActivity3.this, "Tabela Apagada!", Toast.LENGTH_SHORT).show();
            }
        });
        dialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogo.show();
    }

    public void onClickLimpar(View v) {
        EditTextPersonagemId.setText("");
        EditTextNome.setText("");
        EditTextNota.setText("");
        EditTextDescricao.setText("");
        EditTextPersonagemId.requestFocus();
    }

    public void onClickVoltar(View v) {
        System.exit(0);
    }

    public void atualizaRegistros() {
        Personagem personagem = new Personagem();
        TextViewRegistros.setText("Registros: " + personagem.getNumeroRegistros());
    }

}