package itesm.mx.proyecto_moviles;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MedicamentoPendiente extends ListActivity implements View.OnClickListener {

    public ArrayList<MedicamentoPorTomar> getDataForListView() {
        MedicamentoPorTomar medicamentoPorTomar;

        ArrayList<MedicamentoPorTomar> listMedicamentosPorTomar = new ArrayList<MedicamentoPorTomar>();

        medicamentoPorTomar = new MedicamentoPorTomar(R.drawable.logo, "Medicina", "1 Pastilla", "8:00", true);
        listMedicamentosPorTomar.add(medicamentoPorTomar);
        medicamentoPorTomar = new MedicamentoPorTomar(R.drawable.logo, "Medicina", "1 Pastilla", "16:00", false);
        listMedicamentosPorTomar.add(medicamentoPorTomar);
        medicamentoPorTomar = new MedicamentoPorTomar(R.drawable.logo, "Medicina", "1 Pastilla", "00:00", false);
        listMedicamentosPorTomar.add(medicamentoPorTomar);

        return listMedicamentosPorTomar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento_pendiente);

        ArrayList <MedicamentoPorTomar> arrayListMedicamentoPorTomar;
        arrayListMedicamentoPorTomar = getDataForListView();

        MedicamentoPorTomarAdapter adapterMedicamentosPorTomar = new MedicamentoPorTomarAdapter(this, arrayListMedicamentoPorTomar);
        setListAdapter(adapterMedicamentosPorTomar);
    }

    public void onClick(View v) {
        return;
    }
}
