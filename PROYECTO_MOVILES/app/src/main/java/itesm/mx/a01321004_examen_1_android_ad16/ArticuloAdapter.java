package itesm.mx.a01321004_examen_1_android_ad16;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Faintinger on 9/15/2016.
 */
public class ArticuloAdapter extends ArrayAdapter <Articulo> {
    ArticuloAdapter(Context context, ArrayList<Articulo> articulos) {
        super(context,0, articulos);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        Articulo artObj = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent,false);
        }

        EditText etSerie = (EditText) convertView.findViewById(R.id.edit_serie_row);
        EditText etNombre = (EditText) convertView.findViewById(R.id.edit_name_row);
        EditText etCtd = (EditText) convertView.findViewById(R.id.edit_ctd_row);
        EditText etPrecio = (EditText) convertView.findViewById(R.id.edit_price_row);
        ImageView ivArticulo = (ImageView) convertView.findViewById(R.id.image_articulo_row);

        etSerie.setText(Integer.toString(artObj.getiSerie()));
        etNombre.setText(artObj.getsNombre());
        etCtd.setText(Integer.toString(artObj.getiCtd()));
        String sValor = Double.toString(artObj.getdPrecio());
        etPrecio.setText("$" + sValor);
        ivArticulo.setImageResource(artObj.getiIdImagen());

        return convertView;

    }
}
