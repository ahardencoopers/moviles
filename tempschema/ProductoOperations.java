/*
* D.R.© Instituto Tecnológico y de Estudios Superiores de Monterrey, México, 2016.
* Se prohíbe la reproducción total o parcial de esta obra por cualquier medio sin previo y
* expreso consentimiento por escrito del Instituto Tecnológico y de Estudios Superiores de Monterrey.
*/
package itesm.mx.proyecto_moviles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;

public class ProductoOperations {

    private static SQLiteDatabase db;
    private static ProductoDBHelper dbHelper;
    //private Electrodomestico elArticulo;
    //private Evento evEvento;

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


	public ProductoOperations(Context context) {
		dbHelper = new ProductoDBHelper(context);
	}

	public void open() throws SQLException {
		try {
		    db = dbHelper.getWritableDatabase();
		} catch (SQLException e) {
		    Log.e("SQLOPEN", e.toString());
		}
	}

	public void close() {
           db.close();
	}
	
	public long addMedicamento(Medicamento med) {
		long newRowId = 0;
		try {
			//Table Medicamentos field strings: nombre tipo dosis horainicio tomarcada comentarios fotoid hastafecha
			//Object Medicamento attributes: nombre tipo dosis horario tomarCada comentarios idImagen hastafecha
			ContentValues values = new ContentValues();
			values.put(MED_NOMBRE, med.getNombre());
			values.put(MED_TIPO, med.getTipo());
			values.put(MED_DOSIS, med.getDosis());
			values.put(MED_HORAINICIO, med.getHorario());
			values.put(MED_TOMARCADA, med.getTomarCada());
			values.put(MED_COMENTARIOS, med.getComentarios());
			values.put(MED_FOTOID, med.getIdImagen());
			values.put(MED_HASTAFECHA, med.getHastaFecha());
		
			newRowId = db.insert(TABLE_MED, null, values);
		}
		catch(SQLException e) {
			Log.e("SQL ADD ERROR", e.toString());
		}
		
		return 
	}

	public long addUsuario(Usuario usr) {
		long newRowId = 0;
		try {
			//Table Usuarios field strings: nombre direccion telefono sexo fechanaci peso altura
			//Object Usuario attributes: nombre direccion telefono sexo fechaNaci peso altura
			ContentValues values = new ContentValues();
			values.put(USRS_NOMRBE, usr.getNombre());
			values.put(USRS_DIR, usr.getDireccion());
			values.put(USRS_TELEFONO, usr.getTelefono());
			values.put(USRS_SEXO, usr.getSexo());
			values.put(USRS_FECHANACI, usr.getFechaNaci());
			values.put(USRS_PESO, usr.getPeso());
			values.put(USRS_ALTURA, usr.getAltura());

			newRowId = db.insert(TABLE_USRS, null, values);
			
		}
		catch(SQLException e) {
			Log.e("SQL ADD ERROR", e.toString());
		}
	}

	public addDoctor(Doctor doc) {
		long newRowId = 0;
		try {
			//Table Docotores  field strings: nombre especialidad direccion codigopos numero telefono correo
			//Object Doctor attributes: nombre especialidad direccion codigopos numero telefono correo
			ContentValues values = new ContentValues();
			values.put(DOCS_NOMBRE, doc.getNombre());
			values.put(DOCS_ESP, doc.getEspecialidad());
			values.put(DOCS_DIR, doc.getDireccion());
			values.put(DOCS_CODIGOPOS, doc.getCodigopos());
			values.put(DOCS_NUMERO, doc.getNumero());
			values.put(DOCS_TELEFONO, doc.getTelefono());
			values.put(DOCS_CORREO, doc.getCorreo());

		}
	}

	public Medicamento findMedicamento(String medicamentoNombre) {
		Medicamento med;
		String query = "SELECT * FROM " + TABLE_MED + " WHERE " + TABLE_MED + ".nombre = " + medicamentoNombre;

		try {
			Cursor cursor = db.rawQuery(query, null);
			if(cursor.moveToFirst()) {
				med = new Medicamento(cursor.getString(0), cursor.getString(1), cursor.getDouble(2),
					cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
			}
			cursor.close();
		}
		catch(SQLException e) {
			Log.e("SQLFIND ERROR", e.toString());
		}
		
		return med;
	}

	public Usuario findUsuario(String usuarioNombre) {
		Usuario usr;
		String query = "SELECT * FROM " + TABLE_USRS + " WHERE " + TABLE_USRS + ".nombre = " + usuarioNombre;

		try {
			Cursor cursor = db.rawQuery(query, null);
			if(cursor.moveToFirst()) {
				usr = new Usuario(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
					cursor.getString(5), cursor.getDouble(6), cursor.getDouble(7));
			}
			cursor.close();
			catch(SQLException e) {
				Log.e("SQLFIND ERROR", e.toString());
			}
		}

		return usr;
	}

	public Doctor findDoctor(String doctorNombre) {
		Doctor doc;
		String query = "SELECT * FROM " + TABLE_DOCS + " WHERE " + TABLE_DOCS + ".nombre = " + doctorNombre;

		try {
			Cursor cursor = db.rawQuery(query, null);
			if(cursor.moveToFirst()) {
				doc = new Doctor(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
					cursor.getString(5), cursor.getString(6));
			}
		}
		catch(SQLException e) {
			Log.e("SQLFIND ERROR", e.toString());
		}

		return doc;
	}

    public boolean deleteMedicamento(String nombreMedicamento) {
        boolean result = false;

        try {
                db.delete(TABLE_MED,
                        "nombre" + " = ?",
                        new String[]{nombreMedicamento});
                result = true;
            }
        } catch(SQLiteException e){
            Log.e("SQLDELETE", e.toString());
        }
        return result;
    }


	public ArrayList<String[]> getAllMedicamentos() {
		Medicamento med;
		ArrayList<Medicamento> listaMedicamentos = new ArrayList<Medicamento>();

		String selectQuery = "Select * FROM " + TABLE_MED;

		try {
			Cursor cursor = db.rawQuery(selectQuery, null);
			if (cursor.moveToFirst()) {
				do {
					med = new Medicamento(cursor.getString(0), cursor.getString(1), cursor.getDouble(2),
					cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));	
					listaTipos.add(sAux);
				} while (cursor.moveToNext());
			}
			cursor.close();
		}catch(SQLException e) {
			Log.e("SQLGETALL", e.toString());
		}
		return listaMedicamentos;
	}

	public ArrayList<String[]> getAllDoctores() {
		Doctor doc;
		ArrayList<Doctor> listaDoctores = new ArrayList<Doctor>();

		String selectQuery = "Select * FROM " + TABLE_MED;

		try {
			Cursor cursor = db.rawQuery(selectQuery, null);
			if (cursor.moveToFirst()) {
				do {
					doc = new Doctor(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
						cursor.getString(5), cursor.getString(6));
					listaTipos.add(sAux);
				} while (cursor.moveToNext());
			}
			cursor.close();
		}catch(SQLException e) {
			Log.e("SQLGETALL", e.toString());
		}
		return listDoctores;
	}
}
