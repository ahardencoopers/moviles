package itesm.mx.proyecto_moviles;

import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class RegistrarMedico extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegistrarMedico = null;
    private EditText etNombre, etEspecialidad, etDireccion, etCP, etCiudad, etTelefono, etCorreo;
    private ProductoOperations dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_medico);

        //base de datos
        dao = new ProductoOperations(this);
        dao.open();

        etNombre = (EditText) findViewById(R.id.edit_nombre_medico);
        etEspecialidad = (EditText) findViewById(R.id.edit_especialidad_medico);
        etDireccion = (EditText) findViewById(R.id.edit_direccion_medico);
        etCP = (EditText) findViewById(R.id.edit_codigo_postal_medico);
        etCiudad = (EditText) findViewById(R.id.edit_ciudad_medico);
        etTelefono = (EditText) findViewById(R.id.edit_telefono_medico);
        etCorreo = (EditText) findViewById(R.id.edit_correo_medico);

        btnRegistrarMedico = (Button) findViewById(R.id.boton_registrar_medico);

        btnRegistrarMedico.setOnClickListener(this);
    }

    private long saveMedico() {
        long res = -1;
        String sNombre = etNombre.getText().toString();
        String sEsp = etEspecialidad.getText().toString();
        String sDir = etDireccion.getText().toString();
        String sCP = etCP.getText().toString();
        String sCiudad = etCiudad.getText().toString();
        String sTelefono = etTelefono.getText().toString();
        String sCorreo = etCorreo.getText().toString();
        if(TextUtils.isEmpty(sNombre) || TextUtils.isEmpty(sEsp) || TextUtils.isEmpty(sCP) || TextUtils.isEmpty(sCiudad) || TextUtils.isEmpty(sTelefono)
                || TextUtils.isEmpty(sCorreo)) {
            Toast.makeText(RegistrarMedico.this, "El médico no pudo ser registrado, llenar todos los campos e intentar de nuevo", Toast.LENGTH_SHORT).show();
            return -1;

        }
        else {
            Doctor doc = new Doctor(0,sNombre,sEsp,sDir,sCP,sCiudad,sCorreo,sTelefono);
            res = dao.addDoctor(doc);
            System.out.println("ID: " + res);
            return res;
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.boton_registrar_medico:
                Intent myIntent = new Intent(this, Medicos.class);
                startActivity(myIntent);
                if(saveMedico() > -1) {
                    finish();
                    Toast.makeText(RegistrarMedico.this, "Médico Registrado!", Toast.LENGTH_SHORT).show();
                }
                /*else {
                    Toast.makeText(RegistrarMedico.this, "El médico no pudo ser registrado", Toast.LENGTH_SHORT).show();
                }*/
        }
    }


}
