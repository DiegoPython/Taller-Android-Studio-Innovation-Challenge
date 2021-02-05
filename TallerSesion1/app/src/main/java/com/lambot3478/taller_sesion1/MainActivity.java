package com.lambot3478.taller_sesion1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNombre;
    private EditText editTextAltura;
    private EditText editTextEdad;
    private EditText editTextPeso;
    private EditText editTextPronombre;

    private RadioGroup opcionesSexo;

    private Button buttonGuardar;
    private Button buttonUsers;

    private Spinner opcionesSangre;

    private String sexo = null;
    private String sangre = null;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        editTextNombre = findViewById(R.id.editTextName);
        editTextAltura = findViewById(R.id.editTextAltura);
        editTextEdad = findViewById(R.id.editTextEdad);
        editTextPeso = findViewById(R.id.editTextPeso);
        editTextPronombre = findViewById(R.id.editTextPronombre);

        opcionesSexo = findViewById(R.id.radioGroupSexo);

        opcionesSangre= findViewById(R.id.spinnerSangre);

        buttonGuardar = findViewById(R.id.buttonGuardar);
        buttonUsers = findViewById(R.id.buttonUsers);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tiposSangre));

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        opcionesSangre.setAdapter(spinnerAdapter);

        //Esta funcion sirve para cambiar el texto de nuestro editText
        //editTextNombre.setText("Hola :D");

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatos();
            }
        });

        buttonUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent usersIntent = new Intent(MainActivity.this, UserTable.class);
                startActivity(usersIntent);
            }
        });

        opcionesSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButtonH)
                    sexo = "H"; //toastMessage("H");
                else
                    sexo = "M"; //toastMessage("M");
            }
        });

        opcionesSangre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sangre = opcionesSangre.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void guardarNombre()
    {

        String nombre = editTextNombre.getText().toString();

        toastMessage("Hola " + nombre + "!");

    }

    public void guardarAltura()
    {

        String altura = editTextAltura.getText().toString();

        if(!altura.isEmpty())
            toastMessage("Tu altura es de " + altura);

    }

    private void guardarDatos()
    {

        String nombre = editTextNombre.getText().toString();
        double altura = 0;
        int edad = 0;
        double peso = 0;
        String pronombre = editTextPronombre.getText().toString();

        if(!editTextAltura.getText().toString().isEmpty()) {
            altura = Double.parseDouble(editTextAltura.getText().toString());
        }

        if(!editTextEdad.getText().toString().isEmpty()) {
            edad = Integer.parseInt(editTextEdad.getText().toString());
        }

        if(!editTextPeso.getText().toString().isEmpty()) {
            peso = Double.parseDouble(editTextPeso.getText().toString());
        }

        if(nombre.isEmpty())
            toastMessage("Por favor, llena el campo de nombre");

        else if(altura <= 0)
            toastMessage("Por favor, llena el campo de altura con un numero valido");

        else if(sexo == null)
            toastMessage("Por favor, elige una opcion de sexo");

        else if(sangre.isEmpty())
            toastMessage("Por favor, escoja su tipo de sangre");

        else if(edad <= 0)
            toastMessage("Por favor, llena el campo de edad con un numero valido");

        else if(peso <= 0)
            toastMessage("Por favor, llena el campo de peso con un numero valido");

        else if(pronombre.isEmpty())
            toastMessage("Por favor, llena el campo de pronombre");

        else
            addData(nombre, edad, altura, peso, sexo, sangre, pronombre);//toastMessage("Nombre: " + nombre + "\nAltura: " + altura + "\nEdad: " + edad + "\nPeso: " + peso + "\nPronombre: " + pronombre + "\nSexo: " + sexo + "\nSangre: " + sangre);

    }

    private void addData(String nombre, int edad, double altura, double peso, String sexo, String sangre, String pronombre)
    {

        boolean insertData = databaseHelper.addData(nombre, edad, altura, peso, sexo, sangre, pronombre);

        if(insertData)
            toastMessage("Usuario agregado!");

        else
            toastMessage("Error: Algo salio mal :(");

    }

    private void toastMessage(String message)
    {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

}