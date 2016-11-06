package itesm.mx.a01321004_examen_1_android_ad16;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.app.ListActivity;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ArticulosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    final String KEY_NOMBRE = "Usuario";
    final String KEY_USER = "USERSESSION";
    final int REQUEST_CODE = 1;
    TextView tvUser;
    Button btnExit;
    Button btnCarrito;
    ListView lvArticulos;
    ArrayList<Articulo> arrayListArticulo;
    ArticuloAdapter adapterArticulos;

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
        setContentView(R.layout.activity_articulos);


        tvUser = (TextView) findViewById(R.id.text_user_articulos);
        lvArticulos = (ListView) findViewById(R.id.list);
        btnExit = (Button) findViewById(R.id.button_exit_articulos);
        btnCarrito = (Button) findViewById(R.id.button_car_articulos);

        String sUser;
        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            sUser = intent.getStringExtra(KEY_NOMBRE);
            tvUser.setText(sUser);
        }

        arrayListArticulo = getDataForListView();

        adapterArticulos = new ArticuloAdapter(this, arrayListArticulo);
        lvArticulos.setAdapter(adapterArticulos);
        //setListAdapter(adapterArticulos);

        btnExit.setOnClickListener(this);
        btnCarrito.setOnClickListener(this);
        lvArticulos.setOnItemClickListener(this);
        //getListView().setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_car_articulos:
                Intent intento = new Intent(this, CarritoActivity.class);
                intento.putExtra(KEY_NOMBRE, tvUser.getText().toString());
                Intent data = getIntent();
                Bundle extras = data.getExtras();
                ArrayList<String> cosasCarrito = (ArrayList<String>)extras.get("Carrito");
                intento.putExtra("Carrito", cosasCarrito);
                for(int i = 0; i < cosasCarrito.size(); i++)
                    System.out.print(cosasCarrito.get(i) + " ");
                System.out.println();
                startActivity(intento);
                break;
            case R.id.button_exit_articulos:
                SharedPreferences settings = getSharedPreferences(KEY_USER, MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString(KEY_USER, "");
                editor.commit();
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, REQUEST_CODE);
                break;
        }

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Articulo articulo = (Articulo) parent.getItemAtPosition(position);
        Intent intent = new Intent(this, DetallesActivity.class);
        intent.putExtra("Serie", articulo.getiSerie());
        intent.putExtra("Nombre",articulo.getsNombre());
        intent.putExtra("Ctd",articulo.getiCtd());
        intent.putExtra("Precio",articulo.getdPrecio());
        intent.putExtra("Imagen", articulo.getiIdImagen());
        Intent data = getIntent();
        Bundle extras = data.getExtras();
        intent.putExtra("Carrito", (ArrayList<String>) extras.get("Carrito"));
        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            int iSerie = (int) extras.get("Serie"); //intent.getIntExtra("Serie", -1);
            String sArticulo = (String) extras.get("Articulo"); //intent.getStringExtra("Articulo");
            int iCant = (int) extras.get("CantCompra"); //intent.getIntExtra("CantCompra", 0);
            getIntent().putExtras(data);
            if(iCant > 0) {
                Toast.makeText(getApplicationContext(), "Compra de " + sArticulo + " guardada!",
                        Toast.LENGTH_LONG).show();
            }
            if(iSerie > 0) {
                ArrayList<Articulo> arrayListArticulo = getDataForListView();
                System.out.println(iSerie);
                int iPos = -1;
                for(int i = 0; i < arrayListArticulo.size(); i++) {
                    if(iSerie == arrayListArticulo.get(i).getiSerie()) {
                        iPos = i;
                        break;
                    }
                }
                System.out.println(iPos);
                if(iPos > -1) {
                    Articulo art = arrayListArticulo.get(iPos);
                    art.setiCtd(art.getiCtd() - iCant);
                    arrayListArticulo.set(iPos, art);
                    //ArticuloAdapter adapterArticulos = new ArticuloAdapter(this, arrayListArticulo);
                    //lvArticulos.setAdapter(adapterArticulos);
                    adapterArticulos.notifyDataSetChanged();
                    //setListAdapter(adapterArticulos);
                }
            }
        }
    }
}
