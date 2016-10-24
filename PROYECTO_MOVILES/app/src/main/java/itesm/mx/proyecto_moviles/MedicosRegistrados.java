package itesm.mx.proyecto_moviles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MedicosRegistrados extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicos_registrados);

        Button tempcontinuar = (Button) findViewById(R.id.boton_temp_continuar);

        tempcontinuar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.boton_temp_continuar:
                Intent myIntent = new Intent(this, MedicamentoPendiente.class);
                startActivity(myIntent);
        }
    }
}
