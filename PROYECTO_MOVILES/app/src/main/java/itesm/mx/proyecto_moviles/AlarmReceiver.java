package itesm.mx.proyecto_moviles;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    private ProductoOperations dao;

    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        //int code= pIntent.getIntExtra("requestCode", 1);

        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder)
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_alarm_black_24dp)
                        .setContentTitle("Medicamentos")
                        .setContentText("Alerta: Debes tomar medicina")
                        .setTicker("Hora de tomar medicina");

        Intent resultIntent =  new Intent(context, MedicamentoPendiente.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MedicamentoPendiente.class);

        //cont

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(mId, mBuilder.build());
    }


}
