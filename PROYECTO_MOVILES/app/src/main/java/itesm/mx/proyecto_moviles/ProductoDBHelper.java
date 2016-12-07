/*
* D.R.© Instituto Tecnológico y de Estudios Superiores de Monterrey, México, 2016.
* Se prohíbe la reproducción total o parcial de esta obra por cualquier medio sin previo y
* expreso consentimiento por escrito del Instituto Tecnológico y de Estudios Superiores de Monterrey.
*/
package itesm.mx.proyecto_moviles;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ProductoDBHelper extends SQLiteOpenHelper {
    //DataBase name
    private static final String DATABASE_NAME = "RecuerdameMed.db";
    private static final int DATABASE_VERSION = 1;

    //Name of the tables
    private static final String TABLE_MED = "Medicamento";
    private static final String TABLE_USRS = "Usuario";
    private static final String TABLE_DOCS = "Doctor";
    private static final String TABLE_FECHAS = "Fechas"; // checar
    private static final String TABLE_HORA = "Hora";

    private static final String TABLE_HIST = "Historial";

    //fields of table Medicamentos
    private static final String MED_NOMBRE = "nombre";
    private static final String MED_TIPO = "tipo";
    private static final String MED_DOSIS = "dosis";
    private static final String MED_HORAINICIO = "horainicio";
    private static final String MED_TOMARCADA = "tomarcada";
    private static final String MED_COMENTARIOS = "comentarios";
    private static final String MED_FOTOID = "fotoid";
    private static final String MED_FECHAINICIO = "fechainicio";
    private static final String MED_HASTAFECHA = "hastafecha";

    //fields of table Usuarios
    private static final String USRS_NOMBRE = "nombre";
    private static final String USRS_DIR = "direccion";
    private static final String USRS_TELEFONO = "telefono";
    //private static final String USRS_CORREO = "correo";
    private static final String USRS_SEXO = "sexo";
    private static final String USRS_FECHANACI = "fechaNacimiento";
    private static final String USRS_PESO = "peso";
    private static final String USRS_ALTURA = "altura";

    //fields of table Doctores
    private static final String DOCS_NOMBRE = "nombre";
    private static final String DOCS_ESPECIALIDAD = "especialidad";
    private static final String DOCS_DIR = "direccion";
    private static final String DOCS_CODIGOPOS = "codigopos";
    private static final String DOCS_CIUDAD = "ciudad";
    private static final String DOCS_TELEFONO = "telefono";
    private static final String DOCS_CORREO = "correo";

    //checar/eliminar
    //fields of table fechas
    private static final String FECHAS_TSTAMP = "tstamp";

    //fields of table Historial
    //private static final String HIST_ID = "ID";
    private static final String HIST_MEDICAMENTO = "medicamento";
    private static final String HIST_DOSIS = "dosis";
    private static final String HIST_HORARIO = "horario";
    private static final String HIST_FECHA = "fecha";
    private static final String HIST_TOMADA = "tomada";

    //fields of table Historial
    private static final String HORA_MEDICAMENTO = "medicamento";
    private static final String HORA_HORARIO = "horario";
    private static final String HORA_FECHA = "fecha";

    //Create table queries
    private static final String CREATE_TABLE_MED = "CREATE TABLE " +
            TABLE_MED + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                               MED_NOMBRE + " TEXT, " + MED_TIPO + " TEXT, " +
                               MED_DOSIS + " REAL, " + MED_HORAINICIO + " TEXT, " +
                               MED_TOMARCADA + " REAL, " + MED_COMENTARIOS + " TEXT, " +
                               MED_FECHAINICIO + " TEXT, " + MED_HASTAFECHA + " TEXT)";

    private static final String CREATE_TABLE_USRS = "CREATE TABLE " +
            TABLE_USRS + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + USRS_NOMBRE + " TEXT, " + USRS_DIR + " TEXT, " +
				                USRS_TELEFONO + " TEXT, " + //USRS_CORREO + " PRIMARY KEY TEXT, " +
                                USRS_SEXO + " TEXT, " + USRS_FECHANACI + " TEXT, " +
                                USRS_PESO + " REAL, " + USRS_ALTURA + " REAL)";

    private static final String CREATE_TABLE_DOCS = "CREATE TABLE " +
            TABLE_DOCS + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + DOCS_NOMBRE + " TEXT, " + DOCS_ESPECIALIDAD + " TEXT, " +
                                DOCS_DIR + " TEXT, " + DOCS_CODIGOPOS + " INT, " + DOCS_TELEFONO + " TEXT, " +
                                DOCS_CIUDAD + " TEXT, " + DOCS_CORREO + " TEXT)";

    //checar/eliminar
    private static final String CREATE_TABLE_FECHAS = "CREATE TABLE " +
            TABLE_FECHAS + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + FECHAS_TSTAMP + " INTEGER)";


    private static final String CREATE_TABLE_HIST = "CREATE TABLE " +
            TABLE_HIST + "(ID INTEGER, " + HIST_MEDICAMENTO + " TEXT, " + HIST_DOSIS + " REAL, " +
                            HIST_HORARIO + " TEXT, " + HIST_FECHA + " TEXT, " + HIST_TOMADA + " INTEGER, " +
                            "PRIMARY KEY (ID, " + HIST_MEDICAMENTO + ")"+")" ;

    private static final String CREATE_TABLE_HORA = "CREATE TABLE " +
            TABLE_HORA + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + HORA_MEDICAMENTO + " REAL, " +
            HORA_HORARIO + " TEXT, " + HIST_FECHA + " TEXT)";

    public ProductoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("Producthelper onCreate", CREATE_TABLE_MED);
        db.execSQL(CREATE_TABLE_MED);

        Log.i("Producthelper onCreate", CREATE_TABLE_USRS);
        db.execSQL(CREATE_TABLE_USRS);

        Log.i("Producthelper onCreate", CREATE_TABLE_DOCS);
        db.execSQL(CREATE_TABLE_DOCS);

        //checar
        Log.i("Producthelper onCreate", CREATE_TABLE_FECHAS);
        db.execSQL(CREATE_TABLE_FECHAS);

        Log.i("Producthelper onCreate", CREATE_TABLE_HIST);
        db.execSQL(CREATE_TABLE_HIST);

        Log.i("Producthelper onCreate", CREATE_TABLE_HORA);
        db.execSQL(CREATE_TABLE_HORA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ProductoDBHelper.class.getName(),
                "Upgrading database from version " +
                        oldVersion +
                        " to " +
                        newVersion +
                        ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MED);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USRS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FECHAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIST);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
