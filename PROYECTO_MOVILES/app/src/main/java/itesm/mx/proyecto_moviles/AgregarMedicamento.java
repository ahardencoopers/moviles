package itesm.mx.proyecto_moviles;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AgregarMedicamento extends AppCompatActivity implements View.OnClickListener {

    //Varibles
    private Spinner spinnerTipoMedicamento = null;
    private String sTipoMedicamento[] = {"Pastilla", "Comprimido", "Tableta", "Topico", "Sublingual"};
    private Button btnGuardarMedicamento = null;
    private EditText etNombre;
    private EditText etDosis;
    private TextView etHora;
    private EditText etTomarCada;
    private TextView tvFechafin;
    private EditText etComentarios;

    private ProductoOperations dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_medicamento);

        //inicio de base de datos
        dao = new ProductoOperations(this);
        dao.open();

        btnGuardarMedicamento = (Button) findViewById(R.id.boton_guardar_medicamento);
        etNombre = (EditText) findViewById(R.id.nombre_medicina);
        etDosis = (EditText) findViewById(R.id.edit_dosis);
        etHora = (TextView) findViewById(R.id.edit_hora_inicio);
        etTomarCada = (EditText) findViewById(R.id.edit_tomar_cada);
        tvFechafin = (TextView) findViewById(R.id.edit_fechafin);
        etComentarios = (EditText) findViewById(R.id.edit_comentario);

        spinnerTipoMedicamento = (Spinner) findViewById(R.id.spinner_tipo_medicina);
        ArrayAdapter<String> adapterSpinnerTipoMedicamento= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sTipoMedicamento);

        spinnerTipoMedicamento.setAdapter(adapterSpinnerTipoMedicamento);
        btnGuardarMedicamento.setOnClickListener(this);
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.boton_guardar_medicamento:
                String nombre = etNombre.getText().toString();
                String dosis = etDosis.getText().toString();
                String horainicio = etHora.getText().toString();
                String cadaHora = etTomarCada.getText().toString();
                String fechafin = tvFechafin.getText().toString();
                String comentarios = etComentarios.getText().toString();
                if (etNombre.getText() != null && etDosis.getText() != null
                        && etHora.getText() != null && etTomarCada.getText() != null &&
                        tvFechafin.getText() != null) {
                    Medicamento medicamento = new Medicamento(nombre,
                            spinnerTipoMedicamento.getSelectedItem().toString(),
                            Double.valueOf(dosis), horainicio, cadaHora, comentarios, fechafin);
                    long index = dao.addMedicamento(medicamento);
                    dao.close();
                    finish();
                }
                else {
                    Toast.makeText(AgregarMedicamento.this, "Favor de llenar todos los campos", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


}
