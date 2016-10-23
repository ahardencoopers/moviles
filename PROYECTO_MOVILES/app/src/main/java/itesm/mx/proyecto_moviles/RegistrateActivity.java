package itesm.mx.proyecto_moviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;

public class RegistrateActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spinnerSexo = null;
    private String sSexos[] = {"Hombre", "Mujer"};
    private Button btnRegistrarse = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrate);

        btnRegistrarse = (Button) findViewById(R.id.button_registrarse);

        spinnerSexo = (Spinner) findViewById(R.id.spinner_sexo);
        ArrayAdapter<String> adapterSpinnerSexo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sSexos);

        spinnerSexo.setAdapter(adapterSpinnerSexo);
        btnRegistrarse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_registrarse:
                Intent myIntent = new Intent(this, AgregarMedicamentos.class);
                startActivity(myIntent);
                break;
        }
    }
}