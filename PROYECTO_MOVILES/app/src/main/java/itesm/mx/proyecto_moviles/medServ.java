package itesm.mx.proyecto_moviles;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.content.Intent;
import android.os.IBinder;

public class medServ extends Service {

    public medServ() {

    }

    public int onStartCommand(Intent intent, int flags, int startId) {

        //return STICKY
    }

    @Override
    public IBinder onBind(Intent intent) {
        //No binding
        return null;

        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
    }
}