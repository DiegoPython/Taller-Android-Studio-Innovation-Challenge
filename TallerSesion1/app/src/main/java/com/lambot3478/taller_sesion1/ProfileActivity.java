package com.lambot3478.taller_sesion1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    private TextView textViewNombre;
    private TextView textViewEdad;
    private TextView textViewAltura;
    private TextView textViewPeso;
    private TextView textViewSexo;
    private TextView textViewSangre;
    private TextView textViewPronombre;

    private int id;
    private String nombre;
    private int edad;
    private double altura;
    private double peso;
    private String sexo;
    private String sangre;
    private String pronombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textViewNombre = findViewById(R.id.textViewNombreP);
        textViewEdad = findViewById(R.id.textViewEdadP);
        textViewAltura = findViewById(R.id.textViewAlturaP);
        textViewPeso = findViewById(R.id.textViewPesoP);
        textViewSexo = findViewById(R.id.textViewSexoP);
        textViewSangre = findViewById(R.id.textViewSangreP);
        textViewPronombre = findViewById(R.id.textViewPronombreP);

        databaseHelper = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();

        id = receivedIntent.getIntExtra("id", -1);

        getData(id);

        textViewNombre.setText(nombre);
        textViewEdad.setText("Edad: " + Integer.toString(edad));
        textViewAltura.setText("Altura: " + Double.toString(altura));
        textViewPeso.setText("Peso: " +Double.toString(peso));
        textViewSexo.setText("Sexo: " +sexo);
        textViewSangre.setText("Tipo de sangre: " + sangre);
        textViewPronombre.setText("Pronombre de preferencia: " + pronombre);

    }

    private void getData(int id)
    {

        Cursor data = databaseHelper.getUserData(id);

        while(data.moveToNext())
        {

            nombre = data.getString(1);
            edad = data.getInt(2);
            altura = data.getDouble(3);
            peso = data.getDouble(4);
            sexo = data.getString(5);
            sangre = data.getString(6);
            pronombre = data.getString(7);

        }

        if(nombre.equals("") || edad <= 0 || altura <= 0 || peso <= 0 || sexo.equals("") || sangre.equals("") || pronombre.equals(""))
        {

            toastMessage("No se encontro un usario con el ID indicado");
            finish();

        }

    }

    private void toastMessage(String message)
    {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

}