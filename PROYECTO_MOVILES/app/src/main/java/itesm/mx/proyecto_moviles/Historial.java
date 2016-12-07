package itesm.mx.proyecto_moviles;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Historial extends AppCompatActivity {

    private ListView listHistorial;
    private static HistorialAdapter historialAdapter;
    //Drawer
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    private ProductoOperations dao;

    public ArrayList<MedicamentoPorTomar> getDataForHistorial() {
        ArrayList<HistorialEntry> listHistorialEntry = new ArrayList<HistorialEntry>();
        ArrayList<MedicamentoPorTomar> listMedicamentoPorTomar = new ArrayList<MedicamentoPorTomar>();
        HistorialEntry temp;
        int dias = 0;

        listMedicamentoPorTomar = dao.getAllHistorialAsc();

        if(listMedicamentoPorTomar.size() > 0) {
            String fechaActual = listMedicamentoPorTomar.get(0).getFecha();
            String[] fecha = listMedicamentoPorTomar.get(0).getFecha().split("/");

            Log.d("historial", listMedicamentoPorTomar.get(0).getNombre() + Long.toString(listMedicamentoPorTomar.get(0).getId()));
            Log.d("historial", listMedicamentoPorTomar.get(0).getFecha());
            temp = new HistorialEntry(Integer.parseInt(fecha[0]) + "/" + Integer.parseInt(fecha[1]) + "/" + Integer.parseInt(fecha[2]));
            historialAdapter.addSeparatorItem(temp);
            /*
            temp = new HistorialEntry(listMedicamentoPorTomar.get(0).getId(), listMedicamentoPorTomar.get(0).getNombre(),
                    listMedicamentoPorTomar.get(0).getFecha(), Double.parseDouble(listMedicamentoPorTomar.get(0).getDosis()),
                    listMedicamentoPorTomar.get(0).getHorario(), listMedicamentoPorTomar.get(0).getTomada());
            historialAdapter.addItem(temp);
            */

            for(int i=0; i<listMedicamentoPorTomar.size(); i++) {
                MedicamentoPorTomar medActual = listMedicamentoPorTomar.get(i);
                if(!fechaActual.equals(medActual.getFecha())) {
                    fechaActual = medActual.getFecha();
                    fecha = medActual.getFecha().split("/");

                    temp = new HistorialEntry(Integer.parseInt(fecha[0]) + "/" + Integer.parseInt(fecha[1]) + "/" + Integer.parseInt(fecha[2]));
                    historialAdapter.addSeparatorItem(temp);
                }

                temp = new HistorialEntry(listMedicamentoPorTomar.get(i).getId(), listMedicamentoPorTomar.get(i).getNombre(),
                        listMedicamentoPorTomar.get(i).getFecha(), Double.parseDouble(listMedicamentoPorTomar.get(i).getDosis()),
                        listMedicamentoPorTomar.get(i).getHorario(), listMedicamentoPorTomar.get(i).getTomada());
                historialAdapter.addItem(temp);
            }
        }

        return listMedicamentoPorTomar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        mDrawerList = (ListView)findViewById(R.id.navList);mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //inicio de base de datos
        dao = new ProductoOperations(this);
        dao.open();

        listHistorial = (ListView) findViewById(R.id.list_historial);
        historialAdapter = new HistorialAdapter(this);

        this.getDataForHistorial();

        listHistorial.setAdapter(historialAdapter);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
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
                        intent = new Intent(Historial.this, AgregarMedicamentos.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(Historial.this, Medicos.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(Historial.this, MedicamentoPendiente.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(Historial.this, CalendarioActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        //intent = new Intent(Historial.this, Historial.class);
                        //startActivity(intent);
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
            Intent intent = new Intent(Historial.this, UserActivity.class);
            startActivity(intent);
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
