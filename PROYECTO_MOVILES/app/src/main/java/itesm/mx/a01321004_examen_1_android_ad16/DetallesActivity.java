package itesm.mx.a01321004_examen_1_android_ad16;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

public class DetallesActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    final String KEY_NOMBRE = "Usuario";
    final int REQUEST_CODE = 1;
    final String KEY_USER = "USERSESSION";
    Button btnExit;
    Button btnGuardar;
    EditText etSeries;
    EditText etNombre;
    EditText etPrecio;
    Spinner spCdt;
    EditText etTotal;
    ImageView ivArticulo;
    String sNumeros[];
    int iCantidad;
    ArrayList<String> arCarrito;
    void fillSpinner(int iCdt) {
        sNumeros = new String[iCdt + 1];
        for (int i = 0; i <= iCdt; i++) {
            sNumeros[i] = Integer.toString(i);
        }
        ArrayAdapter<String> adapterSpinnerNumeros = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, sNumeros);
        adapterSpinnerNumeros.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCdt.setAdapter(adapterSpinnerNumeros);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        btnExit = (Button) findViewById(R.id.button_exit_detalles);
        btnGuardar = (Button) findViewById(R.id.button_save_details);
        etNombre = (EditText) findViewById(R.id.edit_name_details);
        etPrecio = (EditText) findViewById(R.id.edit_price_details);
        etSeries = (EditText) findViewById(R.id.edit_serie_details);
        spCdt = (Spinner) findViewById(R.id.spinner);
        etTotal = (EditText) findViewById(R.id.edit_total_details);
        ivArticulo = (ImageView) findViewById(R.id.image_details);

        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            int iSeries = intent.getIntExtra("Serie", -1);
            String sNombre = intent.getStringExtra("Nombre");
            int iCdt = intent.getIntExtra("Ctd", 0);
            double dPrecio = intent.getDoubleExtra("Precio", 0);
            int iImage = intent.getIntExtra("Imagen", 0);
            arCarrito = intent.getStringArrayListExtra("Carrito");
            etSeries.setText(Integer.toString(iSeries));
            etNombre.setText(sNombre);
            etPrecio.setText(Double.toString(dPrecio));
            fillSpinner(iCdt);
            ivArticulo.setImageResource(iImage);
            ivArticulo.getLayoutParams().width = 500;
        }
        etTotal.setText("$" + 0);

        btnExit.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);
        spCdt.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        iCantidad = Integer.parseInt(parent.getItemAtPosition(position).toString());
        Double dPrecio = Double.parseDouble(etPrecio.getText().toString());
        etTotal.setText(Double.toString(iCantidad * dPrecio));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        SharedPreferences settings = getSharedPreferences(KEY_USER, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        switch (v.getId()) {

            case R.id.button_exit_detalles:
                editor.putString(KEY_USER, "");
                editor.commit();
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.button_save_details:
                Bundle bundle = new Bundle();
                bundle.putInt("Serie", Integer.parseInt(etSeries.getText().toString()));
                bundle.putInt("CantCompra", iCantidad);
                bundle.putString("Articulo", etNombre.getText().toString());
                bundle.putStringArrayList("Carrito", agregarAlCarrito());
                getIntent().putExtras(bundle);
                setResult(RESULT_OK, getIntent());
                finish();
                break;
        }
    }

   ArrayList<String> agregarAlCarrito() {
       Intent intent = getIntent();
       ArrayList<String> cosasCarrito = arCarrito;
       int iPos = -1;
       if(cosasCarrito != null)
           iPos = cosasCarrito.indexOf(etSeries.getText().toString());
       System.out.println(iPos);
       if(iPos >= 0) {
           int iCant = Integer.parseInt(cosasCarrito.get(iPos));
           String sVal = Integer.toString(iCant + iCantidad);
           cosasCarrito.set(iPos + 1, sVal);
       }
       else {
           cosasCarrito = new ArrayList<String>();
           String sSeries = etSeries.getText().toString();
           String sCantidad = Integer.toString(iCantidad);
           cosasCarrito.add(sSeries);
           cosasCarrito.add(sCantidad);
       }
       for(int i = 0; i < cosasCarrito.size(); i++)
           System.out.print(cosasCarrito.get(i) + " ");
       System.out.println();
       return cosasCarrito;
    }

}
