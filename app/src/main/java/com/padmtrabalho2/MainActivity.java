package com.padmtrabalho2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonLista;
    private Button buttonFechar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLista = findViewById(R.id.buttonLista);
        buttonFechar = findViewById(R.id.buttonFechar);
    }

    public void onClickButtonLista(View v) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivityForResult(intent, 0);
    }
    public void onClickBotaoFechar(View v) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle("Fechar aplicativo");
        dialogo.setMessage("Você tem certeza que deseja sair?");
        dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Fechando o aplicativo", Toast.LENGTH_SHORT).show();
                System.exit(0);
            }
        });
        dialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogo.show();
    }
}