package itesm.mx.proyecto_moviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnComenzar = null;
    private ProductoOperations dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        //inicio de base de datos
        dao = new ProductoOperations(this);
        dao.open();
        Usuario usr = dao.findUsuario();
        if(usr != null) {
            Intent intent = new Intent(this, AgregarMedicamentos.class);
            finish();
            startActivity(intent);
        }

        //llamar a la forma
        btnComenzar = (Button) findViewById(R.id.comenzar);
        btnComenzar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.comenzar:
                Intent myIntent = new Intent(this, RegistrateActivity.class);
                finish();
                startActivity(myIntent);
                break;
        }
    }
}
