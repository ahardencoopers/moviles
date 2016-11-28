package itesm.mx.proyecto_moviles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Faintinger on 11/11/2016.
 */
public class DoctorAdapter extends ArrayAdapter<Doctor> {
    public DoctorAdapter(Context context, ArrayList<Doctor> doctores) {
        super(context, 0, doctores);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Doctor doctor = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_medico, parent, false);
        }

        TextView tvNombre = (TextView) convertView.findViewById(R.id.text_nameRow_doctor);
        TextView tvEspecialidad = (TextView) convertView.findViewById(R.id.text_especRow_doctor);
        TextView tvTelefono = (TextView) convertView.findViewById(R.id.text_telRow_doctor);

        tvNombre.setText(doctor.getNombre());
        tvEspecialidad.setText(doctor.getEspecialidad());
        tvTelefono.setText(doctor.getTelefono());
        return convertView;
    }

}
