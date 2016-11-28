package itesm.mx.proyecto_moviles;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.widget.Toast;

/**
 * Created by Carlo on 11/18/2016.
 *
 * Broadcast Receiver que recibe senal de dispositivo encendidio
 * para poder inicar servicio de Medicamentos.
 *
 * Se filtran seniales en androidManifest.xml
 *
 */

public class AutoStart extends WakefulBroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Intent startServiceIntent = new Intent(context, medServ.class);
        startWakefulService(context, startServiceIntent);
    }

}
