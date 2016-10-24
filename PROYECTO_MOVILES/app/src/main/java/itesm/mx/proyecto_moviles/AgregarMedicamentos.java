package itesm.mx.proyecto_moviles;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class AgregarMedicamentos extends ListActivity {

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

        ArrayList <Medicamento> arrayListMedicamento;
        arrayListMedicamento = getDataForListView();

        MedicamentoAdapter adapterMedicamentos = new MedicamentoAdapter(this, arrayListMedicamento);
        setListAdapter(adapterMedicamentos);
    }
}
