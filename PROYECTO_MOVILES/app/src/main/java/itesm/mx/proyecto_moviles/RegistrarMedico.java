package itesm.mx.proyecto_moviles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class RegistrarMedico extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegistrarMedico = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_medico);

        btnRegistrarMedico = (Button) findViewById(R.id.boton_registrar_medico);

        btnRegistrarMedico.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.boton_registrar_medico:
                Intent myIntent = new Intent(this, MedicosRegistrados.class);
                startActivity(myIntent);
        }
    }
}
