package itesm.mx.proyecto_moviles;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AlarmReceiver extends BroadcastReceiver {

    private ProductoOperations dao;

    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                "WakelockTag");
        wakeLock.acquire();

        int iReqCode= intent.getIntExtra("requestCode", 0);

        dao = new ProductoOperations(context);
        dao.open();

        Medicamento med = dao.findMedicamento((long) iReqCode);
        Calendar calAhora = Calendar.getInstance();
        Calendar calMedFinal = Calendar.getInstance();

        String[] sFecha = med.getHastaFecha().split("/");
        int iDia = Integer.parseInt(sFecha[0]);
        int iAnno = Integer.parseInt(sFecha[2]);
        int iMes = Integer.parseInt(sFecha[1]);

        calMedFinal.set(Calendar.YEAR, iAnno);
        calMedFinal.set(Calendar.MONTH, (iMes -1));
        calMedFinal.set(Calendar.DAY_OF_MONTH, iDia);

        calMedFinal.set(Calendar.HOUR_OF_DAY, 0);
        calMedFinal.set(Calendar.MINUTE,0);
        calMedFinal.set(Calendar.SECOND,0);
        calMedFinal.set(Calendar.MILLISECOND,0);

        boolean bAlarmaVigente = calMedFinal.getTimeInMillis() > calAhora.getTimeInMillis();
        if((iReqCode!= 0)&&(bAlarmaVigente)){

            NotificationCompat.Builder mBuilder = (NotificationCompat.Builder)
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.ic_alarm_black_24dp)
                            .setContentTitle("Medicamentos")
                            .setContentText("Alerta: Debes tomar medicina")
                            .setTicker("Hora de tomar medicina");

            Intent resultIntent = new Intent(context, MedicamentoPendiente.class);

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(MedicamentoPendiente.class);

            //cont

            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent =
                    stackBuilder.getPendingIntent(
                            0,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );//usar iReqCode previo?
            mBuilder.setContentIntent(resultPendingIntent);

            NotificationManager mNotificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            int nID = 0;
            // mId allows you to update the notification later on.
            mNotificationManager.notify(nID, mBuilder.build());
        }
        else{

            //overwrite alarm
            AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent alarmIntent = new Intent(context, AlarmReceiver.class);
            alarmIntent.putExtra("reqCode", iReqCode);
            String ALARMA_ACTION = "itesm.mx.proyecto_moviles.ALARMA";
            alarmIntent.setAction(ALARMA_ACTION);

            PendingIntent pending = PendingIntent.getBroadcast(context,
                    iReqCode, intent, 0);

            //cancel alarm
            alarmMgr.cancel(pending);

        }

        dao.close();
        wakeLock.release();
    }

}