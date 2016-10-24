package itesm.mx.proyecto_moviles;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class AgregarMedicamentos extends ListActivity implements View.OnClickListener {

    private Button btnAgregarMedicamento = null;

    public ArrayList<Medicamento> getDataForListView() {
        Medicamento medicamento;

        ArrayList<Medicamento> listMedicamentos = new ArrayList<Medicamento>();

        medicamento = new Medicamento(R.drawable.logo, "Medicina", "Tomar cada 8 horas", "8:00 - 16:00 - 00:00");
        listMedicamentos.add(medicamento);
        medicamento = new Medicamento(R.drawable.logo, "Medicina", "Tomar cada 8 horas", "8:00 - 16:00 - 00:00");
        listMedicamentos.add(medicamento);

        return listMedicamentos;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_medicamentos);

        btnAgregarMedicamento = (Button) findViewById(R.id.button_agregar_medicamento);

        ArrayList <Medicamento> arrayListMedicamento;
        arrayListMedicamento = getDataForListView();

        MedicamentoAdapter adapterMedicamentos = new MedicamentoAdapter(this, arrayListMedicamento);
        setListAdapter(adapterMedicamentos);
        btnAgregarMedicamento.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_agregar_medicamento:
                Intent myIntent = new Intent(this, AgregarMedicamento.class);
                startActivity(myIntent);
                break;
        }
    }
}
