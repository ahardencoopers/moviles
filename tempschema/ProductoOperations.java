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
			Log.e("SQLFind ERROR", e.toString());
		}
		
		return med;
	}

	public Usuario findUsuario(String usuarioNombre) {
		Usuario usr;
		String query = "SELECT * FROM " + TABLE_USRS
	}

	public Evento findEvento(long eventID) {

		String query = "Select " + TABLE_EVENTO + ".ID, " + TABLE_TIPO + "." + TIPO_NOMBRE + ", "
                + TABLE_TIPO + "." + TIPO_CONSUMO + ", " + TABLE_ELECTRODOMESTICO + "." + ELECT_MARCA + ", "
                + TABLE_EVENTO + "." + EVENTO_TIEMPOUSO + ", " + TABLE_EVENTO + "." + EVENTO_HORA_INICIO + ", "
                + TABLE_EVENTO + "." + EVENTO_FECHA + ", " + TABLE_ELECTRODOMESTICO + "." + ELECT_FOTO
                + " FROM " + TABLE_EVENTO + ", " + TABLE_ELECTRODOMESTICO + "," + TABLE_TIPO +
                " WHERE " + EVENTO_APARATO + " = " + TABLE_ELECTRODOMESTICO + ".ID AND " +
                ELECT_TIPOAPARATO + " = " + TABLE_TIPO + ".ID AND " + TABLE_EVENTO +
                ".ID" + " =  \"" + eventID + "\"";

        try {

            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                double dAux = cursor.getDouble(4) * cursor.getInt(2);
                evEvento = new Evento(Long.parseLong(cursor.getString(0)),
                        cursor.getString(1), cursor.getString(3), cursor.getString(6),
                        cursor.getDouble(4), cursor.getString(5), dAux, cursor.getBlob(7));

            }
            cursor.close();
            }catch(SQLException e) {
                Log.e("SQLFind", e.toString());
            }
		return evEvento;
	}

    public boolean deleteElectrodomestico(long electID) {

        boolean result = false;

        String query1 = "Select * FROM " + TABLE_EVENTO +
                " WHERE " + EVENTO_APARATO +
                " =  \"" + electID + "\"";
        String query2 = "Select * FROM " + TABLE_ELECTRODOMESTICO +
                " WHERE " + "ID" +
                " =  \"" + electID + "\"";
        try {
            Cursor cursor = db.rawQuery(query1, null);
            if (cursor.moveToFirst()) {
                int id = Integer.parseInt(cursor.getString(0));
                db.delete(TABLE_EVENTO,
                        "ID" + " = ?",
                        new String[]{String.valueOf(id)});
                result = true;
            }
            cursor.close();
            cursor = db.rawQuery(query2, null);
            if (cursor.moveToFirst()) {
                int id = Integer.parseInt(cursor.getString(0));
                db.delete(TABLE_ELECTRODOMESTICO,
                        "ID" + " = ?",
                        new String[]{String.valueOf(id)});
                result = true;
            }
            cursor.close();
        } catch(SQLiteException e){
            Log.e("SQLDELETE", e.toString());
        }
        return result;
    }


	public boolean deleteEvent(long eventID) {
			
		boolean result = false;

		String query = 	"Select * FROM " + TABLE_EVENTO +
				        " WHERE " + "ID" +
				        " =  \"" + eventID + "\"";
        try {
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                int id = Integer.parseInt(cursor.getString(0));
                db.delete(TABLE_EVENTO,
                        "ID" + " = ?",
                        new String[]{String.valueOf(id)});
                result = true;
            }
            cursor.close();
        } catch(SQLiteException e){
            Log.e("SQLDELETE", e.toString());
        }
		return result;
	}

    public ArrayList<String[]> getAllTypes() {

        ArrayList<String[]> listaTipos = new ArrayList<String[]>();

        String selectQuery = "Select * FROM " + TABLE_TIPO;

        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    String [] sAux = new String[3];
                    sAux[0] = cursor.getString(0);
                    sAux[1] = cursor.getString(1);
                    sAux[2] = cursor.getString(2);
                    listaTipos.add(sAux);

                } while (cursor.moveToNext());
            }
            cursor.close();
        }catch(SQLException e) {
            Log.e("SQLList", e.toString());
        }
        return listaTipos;
    }
		
	public ArrayList<Evento> getAllEvents() {

		ArrayList<Evento> listaProducts = new ArrayList<Evento>();

		String selectQuery = "Select " + TABLE_EVENTO + ".ID, " + TABLE_TIPO + "." + TIPO_NOMBRE + ", "
                + TABLE_TIPO + "." + TIPO_CONSUMO + ", " + TABLE_ELECTRODOMESTICO + "." + ELECT_MARCA + ", "
                + TABLE_EVENTO + "." + EVENTO_TIEMPOUSO + ", " + TABLE_EVENTO + "." + EVENTO_HORA_INICIO + ", "
                + TABLE_EVENTO + "." + EVENTO_FECHA + ", " + TABLE_ELECTRODOMESTICO + "." + ELECT_FOTO
                + " FROM " + TABLE_EVENTO + ", " + TABLE_ELECTRODOMESTICO + "," + TABLE_TIPO
                + " WHERE " + TABLE_EVENTO + "." + EVENTO_APARATO + " = " + TABLE_ELECTRODOMESTICO + ".ID AND "
                + TABLE_ELECTRODOMESTICO + "." + ELECT_TIPOAPARATO + " = " + TABLE_TIPO + ".ID";

        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    evEvento = null;
                    double dAux = cursor.getDouble(4) * cursor.getInt(2);
                    evEvento = new Evento(Long.parseLong(cursor.getString(0)),
                            cursor.getString(1), cursor.getString(3), cursor.getString(6),
                            cursor.getDouble(4), cursor.getString(5), dAux, cursor.getBlob(7));
                    listaProducts.add(evEvento);

                } while (cursor.moveToNext());
            }
            cursor.close();
        }catch(SQLException e) {
            Log.e("SQLList", e.toString());
        }
	    return listaProducts;
	}

    public ArrayList<Electrodomestico> getAllElectrodomesticos() {

        ArrayList<Electrodomestico> listaProducts = new ArrayList<Electrodomestico>();

        String selectQuery = "Select " + TABLE_ELECTRODOMESTICO + ".ID, "
                + TABLE_TIPO + "." + TIPO_NOMBRE + ", "
                + TABLE_ELECTRODOMESTICO + "." + ELECT_MARCA + ", "
                + TABLE_ELECTRODOMESTICO + "." + ELECT_FOTO
                + " FROM " + TABLE_ELECTRODOMESTICO + ", " + TABLE_TIPO
                + " WHERE " + TABLE_ELECTRODOMESTICO + "." + ELECT_TIPOAPARATO + " = " + TABLE_TIPO + ".ID";

        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    elArticulo = null;
                    elArticulo = new Electrodomestico(Long.parseLong(cursor.getString(0)),
                            cursor.getString(1), cursor.getString(2), cursor.getBlob(3));
                    listaProducts.add(elArticulo);
                    //System.out.println(cursor.getString(0) + " " + cursor.getString(1) + " " + cursor.getString(2));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }catch(SQLException e) {
            Log.e("SQLList", e.toString());
        }
        return listaProducts;
    }

    public ArrayList<Integer>  getAllEventIds() {

        ArrayList<Integer> listaIds = new ArrayList<Integer>();

        String selectQuery = "Select " + TABLE_EVENTO + ".ID " + " FROM " + TABLE_EVENTO;

        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    listaIds.add(cursor.getInt(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }catch(SQLException e) {
            Log.e("SQLList", e.toString());
        }
        return listaIds;

    }

    public ArrayList<String>  getAllEventDates() {

        ArrayList<String> listaDates = new ArrayList<String>();

        String selectQuery = "Select " + TABLE_EVENTO + "." +EVENTO_FECHA + " FROM " + TABLE_EVENTO
                + " GROUP BY " + EVENTO_FECHA;

        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    listaDates.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }catch(SQLException e) {
            Log.e("SQLList", e.toString());
        }
        return listaDates;
    }

    public ArrayList<Evento> getAllEventsByDate(String sDATE) {

        ArrayList<Evento> listaProducts = new ArrayList<Evento>();

        String selectQuery = "Select " + TABLE_EVENTO + ".ID, " + TABLE_TIPO + "." + TIPO_NOMBRE + ", "
                + TABLE_TIPO + "." + TIPO_CONSUMO + ", " + TABLE_ELECTRODOMESTICO + "." + ELECT_MARCA + ", SUM("
                + TABLE_EVENTO + "." + EVENTO_TIEMPOUSO + "), " + TABLE_EVENTO + "." + EVENTO_HORA_INICIO + ", "
                + TABLE_EVENTO + "." + EVENTO_FECHA + ", " + TABLE_ELECTRODOMESTICO + "." + ELECT_FOTO
                + " FROM " + TABLE_EVENTO + ", " + TABLE_ELECTRODOMESTICO + "," + TABLE_TIPO
                + " WHERE " + TABLE_EVENTO + "." + EVENTO_APARATO + " = " + TABLE_ELECTRODOMESTICO + ".ID AND "
                + TABLE_ELECTRODOMESTICO + "." + ELECT_TIPOAPARATO + " = " + TABLE_TIPO + ".ID AND "
                + TABLE_EVENTO + "." + EVENTO_FECHA + " = " + "\"" + sDATE + "\" GROUP BY "
                + TABLE_ELECTRODOMESTICO + "." + ELECT_MARCA;
                //+ " ORDER BY " + TABLE_EVENTO + "." + EVENTO_FECHA + " DESC";

        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    evEvento = null;
                    double dAux = cursor.getDouble(4) * cursor.getInt(2);
                    evEvento = new Evento(Long.parseLong(cursor.getString(0)),
                            cursor.getString(1), cursor.getString(3), cursor.getString(6),
                            cursor.getDouble(4), cursor.getString(5), dAux, cursor.getBlob(7));
                    listaProducts.add(evEvento);

                } while (cursor.moveToNext());
            }
            cursor.close();
        }catch(SQLException e) {
            Log.e("SQLList", e.toString());
        }
        return listaProducts;
    }
}
