package com.example.laboratorio1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String imc = getIntent().getStringExtra("resultado");
        Double _imc = getIntent().getDoubleExtra("imc",0.00);
        String resultado="";
        String result = getString(R.string.resultado);

        resultado = result +" "+ imc;

        TextView textView = (TextView) findViewById(R.id.txtResultado);
        TextView txtRecomendacion = (TextView) findViewById(R.id.txtRecomendacion);
        textView.setText(resultado);
        Resources res = getResources();
        String [] Resultados = res.getStringArray(R.array.recomendaciones);
        if(_imc<18.6){
            txtRecomendacion.setText(Resultados[0]);
        }else if(_imc>=18.6 & _imc <=24.9){
            txtRecomendacion.setText(Resultados[1]);
        }else if(_imc>24.9 & _imc<=29.9){
            txtRecomendacion.setText(Resultados[2]);
        }else if(_imc>29.9){
            txtRecomendacion.setText(Resultados[3]);
        }


    }
}