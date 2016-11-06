package itesm.mx.a01321004_examen_1_android_ad16;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {
    final String KEY_USER = "USERSESSION";
    final String KEY_USERS = "Usuarios";
    Usuario usUsuarios;
    EditText etEmail;
    EditText etPass;
    EditText etConfPass;
    TextView tvUser;
    Button btnGuardar;
    Button btnSalir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        usUsuarios = new Usuario(getSharedPreferences(KEY_USERS, MODE_PRIVATE));
        setContentView(R.layout.activity_registro);
        etEmail = (EditText) findViewById(R.id.edit_email_register);
        etPass = (EditText) findViewById(R.id.edit_pass_register);
        etConfPass = (EditText) findViewById(R.id.edit_passconf_register);
        btnGuardar = (Button) findViewById(R.id.button_save_register);
        btnSalir = (Button) findViewById(R.id.button_exit_register);

        tvUser = (TextView) findViewById(R.id.text_Admin_register);
        tvUser.setText("admin@gmail.com");

        btnGuardar.setOnClickListener(this);
        btnSalir.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_exit_register:
                SharedPreferences settings = getSharedPreferences(KEY_USER, MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString(KEY_USER, "");
                editor.commit();
                finish();
                break;
            case R.id.button_save_register:
                String sMail = etEmail.getText().toString();
                String sPass1 = etPass.getText().toString();
                String sPass2 = etConfPass.getText().toString();
                if(sPass1.equals(sPass2)){
                    String sError = usUsuarios.registrarUsuario(sMail, sPass1);
                    if(sError.equals(""))
                        Toast.makeText(getApplicationContext(), "Usuario registrado exitosamente!",
                            Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(), sError,
                                Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Las contraseñas no concuerdan",
                            Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
