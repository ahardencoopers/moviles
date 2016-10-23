package itesm.mx.proyecto_moviles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RegistrateActivity extends AppCompatActivity {

    private Spinner spinnerSexo = null;
    private String sSexos[] = {"Hombre", "Mujer"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrate);

        spinnerSexo = (Spinner) findViewById(R.id.spinner_sexo);
        ArrayAdapter<String> adapterSpinnerSexo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sSexos);

        spinnerSexo.setAdapter(adapterSpinnerSexo);
    }
}