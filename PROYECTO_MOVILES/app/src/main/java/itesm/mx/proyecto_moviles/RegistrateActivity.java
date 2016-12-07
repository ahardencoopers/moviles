package itesm.mx.proyecto_moviles;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class RegistrateActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spinnerSexo = null;
    private String sSexos[] = {"Hombre", "Mujer"};
    private Button btnRegistrarse = null;
    private ProductoOperations dao;
    EditText etNombre, etDireccion, etTelefono, etDOB, etPeso, etAltura;
    TextView tvDOB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrate);
        //inicio de base de datos
        dao = new ProductoOperations(this);
        dao.open();
        //Declaracion de forma
        etNombre = (EditText) findViewById(R.id.edit_nombre);
        etDireccion = (EditText) findViewById(R.id.edit_direccion);
        etTelefono = (EditText) findViewById(R.id.edit_telefono);
        tvDOB = (TextView) findViewById(R.id.edit_fechafin);
        etPeso = (EditText) findViewById(R.id.edit_peso);
        etAltura = (EditText) findViewById(R.id.edit_altura);

        btnRegistrarse = (Button) findViewById(R.id.button_registrarse);

        spinnerSexo = (Spinner) findViewById(R.id.spinner_sexo);
        ArrayAdapter<String> adapterSpinnerSexo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sSexos);

        spinnerSexo.setAdapter(adapterSpinnerSexo);
        btnRegistrarse .setOnClickListener(this);
    }

    private boolean registrarUsuario() {
        String sNombre, sDireccion, sTelefono, sDOB, sSexo;
        double dPeso, dAltura;

        sNombre = etNombre.getText().toString();
        sDireccion = etDireccion .getText().toString();
        sTelefono = etTelefono.getText().toString();
        sSexo = spinnerSexo.getSelectedItem().toString();
        sDOB = tvDOB.getText().toString();


        if(TextUtils.isEmpty(sNombre) || TextUtils.isEmpty(sDireccion) || TextUtils.isEmpty(sTelefono) || TextUtils.isEmpty(sSexo)
                || TextUtils.isEmpty(sDOB) || TextUtils.isEmpty(etPeso.getText().toString()) || TextUtils.isEmpty(etAltura.getText().toString())) {
            Toast.makeText(RegistrateActivity.this,
                    "Error al registrar usuario, favor de llenar todos los campos e intentarlo de nuevo.",
                    Toast.LENGTH_SHORT).show();

            return false;
        }
        else {
            dPeso = Double.parseDouble(etPeso.getText().toString());
            dAltura = Double.parseDouble(etAltura.getText().toString());

            Usuario usr = new Usuario(0, sNombre, sDireccion, sTelefono,sSexo, sDOB, dPeso, dAltura);

            long index = dao.addUsuario(usr);
            return (index > -1);
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_registrarse:
                if(registrarUsuario()) {
                    Intent myIntent = new Intent(this, AgregarMedicamentos.class);
                    dao.close();
                    finish();
                    startActivity(myIntent);
                }
                /*else {
                    Toast.makeText(RegistrateActivity.this,
                            "Error al registrar usuario, favor de volver a abrir la app e intentarlo nuevamente...",
                            Toast.LENGTH_SHORT).show();
                }*/
        }
    }

    @Override
    protected void onResume() {
        dao.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dao.close();
        super.onPause();
    }

}