package itesm.mx.proyecto_moviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnComenzar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

        btnComenzar = (Button) findViewById(R.id.comenzar);
        btnComenzar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.comenzar:
                Intent myIntent = new Intent(this, RegistrateActivity.class);
                startActivity(myIntent);
                break;
        }
    }
}
