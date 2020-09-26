package com.example.laboratorio1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText txtTalla;
    private EditText txtPeso;
    private TextWatcher text = null;

    public final static String EXTRA_MESSAGE = "com.example.labcomunicacion.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner spinner = findViewById(R.id.spnTipo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.tipo,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        final Button comprobar = (Button) findViewById(R.id.btnMostrar);
        comprobar.setEnabled(false);

        final TextView tipo = (TextView) findViewById(R.id.lblTipo);

        txtTalla = (EditText) findViewById((R.id.txtTalla));
        txtPeso= (EditText) findViewById((R.id.txtPeso));

        text = new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence,int i ,int i1,int i2){
                if(txtPeso.getText().toString().trim().equals("") || txtTalla.getText().toString().trim().equals("")){
                    comprobar.setEnabled(false);
                }
                else{
                    comprobar.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        txtPeso.addTextChangedListener(text);
        txtTalla.addTextChangedListener(text);

        comprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(getApplicationContext(),ResultActivity.class);

                try{

                    Double talla = Double.parseDouble(txtTalla.getText().toString());
                    Double peso =  Double.parseDouble(txtPeso.getText().toString());
                    Double IMC = 0.00;
                    String resultado ="";

                    if(spinner.getSelectedItemPosition()==0){
                        IMC = Math.round((peso/(Math.pow(talla,2)))*10.0)/10.0;
                        resultado=IMC + " Kg/m^2.";
                    }else{
                        IMC= Math.round(((peso*703)/(Math.pow(talla,2)))*10.0)/10.0;
                        resultado=IMC + " lb/inch^2.";
                    }

                    intent.putExtra("resultado",resultado);
                    intent.putExtra("imc",IMC);
                    startActivity(intent);

                }catch (Exception e){

                    Toast.makeText(getBaseContext(),"Ingrese valores válidos",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text  = parent.getItemAtPosition(position).toString();
        TextView talla = (TextView) findViewById(R.id.talla);
        TextView peso = (TextView) findViewById(R.id.peso);
        if(text.equals("Métrico") || text.equals("Metric")){
            talla.setText("m");
            peso.setText("Kg");
        }else if(text.equals("Inglés") || text.equals("English")){
            talla.setText("inch");
            peso.setText("lb");
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}