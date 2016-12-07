package itesm.mx.proyecto_moviles; /**
 * Created by achs on 10/24/16.
 */
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import itesm.mx.proyecto_moviles.Medicamento;
import itesm.mx.proyecto_moviles.R;
import java.util.ArrayList;


public class MedicamentoAdapter extends ArrayAdapter<Medicamento> {
    public MedicamentoAdapter(Context context, ArrayList<Medicamento> medicamentos) {
        super(context, 0, medicamentos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Medicamento medicamento = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);

            ImageView ivMedicamento = (ImageView) convertView.findViewById(R.id.image_medicamento);
            TextView tvNombre = (TextView) convertView.findViewById(R.id.text_nombre_medicina);
            TextView tvTomarCada = (TextView) convertView.findViewById(R.id.text_tomar_cada);
            TextView tvHorario = (TextView) convertView.findViewById(R.id.text_horario);
            TextView tvDosis = (TextView) convertView.findViewById(R.id.text_dosis);
            //System.out.println(medicamento.getIdImagen());
            //if(medicamento.getIdImagen() > -1)
            //    ivMedicamento.setImageResource(medicamento.getIdImagen());
            tvNombre.setText(medicamento.getNombre());
            tvTomarCada.setText("Tomar cada: " + medicamento.getTomarCada() + " horas");
            tvHorario.setText("Horario: " + medicamento.getHorario());
            tvDosis.setText("Dosis: " + medicamento.getDosis() + " " + medicamento.getTipo());
        }

        return convertView;
    }
}