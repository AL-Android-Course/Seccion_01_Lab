package com.alejandrolora.seccion_01_lab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    // Elementos UI
    private SeekBar seekBarAge;
    private TextView textViewAge;
    private Button btnNext;
    private RadioButton radioButtonGreeter;
    private RadioButton radioButtonFarewell;

    // Otros valores
    private String name = "";
    private int age = 18;
    private final int MAX_AGE = 60;
    private final int MIN_AGE = 16;

    // Para compartir
    public static final int GREETER_OPTION = 1;
    public static final int FAREWELL_OPTION = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Activar la flecha para volver al activity principal
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Recogemos el nombre del activity anterior
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            name = bundle.getString("name");
        }

        // Instanciamos los elementos de la UI con sus referencias
        seekBarAge = (SeekBar) findViewById(R.id.seekBarAge);
        textViewAge = (TextView) findViewById(R.id.textViewCurrentAge);
        btnNext = (Button) findViewById(R.id.buttonToThirdActivity);
        radioButtonGreeter = (RadioButton) findViewById(R.id.radioButtonGreeter);
        radioButtonFarewell = (RadioButton) findViewById(R.id.radioButtonFarewell);


        // Evento change para el SeekBar
        seekBarAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int currentAge, boolean b) {
                age = currentAge;
                textViewAge.setText(age + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Aunque no lo sobreescribamos con alguna funcionalidad, OnSeekBarChangeListener es una interfaz
                // y como interfaz que es, necesitamos sobreescribir todos sus métodos, aunque lo dejemos vacío.
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Declaramos nuestras restricciones de edad en el evento en que el usuario suelta/deja el seekbar.
                age = seekBar.getProgress();
                textViewAge.setText(age + "");

                if (age > MAX_AGE) {
                    btnNext.setVisibility(View.INVISIBLE);
                    Toast.makeText(SecondActivity.this, "The max age allowed is: "+MAX_AGE+" years old.", Toast.LENGTH_LONG).show();
                } else if (age < MIN_AGE) {
                    btnNext.setVisibility(View.INVISIBLE);
                    Toast.makeText(SecondActivity.this, "The min age allowed is: "+MIN_AGE+" years old.", Toast.LENGTH_LONG).show();
                } else {
                    btnNext.setVisibility(View.VISIBLE);
                }
            }
        });


        // Evento click del botón para pasar al siguiente Activity
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                // Si el botón de greeter esta activo, option valdrá 1, si no, 2
                int option = (radioButtonGreeter.isChecked()) ? GREETER_OPTION : FAREWELL_OPTION;
                intent.putExtra("option", option);
                startActivity(intent);
                Toast.makeText(SecondActivity.this, seekBarAge.getProgress()+"", Toast.LENGTH_LONG).show();
            }
        });


    }
}
