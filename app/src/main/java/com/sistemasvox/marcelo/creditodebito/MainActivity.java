package com.sistemasvox.marcelo.creditodebito;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends Activity implements View.OnKeyListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void preparacaoInicial() {
        EditText entrada = findViewById(R.id.txtEntrada);
        Double n = (Double) Double.parseDouble(entrada.getText().toString());
        calcular(n);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
            preparacaoInicial();
            return true;
        }
        return false;
    }

    private void calcular(Double n) {
        Double aux = n;
        n = (n + ((n * 2.29) / 100));
        DecimalFormat de = new DecimalFormat("#0.00");
        //de.format(n);
        TextView mensagem = findViewById(R.id.txtResultado);
        mensagem.setText("Para R$ " + aux + ", deve ser cobrado: R$ " + de.format(n) + ".");
    }

    private void caixaDeTexto(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Sério?");

        builder.setMessage(s)
                .setCancelable(false)
                .setPositiveButton("OK (reiniciar)", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancelar (exit)", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                        System.exit(0);
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void botao(View x) {
        preparacaoInicial();
        EditText et = findViewById(R.id.txtEntrada);
        et.setText("Pague meus R$ 50.00");
        et.setEnabled(false);
        Button bt = findViewById(R.id.btnCalcular);
        bt.setEnabled(false);

    }
}
