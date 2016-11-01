package itesm.mx.proyecto_moviles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class CalendarioActivity extends AppCompatActivity {

    private ListView listCalendario;
    private CalendarioAdapter calendarioAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        listCalendario = (ListView) findViewById(R.id.list2);
        calendarioAdapter = new CalendarioAdapter(this);

        calendarioAdapter.addSeparatorItem("Lunes");
        calendarioAdapter.addItem("Medicina");
        calendarioAdapter.addItem("Medicina");
        calendarioAdapter.addSeparatorItem("Martes");
        calendarioAdapter.addItem("Medicina");
        calendarioAdapter.addItem("Medicina");
        calendarioAdapter.addSeparatorItem("Miercoles");
        calendarioAdapter.addItem("Medicina");
        calendarioAdapter.addItem("Medicina");

        listCalendario.setAdapter(calendarioAdapter);
    }
}
