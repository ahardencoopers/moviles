package itesm.mx.proyecto_moviles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by achs on 24/10/16.
 */
public class MedicamentoPorTomarAdapter extends ArrayAdapter<MedicamentoPorTomar> {
    public MedicamentoPorTomarAdapter(Context context, ArrayList<MedicamentoPorTomar> medicamentosPorTomar) {
        super(context, 0, medicamentosPorTomar);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MedicamentoPorTomar medicamentoPorTomar = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_medicamento_pendiente, parent, false);

            ImageView ivMedicamento = (ImageView) convertView.findViewById(R.id.image_medicamento);
            TextView tvNombre = (TextView) convertView.findViewById(R.id.text_nombre_medicina);
            TextView tvDosis = (TextView) convertView.findViewById(R.id.text_dosis);
            TextView tvHorarioDosis = (TextView) convertView.findViewById(R.id.text_horario_dosis);
            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox2);

            //ivMedicamento.setImageResource(medicamentoPorTomar.getIdImagen());
            tvNombre.setText(medicamentoPorTomar.getNombre());
            tvDosis.setText("Dosis: " + medicamentoPorTomar.getDosis());
            tvHorarioDosis.setText("Hora: " + medicamentoPorTomar.getHorario());
            checkBox.setChecked(medicamentoPorTomar.getTomada());

        }
        else {
            ImageView ivMedicamento = (ImageView) convertView.findViewById(R.id.image_medicamento);
            TextView tvNombre = (TextView) convertView.findViewById(R.id.text_nombre_medicina);
            TextView tvDosis = (TextView) convertView.findViewById(R.id.text_dosis);
            TextView tvHorarioDosis = (TextView) convertView.findViewById(R.id.text_horario_dosis);
            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox2);

            //ivMedicamento.setImageResource(medicamentoPorTomar.getIdImagen());
            tvNombre.setText(medicamentoPorTomar.getNombre());
            tvDosis.setText("Dosis: " + medicamentoPorTomar.getDosis());
            tvHorarioDosis.setText("Hora: " + medicamentoPorTomar.getHorario());
            checkBox.setChecked(medicamentoPorTomar.getTomada());

        }
        return convertView;
    }
}
