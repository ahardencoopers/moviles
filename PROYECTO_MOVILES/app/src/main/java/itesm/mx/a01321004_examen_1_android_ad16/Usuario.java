package itesm.mx.a01321004_examen_1_android_ad16;

import android.content.SharedPreferences;
import android.util.ArraySet;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Omar Garcia on 9/14/2016.
 */
public class Usuario {
    private final String KEY_USERS = "Usuarios";
    private ArrayList<String> Usuarios;
    private SharedPreferences settings;

    Usuario(SharedPreferences config) {
        settings = config;
        Set mySetBack = settings.getStringSet(KEY_USERS, null);
        //Convert Your Set to List again
        if(mySetBack != null)
            Usuarios = new ArrayList<String>(mySetBack);
        //System.out.println(Usuarios.get(0)+ " " + Usuarios.get(1));
    }

    String registrarUsuario(String sEmail, String sPass) {
        if(!existeUsuario(sEmail, sPass)) {
            if(Usuarios == null)
                Usuarios = new ArrayList<String>();
            Usuarios.add(sEmail + " " + sPass);
            //Usuarios.add(sPass);
            Set<String> set = new HashSet<>(Usuarios);
            SharedPreferences.Editor editor = settings.edit();
            editor.putStringSet(KEY_USERS, set);
            editor.commit();
            return"";
        }
        else {
            return "El usuario " + sEmail + " ya existe";
        }
    }

    boolean existeUsuario(String sEmail, String sPass) {
        if(Usuarios == null)
            return false;
        else
            return Usuarios.indexOf(sEmail + " " + sPass) >= 0;
    }

    boolean iniciarSesion(String sMail, String sPass) {
        System.out.println(sMail + " " + sPass);
        if(existeUsuario(sMail, sPass)) {
            System.out.println("Entra");
            int iPos = Usuarios.indexOf(sMail + " " + sPass);
            if(iPos > -1 )
                return true;
            else
                return false;
        }
        return false;
    }

}
