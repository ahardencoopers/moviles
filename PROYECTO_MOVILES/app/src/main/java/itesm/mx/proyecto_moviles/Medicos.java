package itesm.mx.proyecto_moviles;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Medicos extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    //Drawer
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    private Button btnAgregarMedico;
    private ListView listMedicos;
    final int REQUEST_CODE = 1;
    ArrayList<Doctor> docDoctores;
    private DoctorAdapter dAdapter;

    ProductoOperations dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicos);
        //data base
        dao = new ProductoOperations(this);
        dao.open();

        //inicio de listview
        docDoctores = showProducts();
        dAdapter = new DoctorAdapter(this, docDoctores);


        //Drawable menu
        mDrawerList = (ListView)findViewById(R.id.navList);mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        addDrawerItems();
        setupDrawer();
        //set drawable
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //set views
        btnAgregarMedico = (Button) findViewById(R.id.button_agregar_medico);
        listMedicos = (ListView) findViewById(R.id.list_medicos);

        //Adapter Lista
        listMedicos.setAdapter(dAdapter);
        listMedicos.setOnItemClickListener(this);
        registerForContextMenu(listMedicos);

        //listeners
        btnAgregarMedico.setOnClickListener(this);

    }

    public ArrayList<Doctor> showProducts () {
        ArrayList<Doctor> List = dao.getAllDoctores();
        //System.out.println("Cantidad de medicos: " + Integer.toString(List.size()));
        if (List != null){
            return List;
        }else
            return null;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, RegistrarMedico.class);
        startActivityForResult(intent, REQUEST_CODE);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Doctor dAux = (Doctor) adapterView.getItemAtPosition(i);
        Intent intent = new Intent(this, MedicosRegistrados.class);
        System.out.println("ID manda " + dAux.getID());
        intent.putExtra("Id", dAux.getID());
        startActivityForResult(intent, REQUEST_CODE);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            dao.open();
            docDoctores.clear();
            docDoctores.addAll(showProducts());
            dAdapter.notifyDataSetChanged();
            //Toast.makeText(getApplicationContext(), "Medico Registrado!",
            //        Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int id = item.getItemId();

        if (id == R.id.delete) {
            Toast.makeText(getApplicationContext(), "Medico Borrado: " + docDoctores.get(info.position).getID(), Toast.LENGTH_LONG).show();
            dao.deleteDoctor(docDoctores.get(info.position).getID());
            docDoctores.clear();
            docDoctores.addAll(showProducts());
            dAdapter.notifyDataSetChanged();
            return true;
        }
        return super.onContextItemSelected(item);
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
                        intent = new Intent(Medicos.this, AgregarMedicamentos.class);
                        startActivity(intent);
                        break;
                    case 1:
                        //intent = new Intent(Medicos.this, Medicos.class);
                        //startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(Medicos.this, MedicamentoPendiente.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(Medicos.this, CalendarioActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(Medicos.this, Historial.class);
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
            Intent intent = new Intent(Medicos.this, UserActivity.class);
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
