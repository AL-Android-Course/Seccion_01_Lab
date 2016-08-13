package com.alejandrolora.seccion_01_lab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    // Elementos UI
    private ImageButton btnConfirm;
    private Button btnSharing;

    // Otros valores
    private String name;
    private int age;
    private int typeOfMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // Activar la flecha para volver al activity principal
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Recogemos el nombre del activity anterior
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            name = bundle.getString("name");
            age = bundle.getInt("age");
            typeOfMessage = bundle.getInt("option");
        }


        // Instanciamos los elementos de la UI con sus referencias
        btnConfirm = (ImageButton) findViewById(R.id.buttonConfirm);
        btnSharing = (Button) findViewById(R.id.buttonToShare);


        // Evento click del botón confirmar
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ThirdActivity.this, createMessage(name, age, typeOfMessage), Toast.LENGTH_LONG).show();
                btnSharing.setVisibility(View.VISIBLE);
                btnConfirm.setVisibility(View.INVISIBLE);
            }
        });


        // Evento click del botón share
        btnSharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, createMessage(name, age, typeOfMessage));
                startActivity(intent);
            }
        });
    }


    private String createMessage(String name, int age, int typeOfMessage) {
        if (typeOfMessage == SecondActivity.GREETER_OPTION) {
            return "Hola " + name + ", ¿Cómo llevas esos " + age + " años? #MyForm";
        } else {
            return "Espero verte pronto " + name + ", antes que cumplas " + (age + 1) + ".. #MyForm";
        }
    }
}
