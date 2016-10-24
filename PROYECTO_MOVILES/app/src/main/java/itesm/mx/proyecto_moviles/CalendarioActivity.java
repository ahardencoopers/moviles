package itesm.mx.proyecto_moviles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.TreeSet;

public class CalendarioActivity extends ListActivity {

    private CalendarioAdapter calendarioAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calendarioAdapter = new CalendarioAdapter(this);
        for (int i = 1; i < 50; i++) {
            calendarioAdapter.addItem("item " + i);
            if (i % 4 == 0) {
                calendarioAdapter.addSeparatorItem("separator " + i);
            }
        }
        setListAdapter(calendarioAdapter);
    }
}
