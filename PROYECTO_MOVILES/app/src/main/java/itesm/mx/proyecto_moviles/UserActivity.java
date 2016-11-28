package itesm.mx.proyecto_moviles;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity implements View.OnClickListener{
    private String sSexos[] = {"Hombre", "Mujer"};
    EditText etNombre, etDireccion, etTelefono, etDOB, etPeso, etAltura;
    TextView tvNombre;
    TextView tvDOB;
    Spinner spSexo;
    Button btnGuardar;
    ProductoOperations dao;
    Usuario usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        //inicio de base de datos
        dao = new ProductoOperations(this);
        dao.open();

        tvNombre = (TextView) findViewById(R.id.text_user);
        etNombre = (EditText) findViewById(R.id.edit_nombre_user);
        etDireccion = (EditText) findViewById(R.id.edit_direccion_user);
        etTelefono = (EditText) findViewById(R.id.edit_telefono_user);
        tvDOB = (TextView) findViewById(R.id.edit_fechafin);
        etPeso = (EditText) findViewById(R.id.edit_peso_user);
        etAltura = (EditText) findViewById(R.id.edit_altura_user);
        btnGuardar = (Button) findViewById(R.id.button_guardarUser);
        spSexo = (Spinner) findViewById(R.id.spinner_sexo_user);

        ArrayAdapter<String> adapterSpinnerSexo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sSexos);
        spSexo.setAdapter(adapterSpinnerSexo);

        setUser();

        btnGuardar.setOnClickListener(this);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


    private void setUser() {
        usr = dao.findUsuario();
        tvNombre.setText(usr.getNombre());
        etNombre.setText(usr.getNombre());
        etDireccion.setText(usr.getDireccion());
        etTelefono.setText(usr.getTelefono());
        tvDOB.setText(usr.getFechaNacimiento());
        etAltura.setText(Double.toString(usr.getAltura()));
        etPeso.setText(Double.toString(usr.getPeso()));
        spSexo.setSelection((usr.getSexo().equals("Hombre"))? 0 : 1);
    }

    private int saveUser() {
        String sNombre, sDireccion, sTelefono, sDOB, sSexo;
        double dPeso, dAltura;
        sNombre = etNombre.getText().toString();
        sDireccion = etDireccion .getText().toString();
        sTelefono = etTelefono.getText().toString();
        sSexo = spSexo.getSelectedItem().toString();
        sDOB = tvDOB.getText().toString();
        dPeso = Double.parseDouble(etPeso.getText().toString());
        dAltura = Double.parseDouble(etAltura.getText().toString());
        Usuario usrAux = new Usuario(usr.getiId(), sNombre, sDireccion, sTelefono,sSexo, sDOB, dPeso, dAltura);
        return dao.updateUser(usrAux);
    }

    @Override
    public void onClick(View view) {
        long val = saveUser();
        if (val > -1) {
            Toast.makeText(UserActivity.this, "Información de usuario actualizada", Toast.LENGTH_SHORT).show();
            //System.out.println(Long.toString(val) + " " + Long.toString(usr.getiId()));
            //System.out.println(usr.getNombre() + " " + usr.getDireccion());
        }
        else
            Toast.makeText(UserActivity.this, "Error al actualizar usuario", Toast.LENGTH_SHORT).show();
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
