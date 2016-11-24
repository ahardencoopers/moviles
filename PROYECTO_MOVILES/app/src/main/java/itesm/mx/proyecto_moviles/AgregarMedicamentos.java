package itesm.mx.proyecto_moviles;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.content.Intent;
import android.widget.Button;

import java.util.ArrayList;

public class AgregarMedicamentos extends AppCompatActivity implements View.OnClickListener {

    private Button btnAgregarMedicamento = null;
    private ListView lista;
    //Drawer
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    private ProductoOperations dao;

    public ArrayList<Medicamento> getDataForListView() {
        Medicamento medicamento;

        ArrayList<Medicamento> listMedicamentos = new ArrayList<Medicamento>();

        //medicamento = new Medicamento(0, "Medicina", "Medicina", 1, "8:00 - 16:00 - 00:00","Cada 8 horas", "23/05/2017" );
        //listMedicamentos.add(medicamento);
        return listMedicamentos;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_medicamentos);

        mDrawerList = (ListView)findViewById(R.id.navList);mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //inicio de base de datos
        dao = new ProductoOperations(this);
        dao.open();

        lista = (ListView) findViewById(R.id.list_medicamentos);

        btnAgregarMedicamento = (Button) findViewById(R.id.button_agregar_medicamento);

        ArrayList <Medicamento> arrayListMedicamento;
        arrayListMedicamento = dao.getAllMedicamentos();

        MedicamentoAdapter adapterMedicamentos = new MedicamentoAdapter(this, arrayListMedicamento);
        lista.setAdapter(adapterMedicamentos);
        btnAgregarMedicamento.setOnClickListener(this);
    }

    private void addDrawerItems() {
        String[] osArray = { "Medicamentos", "Mi Doctor", "Hoy", "Calendario"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch(position) {
                    case 0:
                        //intent = new Intent(AgregarMedicamentos.this, AgregarMedicamentos.class);
                        //startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(AgregarMedicamentos.this, Medicos.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(AgregarMedicamentos.this, MedicamentoPendiente.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(AgregarMedicamentos.this, CalendarioActivity.class);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_user) {
            Intent intent = new Intent(AgregarMedicamentos.this, UserActivity.class);
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
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_agregar_medicamento:
                Intent myIntent = new Intent(this, AgregarMedicamento.class);
                startActivity(myIntent);
                break;
        }
    }
}
