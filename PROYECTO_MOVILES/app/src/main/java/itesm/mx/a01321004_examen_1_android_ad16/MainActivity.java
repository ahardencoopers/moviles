package itesm.mx.a01321004_examen_1_android_ad16;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    final int REQUEST_CODE = 1;
    final String KEY_NOMBRE = "Usuario";
    final String KEY_USER = "USERSESSION";
    final String KEY_USERS = "Usuarios";
    EditText etEmail;
    EditText etPass;
    Button btnLogin;
    Usuario usUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etEmail = (EditText) findViewById(R.id.edit_email_login);
        etPass = (EditText) findViewById(R.id.edit_pass_login);
        btnLogin = (Button) findViewById(R.id.button_login);

        btnLogin.setOnClickListener(this);

        SharedPreferences settings = getSharedPreferences(KEY_USERS,MODE_PRIVATE);
        usUsers = new Usuario(getSharedPreferences(KEY_USERS,MODE_PRIVATE));

        String sUser = settings.getString(KEY_USER, "");
        /*if(sUser != ""){
            if(sUser.equals("admin@gmail.com")) {
                    Intent myIntent = new Intent(this, RegistroActivity.class);
                    startActivityForResult(myIntent, REQUEST_CODE);
            }
            else {
                Intent myIntent = new Intent(this, ArticulosActivity.class);
                myIntent.putExtra(KEY_NOMBRE, sUser);
                startActivityForResult(myIntent, REQUEST_CODE);
            }
        }*/
    }

    @Override
    public void onClick(View v) {
        String sCorreo = etEmail.getText().toString();
        System.out.println(sCorreo);
        String sPass = etPass.getText().toString();
        System.out.println(sPass);
        if(sCorreo.equals("admin@gmail.com")) {
            if(sPass.equals("admin")) {
                //implementacion del boton editar
                Intent myIntent = new Intent(this, RegistroActivity.class);
                startActivityForResult(myIntent, REQUEST_CODE);
            }
        }
        else {
            //usUsers = new Usuario(getPreferences(MODE_PRIVATE));
            if(usUsers.iniciarSesion(etEmail.getText().toString(), etPass.getText().toString())) {
                SharedPreferences settings = getSharedPreferences(KEY_USERS, MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString(KEY_USER, etEmail.getText().toString() + " " + etPass.getText().toString());
                editor.commit();
                Intent myIntent = new Intent(this, ArticulosActivity.class);
                myIntent.putExtra(KEY_NOMBRE, etEmail.getText().toString());
                startActivityForResult(myIntent, REQUEST_CODE);
            }
            else
                Toast.makeText(getApplicationContext(), "Usuario o contraseña erroneos...",
                        Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Toast.makeText(getApplicationContext(), "Gracias por usar mi tiendita!",
                        Toast.LENGTH_LONG).show();
        }
    }
}
