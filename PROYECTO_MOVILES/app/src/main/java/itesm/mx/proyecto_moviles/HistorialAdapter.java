package itesm.mx.proyecto_moviles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.TreeSet;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by achs on 24/10/16.
 */
class HistorialAdapter extends BaseAdapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
    private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;

    private ArrayList<HistorialEntry> mData = new ArrayList<HistorialEntry>();
    private LayoutInflater mInflater;

    private TreeSet<Integer> mSeparatorsSet = new TreeSet<Integer>();
    private Context context;

    public HistorialAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(final HistorialEntry item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void addSeparatorItem(final HistorialEntry item) {
        mData.add(item);
        // save separator position
        mSeparatorsSet.add(mData.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return mSeparatorsSet.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_MAX_COUNT;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public HistorialEntry getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        int type = getItemViewType(position);
        System.out.println("getView " + position + " " + convertView + " type = " + type);
        if (convertView == null) {
            holder = new ViewHolder();
            switch (type) {
                case TYPE_ITEM:
                    convertView = mInflater.inflate(R.layout.row_historial, null);
                    holder.imageView = (ImageView) convertView.findViewById(R.id.image_medicamento);
                    holder.textView = (TextView) convertView.findViewById(R.id.text_nombre_medicina);
                    holder.horario = (TextView) convertView.findViewById(R.id.text_horario);
                    holder.dosis = (TextView) convertView.findViewById(R.id.text_dosis);
                    break;
                case TYPE_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.row_dia, null);
                    holder.textView = (TextView)convertView.findViewById(R.id.text_dia);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        if( type == TYPE_ITEM) {
            holder.textView.setText(mData.get(position).getNombre());
            holder.horario.setText(mData.get(position).getHorario());
            holder.dosis.setText(Double.toString(mData.get(position).getDosis()));
        }
        else {
            holder.textView.setText(mData.get(position).getSeparador());
        }

        return convertView;
    }

}
