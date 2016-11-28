package itesm.mx.proyecto_moviles;

import android.content.Intent;
import android.content.res.Configuration;
import android.icu.text.RelativeDateTimeFormatter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MedicamentoPendiente extends AppCompatActivity {
    ListView lvMedicamentoPendiente;
    //Drawer
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    private ProductoOperations dao;

    public long getDifDays(String fechaInicio, String fechaFin) {
        DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        long diferencia = -1;
        try {
            Date dateStart = format.parse(fechaInicio);
            Date dateEnd = format.parse(fechaFin);
            diferencia = Math.round((dateEnd.getTime() - dateStart.getTime()) / (double) 86400000);
            Log.d("FECHA", dateStart.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    return diferencia;
    }

    public ArrayList<MedicamentoPorTomar> getDataForListView() {
        MedicamentoPorTomar medicamentoPorTomar;

        ArrayList<Medicamento> listMedicamentos = new ArrayList<Medicamento>();

        //fecha de hoy
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String formattedDate = dateFormat.format(date); //2016/11/16 12:08:43
        int horas;
        double tomarhora;

        listMedicamentos = dao.getAllMedicamentos();

        ArrayList<MedicamentoPorTomar> listMedicamentosPorTomar = new ArrayList<MedicamentoPorTomar>();

        double dFechaAct = Double.parseDouble(formattedDate.replace("/",""));
        for (int i = 0; i < listMedicamentos.size(); i++) {
            String fechaInicio = listMedicamentos.get(i).getFechaInicio();
            String horario = listMedicamentos.get(i).getHorario();
            String cadahora = listMedicamentos.get(i).getTomarCada();
            tomarhora = Double.parseDouble(cadahora);
            horas = Integer.parseInt(horario.split(":")[0]);
            long dias = getDifDays(fechaInicio, formattedDate);
            double dFechaIn = Double.parseDouble(fechaInicio.replace("/",""));
            for (int j = 0; j < dias-1; j++) {
                while (horas <= 24) {
                    horas+=tomarhora;
                }
                horas = horas%24;
            }
            System.out.println(Double.toString(dFechaIn) + " " + Double.toString(dFechaAct));
            while((dFechaIn < dFechaAct) && (horas - tomarhora > 0)) {
                horas -= tomarhora;
            }
            while (horas <= 24) {
                medicamentoPorTomar = new MedicamentoPorTomar(R.drawable.logo,
                        listMedicamentos.get(i).getNombre(), String.valueOf(listMedicamentos.get(i).getDosis()),
                        String.valueOf(horas) + ":" + horario.split(":")[1], false, formattedDate);
                listMedicamentosPorTomar.add(medicamentoPorTomar);
                if(dao.getHistorialByDate(formattedDate).size() <= 0) {
                    dao.addHistorial(medicamentoPorTomar);
                }
                horas += tomarhora;
            }
        }
        return listMedicamentosPorTomar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento_pendiente);

        //Drawer
        mDrawerList = (ListView)findViewById(R.id.navList);mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        addDrawerItems();
        setupDrawer();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //inicio de base de datos
        dao = new ProductoOperations(this);
        dao.open();

        lvMedicamentoPendiente = (ListView) findViewById(R.id.list_medicamento_pendiente);
        ArrayList <MedicamentoPorTomar> arrayListMedicamentoPorTomar;
        arrayListMedicamentoPorTomar = getDataForListView();

        MedicamentoPorTomarAdapter adapterMedicamentosPorTomar = new MedicamentoPorTomarAdapter(this, arrayListMedicamentoPorTomar);
        lvMedicamentoPendiente.setAdapter(adapterMedicamentosPorTomar);
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
                        intent = new Intent(MedicamentoPendiente.this, AgregarMedicamentos.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MedicamentoPendiente.this, Medicos.class);
                        startActivity(intent);
                        break;
                    case 2:
                        //intent = new Intent(MedicamentoPendiente.this, MedicamentoPendiente.class);
                        //startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(MedicamentoPendiente.this, CalendarioActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(MedicamentoPendiente.this, Historial.class);
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
            Intent intent = new Intent(MedicamentoPendiente.this, UserActivity.class);
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
