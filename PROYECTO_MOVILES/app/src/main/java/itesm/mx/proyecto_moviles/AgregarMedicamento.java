package itesm.mx.proyecto_moviles;


//ch
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;


import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;

public class AgregarMedicamento extends AppCompatActivity implements View.OnClickListener {

    //Varibles
    private Spinner spinnerTipoMedicamento = null;
    private String sTipoMedicamento[] = {"Pastilla", "Comprimido", "Tableta", "Topico", "Sublingual"};
    private Button btnGuardarMedicamento = null;
    private EditText etNombre;
    private EditText etDosis;
    private TextView etHora;
    private EditText etTomarCada;
    private TextView tvFechafin;
    private EditText etComentarios;

    private ProductoOperations dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_medicamento);

        //inicio de base de datos
        dao = new ProductoOperations(this);
        dao.open();

        btnGuardarMedicamento = (Button) findViewById(R.id.boton_guardar_medicamento);
        etNombre = (EditText) findViewById(R.id.nombre_medicina);
        etDosis = (EditText) findViewById(R.id.edit_dosis);
        etHora = (TextView) findViewById(R.id.edit_hora_inicio);
        etTomarCada = (EditText) findViewById(R.id.edit_tomar_cada);
        tvFechafin = (TextView) findViewById(R.id.edit_fechafin);
        etComentarios = (EditText) findViewById(R.id.edit_comentario);

        spinnerTipoMedicamento = (Spinner) findViewById(R.id.spinner_tipo_medicina);
        ArrayAdapter<String> adapterSpinnerTipoMedicamento= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sTipoMedicamento);

        spinnerTipoMedicamento.setAdapter(adapterSpinnerTipoMedicamento);
        btnGuardarMedicamento.setOnClickListener(this);
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.boton_guardar_medicamento:
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat mdformat = new SimpleDateFormat("dd/MM/yyyy");
                String nombre = etNombre.getText().toString();
                String dosis = etDosis.getText().toString();
                String horainicio = etHora.getText().toString();
                String cadaHora = etTomarCada.getText().toString();
                String fechainicio = mdformat.format(calendar.getTime());
                String fechafin = tvFechafin.getText().toString();
                String comentarios = etComentarios.getText().toString();

                if (!TextUtils.isEmpty(nombre) && !TextUtils.isEmpty(dosis) && !TextUtils.isEmpty(horainicio) && !TextUtils.isEmpty(cadaHora) &&
                        !TextUtils.isEmpty(fechafin)) {

                Calendar cal = Calendar.getInstance();
                String[] sHora = horainicio.split(":");
                int iHora = Integer.parseInt(sHora[0]);
                int iMinu = Integer.parseInt(sHora[1]);
                int iIntervalo = Integer.parseInt(cadaHora);
                System.out.println(iMinu);
                String[] sFecha = fechainicio.split("/");
                int iDia = Integer.parseInt(sFecha[0]);
                int iAnno = Integer.parseInt(sFecha[2]);
                int iMes = Integer.parseInt(sFecha[1]);

                cal.set(Calendar.YEAR, iAnno);
                cal.set(Calendar.MONTH, (iMes -1));
                cal.set(Calendar.DAY_OF_MONTH, iDia);

                cal.set(Calendar.HOUR_OF_DAY, iHora);
                cal.set(Calendar.MINUTE,iMinu);
                cal.set(Calendar.SECOND,0);
                cal.set(Calendar.MILLISECOND,0);

                    Calendar auxCal = Calendar.getInstance();
                    if (cal.before(auxCal)) {
                        cal.add(Calendar.HOUR_OF_DAY, iIntervalo);
                    }

                boolean bAlarmInFuture = (cal.getTimeInMillis() > calendar.getTimeInMillis());

                    Medicamento medicamento = new Medicamento(nombre,
                            spinnerTipoMedicamento.getSelectedItem().toString(),
                            Double.valueOf(dosis), horainicio, cadaHora, comentarios, fechainicio, fechafin);

                    long index = dao.addMedicamento(medicamento);
                    int iReqCode = (int) index;
                    //int iReqCode = (int) dao.addFecha(cal.getTimeInMillis());


                    AlarmManager alarmMgr = (AlarmManager)getSystemService(ALARM_SERVICE);
                    Intent intent = new Intent(this, AlarmReceiver.class);
                    intent.putExtra("reqCode", iReqCode);
                    String ALARMA_ACTION = "itesm.mx.proyecto_moviles.ALARMA";
                    intent.setAction(ALARMA_ACTION);

                    PendingIntent pending = PendingIntent.getBroadcast(this.getApplicationContext(),
                            iReqCode, intent, 0);

                    alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,
                            cal.getTimeInMillis(), //hora de prender alarma
                            (long) (iIntervalo*3600*1000), //intervalo de tiempo
                            pending);

                    dao.close();

                    Toast.makeText(AgregarMedicamento.this, "Medicamento Registrado!", Toast.LENGTH_SHORT).show();
                    Log.i("HORA", horainicio);
                    finish();
                    Intent ret = new Intent(this, MedicamentoPendiente.class);
                    startActivity(ret);
                }
                if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(dosis) || TextUtils.isEmpty(horainicio) || TextUtils.isEmpty(cadaHora) ||
                        TextUtils.isEmpty(fechafin)) {
                    Toast.makeText(AgregarMedicamento.this, "Error al registrar Medicamento, llene todos los campos e intente de nuevo.", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

}
