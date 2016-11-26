package itesm.mx.proyecto_moviles;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.TextView;

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
                String nombre = etNombre.getText().toString();
                String dosis = etDosis.getText().toString();
                String horainicio = etHora.getText().toString();
                String cadaHora = etTomarCada.getText().toString();
                String fechafin = tvFechafin.getText().toString();
                String comentarios = etComentarios.getText().toString();
                if (etNombre.getText() != null && etDosis.getText() != null
                        && etHora.getText() != null && etTomarCada.getText() != null &&
                        tvFechafin.getText() != null) {
                    Medicamento medicamento = new Medicamento(nombre,
                            spinnerTipoMedicamento.getSelectedItem().toString(),
                            Double.valueOf(dosis), horainicio, cadaHora, comentarios, fechafin);
                    long index = dao.addMedicamento(medicamento);

                    //checar para horas antes de actual

                    Calendar cal = Calendar.getInstance();
                    String[] sHora = horainicio.split(":");
                    int iHora = Integer.parseInt(sHora[0]);
                    int iMinu = Integer.parseInt(sHora[1]);
                    int iIntervalo = Integer.parseInt(cadaHora);


                    cal.set(Calendar.HOUR_OF_DAY, iHora);
                    cal.set(Calendar.MINUTE,iMinu);
                    cal.set(Calendar.SECOND,0);
                    cal.set(Calendar.MILLISECOND,0);

                    int iReqCode = (int) dao.addFecha(cal.getTimeInMillis());

                    AlarmManager alarmMgr = (AlarmManager)getSystemService(ALARM_SERVICE);
                    Intent intent = new Intent(this, AlarmReceiver.class);
                    intent.putExtra("reqCode", iReqCode);
                    String ALARMA_ACTION = "itesm.mx.proyecto_moviles.ALARMA";
                    intent.setAction(ALARMA_ACTION);

                    PendingIntent pending = PendingIntent.getBroadcast(this.getApplicationContext(),
                            iReqCode, intent, 0);

                    alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                            cal.getTimeInMillis(), //hora de prender alarma
                            (long) (iIntervalo*3600*1000), //intervalo de tiempo
                            pending);

                    dao.close();

                    finish();
                }
                break;
        }
    }


}
