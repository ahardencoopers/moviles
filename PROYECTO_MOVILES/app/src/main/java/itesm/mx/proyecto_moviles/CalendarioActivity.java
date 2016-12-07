package itesm.mx.proyecto_moviles;

import android.content.Intent;
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
import android.widget.ListView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

public class CalendarioActivity extends AppCompatActivity {

    private ListView listCalendario;
    private static CalendarioAdapter calendarioAdapter;
    //Drawer
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    private ProductoOperations dao;

    public ArrayList<Medicamento> getDataForCalendario() {
        ArrayList<CalendarioEntry> listCalendarioEntry = new ArrayList<CalendarioEntry>();
        ArrayList<Medicamento> listMedicamentos = new ArrayList<Medicamento>();
        CalendarioEntry temp;
        int dias = 0;

        dao = new ProductoOperations(this);
        dao.open();

        listMedicamentos = dao.getAllMedicamentos();

        while(dias < 5) {
            Calendar cCalendario = Calendar.getInstance();
            int dia = (cCalendario.get(Calendar.DAY_OF_MONTH) + dias);
            int mes =  (cCalendario.get(Calendar.MONTH) + 1);
            int year = cCalendario.get(Calendar.YEAR);

            temp = new CalendarioEntry(Integer.toString(dia) + "/" + Integer.toString(mes) + "/" + Integer.toString(year));
            calendarioAdapter.addSeparatorItem(temp);

            Log.d("Calendario temp", temp.getSeparador());

            for(int i = 0; i < listMedicamentos.size(); i++) {
                Medicamento actual = listMedicamentos.get(i);

                Log.d("Calendario med", actual.getHastaFecha());

                String[] fechaMed = actual.getHastaFecha().split("/");
                int diaMed = Integer.parseInt(fechaMed[0]);
                int mesMed = Integer.parseInt(fechaMed[1]);
                int yearMed = Integer.parseInt(fechaMed[2]);

                if(year <= yearMed) {
                    if(mes - 1 <= mesMed) {
                        if(dia <= diaMed) {
                            temp = new CalendarioEntry(actual.getId(), actual.getNombre(), actual.getTipo(), actual.getDosis(), actual.getHorario(),
                                    actual.getTomarCada(), actual.getComentarios(), actual.getHastaFecha());
                            calendarioAdapter.addItem(temp);
                        }
                    }
                }

            }

            dias++;
        }

        return listMedicamentos;
    }

    //Calendario
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        //Drawer
        mDrawerList = (ListView)findViewById(R.id.navList)
        ;mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        addDrawerItems();
        setupDrawer();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        listCalendario = (ListView) findViewById(R.id.list_calendario);
        calendarioAdapter = new CalendarioAdapter(this);

        this.getDataForCalendario();

        listCalendario.setAdapter(calendarioAdapter);
    }

    //Drawer
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
                        intent = new Intent(CalendarioActivity.this, AgregarMedicamentos.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(CalendarioActivity.this, Medicos.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(CalendarioActivity.this, MedicamentoPendiente.class);
                        startActivity(intent);
                        break;
                    case 3:
                        //intent = new Intent(CalendarioActivity.this, CalendarioActivity.class);
                        //startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(CalendarioActivity.this, Historial.class);
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
            Intent intent = new Intent(CalendarioActivity.this, UserActivity.class);
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
