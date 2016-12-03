package itesm.mx.proyecto_moviles;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class medServ extends IntentService {

    private ProductoOperations dao;

    public medServ(String name) {
        super(name);
    }

    @Override
    public void onDestroy() {
        Log.d("DEBUG", "MedServe done");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        dao = new ProductoOperations(this);
        dao.open();

        ArrayList<Medicamento> arrMed = dao.getAllMedicamentos();
        Medicamento med;
        Calendar calAhora = Calendar.getInstance();


        for(int i=0;i<arrMed.size();i++){
            med = arrMed.get(i);

            //checar que medicamento no haya vencido
            Date dActual = new Date();
            Date dMedFinal = new Date();
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                dActual = dateFormat.parse(dateFormat.format(calAhora.getTime()));
                dMedFinal = dateFormat.parse(med.getHastaFecha());
            } catch (java.text.ParseException e) {
                Log.d("DEBUG","Error al parsear en servicio");
            }

            //checar fecha fin medicina despues de fecha actual
            if (dMedFinal.compareTo(dActual) >= 0){

                Calendar calMed = Calendar.getInstance();

                String[] sHora = med.getHorario().split(":");
                int iHora = Integer.parseInt(sHora[0]);
                int iMinu = Integer.parseInt(sHora[1]);
                long lIntervalo = (long) Integer.parseInt(med.getTomarCada());

                String[] sFecha = med.getFechaInicio().split("/");
                int iDia = Integer.parseInt(sFecha[0]);
                int iAnno = Integer.parseInt(sFecha[2]);
                int iMes = Integer.parseInt(sFecha[1]);

                calMed.set(Calendar.YEAR, iAnno);
                calMed.set(Calendar.MONTH, (iMes - 1));//month va de 0 a 11
                calMed.set(Calendar.DAY_OF_MONTH, iDia);

                calMed.set(Calendar.HOUR_OF_DAY, iHora);
                calMed.set(Calendar.MINUTE, iMinu);
                calMed.set(Calendar.SECOND, 0);
                calMed.set(Calendar.MILLISECOND, 0);

                //tiempo de intervalo ya transcurrido
                // (tCurrent - tStart)%(Intervalo (en milis))
                long interElapsed = (calAhora.getTimeInMillis() - calMed.getTimeInMillis())%
                        (lIntervalo*3600*1000);

                //nueva fecha en (intervalo - interElapsed) + tCurrent
                long alarmNewStart = (lIntervalo*3600*1000) - interElapsed + calAhora.getTimeInMillis();


                int iReqCode = (int) med.getId();//id med como alarm id
                AlarmManager alarmMgr = (AlarmManager)getSystemService(ALARM_SERVICE);
                Intent alarmIntent = new Intent(this, AlarmReceiver.class);
                alarmIntent.putExtra("reqCode", iReqCode);
                String ALARMA_ACTION = "itesm.mx.proyecto_moviles.ALARMA";
                alarmIntent.setAction(ALARMA_ACTION);

                PendingIntent pending = PendingIntent.getBroadcast(this.getApplicationContext(),
                        iReqCode, alarmIntent, 0);

                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,
                        alarmNewStart, //hora de prender alarma
                        (long) (lIntervalo*3600*1000), //intervalo de tiempo
                        pending);
            }
        }
        dao.close();
        AutoStart.completeWakefulIntent(intent);
    }
}