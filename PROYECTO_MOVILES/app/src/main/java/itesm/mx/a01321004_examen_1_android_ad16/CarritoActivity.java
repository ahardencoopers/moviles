package itesm.mx.a01321004_examen_1_android_ad16;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CarritoActivity extends AppCompatActivity implements View.OnClickListener{
    final int REQUEST_CODE = 1;
    final String KEY_NOMBRE = "Usuario";
    final String KEY_USER = "USERSESSION";
    double dTotal = 0;
    Button btnExit;
    ListView lvCarrito;
    TextView tvUser;
    TextView tvTotal;

    public ArrayList<Articulo> getDataForListView() {
        Articulo articulo;
        ArrayList<Articulo> listArticulos = new ArrayList<Articulo>();
        articulo = new Articulo(2345, "Pluma Azul", 150, 4.20, R.drawable.plumaazul);
        listArticulos.add(articulo);
        articulo = new Articulo(3456,"Sharpie Rojo", 15, 16.10, R.drawable.sharpie);
        listArticulos.add(articulo);
        articulo = new Articulo(3457, "Legajo tamaño carta", 25, 5, R.drawable.legajo);
        listArticulos.add(articulo);
        articulo = new Articulo(3458, "Sacapuntas electrico", 18, 317.50, R.drawable.sacapuntas);
        listArticulos.add(articulo);
        articulo = new Articulo(3459, "Borrador pintarrón", 200, 23, R.drawable.borrador);
        listArticulos.add(articulo);
        articulo = new Articulo(4356, "Pluma negra", 100, 10.50, R.drawable.plumanegra);
        listArticulos.add(articulo);
        articulo = new Articulo(12345, "Lápiz Mirado #2", 140, 5.60, R.drawable.lapiz);
        listArticulos.add(articulo);
        return listArticulos;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        btnExit = (Button) findViewById(R.id.button_exit_shopcar);
        lvCarrito = (ListView) findViewById(R.id.listView_shopcar);
        tvTotal = (TextView) findViewById(R.id.edit_total_car);
        tvUser = (TextView) findViewById(R.id.text_user_carshop);

        String sUser;
        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            sUser = intent.getStringExtra(KEY_NOMBRE);
            tvUser.setText(sUser);
            Bundle bundle = intent.getExtras();
            ArrayList<String> Carrito = (ArrayList<String>)bundle.get("Carrito");
            if(Carrito != null) {
                ArrayList<Articulo> arrayListArticulo = getCarrito(Carrito);
                ArticuloAdapter adapterArticulos = new ArticuloAdapter(this, arrayListArticulo);
                lvCarrito.setAdapter(adapterArticulos);
            }
        }

        tvTotal.setText(Double.toString(dTotal));

        btnExit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        SharedPreferences settings = getSharedPreferences(KEY_USER, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString(KEY_USER, "");
        editor.commit();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    ArrayList<Articulo> getCarrito(ArrayList<String> car) {
        //ArticulosActivity art = new ArticulosActivity();
        ArrayList<Articulo> articulos = getDataForListView();
        ArrayList<Articulo> carrito = new ArrayList<Articulo>();
        for(int cont = 0; cont < car.size(); cont = cont + 2) {
            for(int i = 0; i < articulos.size(); i++) {
                if(articulos.get(i).getiSerie() == Integer.parseInt(car.get(cont))) {
                    Articulo artAux = articulos.get(i);
                    artAux.setiCtd(Integer.parseInt(car.get(cont+1)));
                    carrito.add(artAux);
                    dTotal += artAux.getiCtd() * artAux.getdPrecio();
                    i = articulos.size();
                }
            }
        }
        return carrito;
    }
}
