package itesm.mx.proyecto_moviles;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Carlo on 11/18/2016.
 *
 * Broadcast Receiver que recibe senal de dispositivo encendidio
 * para poder inicar servicio de Medicamentos.
 *
 * Se filtran seniales en androidManifest.xml
 *
 */

public class AutoStart extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Intent startServiceIntent = new Intent(context, medServ.class);
        context.startService(startServiceIntent);
    }

}
