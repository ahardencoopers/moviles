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
    private static final String TABLE_MED = "Medicamentos";
    private static final String TABLE_USRS = "Usuarios";
    private static final String TABLE_DOCS = "Doctores";

    //fields of table Medicamentos
    private static final String MED_NOMBRE = "nombre";
    private static final String MED_TIPO = "tipo";
    private static final String MED_DOSIS = "dosis";
    private static final String MED_HORAINICIO = "horainicio";
    private static final String MED_TOMARCADA = "tomarcada";
    private static final String MED_COMENTARIOS = "comentarios";
    private static final String MED_FOTOID = "fotoid";
    private static final String MED_HASTAFECHA = "hastafecha";

    //fields of table Usuarios
    private static final String USRS_NOMBRE = "nombre";
    private static final String USRS_DIR = "direccion";
    private static final String USRS_TELEFONO = "telefono";
    private static final String USRS_SEXO = "sexo";
    private static final String USRS_FECHANACI = "fechanaci";
    private static final String USRS_PESO = "peso";
    private static final String USRS_ALTURA = "altura";

    //fields of table Doctores
    private static final String DOCS_NOMBRE = "nombre";
    private static final String DOCS_ESP = "especialidad";
    private static final String DOCS_DIR = "direccion";
    private static final String DOCS_CODIGOPOS = "codigopos";
    private static final String DOCS_NUMERO = "numero";
    private static final String DOCS_TELEFONO = "telefono";
    private static final String DOCS_CORREO = "correo";

    //Create table queries
    private static final String CREATE_TABLE_MED = "CREATE TABLE " +
            TABLE_MED + " (" + MED_NOMBRE + "PRIMARY KEY STRING, " + MED_TIPO + " STRING, " + 
                               MED_DOSIS + " REAL, " + MED_TOMARCADA + " REAL, " +
                               MED_HORAINICIO + "STRING, " + MED_COMENTARIOS + "STRING, " +
                               MED_FOTOID + "INT, " + MED_HASTAFECHA + "STRING)";

    private static final String CREATE_TABLE_USRS = "CREATE TABLE " +
            TABLE_USRS + " (" + USRS_NOMBRE + "PRIMARY KEY STRING, " + USRS_DIR + " STRING, " +
				USRS_TELEFONO + " STRING, " + USRS_TELEFONO + " STRING, " +
                                USRS_SEXO + " STRING, " + USRS_FECHANACI + " STRING, " +
                                USRS_PESO + " REAL, " + USRS_ALTURA + " REAL)";

    private static final String CREATE_TABLE_DOCS = "CREATE TABLE " +
            TABLE_DOCS + " (" + DOCS_NOMBRE + "PRIMARY KEY STRING, " + DOCS_ESPECIALIDAD + " STRING, " +
                                DOCS_DIR + " STRING, " + DOCS_CODIGOPOS + " STRING, " +
                                DOCS_NUMERO + " STRING, " + DOCS_CORREO + " STRING)";

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

        insertTipos(db);
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
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
