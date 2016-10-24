package itesm.mx.proyecto_moviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;

public class AgregarMedicamento extends AppCompatActivity implements View.OnClickListener {

    private Spinner spinnerTipoMedicamento = null;
    private String sTipoMedicamento[] = {"Pastilla", "Comprimido", "Tableta", "Topico", "Sublingual"};
    private Button btnGuardarMedicamento = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_medicamento);

        btnGuardarMedicamento = (Button) findViewById(R.id.boton_guardar_medicamento);

        spinnerTipoMedicamento = (Spinner) findViewById(R.id.spinner_tipo_medicina);
        ArrayAdapter<String> adapterSpinnerTipoMedicamento= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sTipoMedicamento);

        spinnerTipoMedicamento.setAdapter(adapterSpinnerTipoMedicamento);
        btnGuardarMedicamento.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.boton_guardar_medicamento:
                Intent myIntent = new Intent(this, RegistrarMedico.class);
                startActivity(myIntent);
                break;
        }
    }
}
