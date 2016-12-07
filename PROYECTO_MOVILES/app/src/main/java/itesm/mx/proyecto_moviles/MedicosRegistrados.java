package itesm.mx.proyecto_moviles;

import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MedicosRegistrados extends AppCompatActivity implements View.OnClickListener{

    //Drawer
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    //database
    ProductoOperations dao;

    //Views
    EditText etNombre, etEspecialidad, etHospital, etCP, etCiudad, etTelefono, etCorreo;
    Button btnActualizar;

    private long idMedico = 1;
    private int REQUESTCODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicos_registrados);

        //data base
        dao = new ProductoOperations(this);
        dao.open();

        //Drawer
        mDrawerList = (ListView)findViewById(R.id.navList);mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        addDrawerItems();
        setupDrawer();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //getViews
        etNombre = (EditText) findViewById(R.id.text_nombre_medico);
        etEspecialidad = (EditText) findViewById(R.id.text_especialidad);
        etHospital = (EditText) findViewById(R.id.text_hospital);
        etCP = (EditText) findViewById(R.id.text_codigo_postal);
        etCiudad = (EditText) findViewById(R.id.text_numero);
        etTelefono = (EditText) findViewById(R.id.text_telefono);
        etCorreo = (EditText) findViewById(R.id.text_correo);
        btnActualizar = (Button) findViewById(R.id.button_actualizar_medico);

        //listeners
        btnActualizar.setOnClickListener(this);

        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            System.out.println("Id: " + intent.getLongExtra("Id", idMedico));
            idMedico = intent.getLongExtra("Id", idMedico);
            setMedico(idMedico);
        }

    }

    void setMedico(float id)
    {
        Doctor doc = dao.findDoctor(Float.toString(id));
        etNombre.setText(doc.getNombre());
        etEspecialidad.setText(doc.getEspecialidad());
        etHospital.setText(doc.getDireccion());
        etCP.setText(doc.getCodigopos());
        etCiudad.setText(doc.getCiudad());
        etTelefono.setText(doc.getTelefono());
        etCorreo.setText(doc.getCorreo());
    }

    float saveMedico()
    {
        String sNombre = etNombre.getText().toString();
        String sEsp = etEspecialidad.getText().toString();
        String sHospital = etHospital.getText().toString();
        String sCP = etCP.getText().toString();
        String sCiudad = etCiudad.getText().toString();
        String sTelefono = etTelefono.getText().toString();
        String sCorreo = etCorreo.getText().toString();
        Doctor doc = new Doctor(idMedico, sNombre, sEsp, sHospital, sCP, sCiudad, sCorreo, sTelefono);
        return dao.updateMedico(doc);
    }

    @Override
    public void onClick(View view) {
        if(saveMedico() > -1) {
            Toast.makeText(MedicosRegistrados.this, "Información de médico actualizada", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        }
        else
            Toast.makeText(MedicosRegistrados.this, "Error al actualizar médico", Toast.LENGTH_SHORT).show();
    }

    private void addDrawerItems() {
        String[] osArray = { "Medicamentos", "Mi Doctor", "Hoy", "Calendario", "Historial" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch(position) {
                    case 0:
                        intent = new Intent(MedicosRegistrados.this, AgregarMedicamentos.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 1:
                        intent = new Intent(MedicosRegistrados.this, Medicos.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 2:
                        intent = new Intent(MedicosRegistrados.this, MedicamentoPendiente.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 3:
                        intent = new Intent(MedicosRegistrados.this, CalendarioActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 4:
                        intent = new Intent(MedicosRegistrados.this, Historial.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Menu");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_user) {
            Intent intent = new Intent(MedicosRegistrados.this, UserActivity.class);
            startActivity(intent);
            return true;
        }
        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        dao.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dao.close();
        super.onPause();
    }
}
