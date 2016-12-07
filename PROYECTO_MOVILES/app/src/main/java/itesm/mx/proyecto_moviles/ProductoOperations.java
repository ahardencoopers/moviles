/*
* D.R.© Instituto Tecnológico y de Estudios Superiores de Monterrey, México, 2016.
* Se prohíbe la reproducción total o parcial de esta obra por cualquier medio sin previo y
* expreso consentimiento por escrito del Instituto Tecnológico y de Estudios Superiores de Monterrey.
*/
package itesm.mx.proyecto_moviles;

import android.animation.FloatEvaluator;
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
	private static final String TABLE_MED = "Medicamento";
	private static final String TABLE_USRS = "Usuario";
	private static final String TABLE_DOCS = "Doctor";
	private static final String TABLE_FECHAS = "Fechas";//chk
	private static final String TABLE_HIST = "Historial";
	private static final String TABLE_HORA = "Hora";

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

	//fields of table fechas
	private static final String FECHAS_TSTAMP = "tstamp";//chk

	//fields of table Historial
	private static final String HIST_MEDICAMENTO = "medicamento";
	private static final String HIST_DOSIS = "dosis";
	private static final String HIST_HORARIO = "horario";
	private static final String HIST_FECHA = "fecha";
	private static final String HIST_TOMADA = "tomada";

	//fields of table Historial
	private static final String HORA_MEDICAMENTO = "medicamento";
	private static final String HORA_HORARIO = "horario";
	private static final String HORA_FECHA = "fecha";


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
			//values.put(MED_FOTOID, med.getIdImagen());
			values.put(MED_FECHAINICIO, med.getFechaInicio());
			values.put(MED_HASTAFECHA, med.getHastaFecha());
		
			newRowId = db.insert(TABLE_MED, null, values);
		}
		catch(SQLException e) {
			Log.e("SQL ADD ERROR", e.toString());
		}
		
		return newRowId;
	}

	public long addUsuario(Usuario usr) {
		long newRowId = 0;
		try {
			//Table Usuarios field strings: nombre direccion telefono sexo fechanaci peso altura
			//Object Usuario attributes: nombre direccion telefono sexo fechaNaci peso altura
			ContentValues values = new ContentValues();
			values.put(USRS_NOMBRE, usr.getNombre());
			values.put(USRS_DIR, usr.getDireccion());
			values.put(USRS_TELEFONO, usr.getTelefono());
			values.put(USRS_SEXO, usr.getSexo());
			values.put(USRS_FECHANACI, usr.getFechaNacimiento());
			values.put(USRS_PESO, usr.getPeso());
			values.put(USRS_ALTURA, usr.getAltura());

			newRowId = db.insert(TABLE_USRS, null, values);
			
		}
		catch(SQLException e) {
			Log.e("SQL ADD ERROR", e.toString());
		}
		return newRowId;
	}

	public int updateUser(Usuario usr) {
		int result = -1;
		try {
			ContentValues values = new ContentValues();
			values.put(USRS_NOMBRE, usr.getNombre());
			values.put(USRS_DIR, usr.getDireccion());
			values.put(USRS_FECHANACI, usr.getFechaNacimiento());
			values.put(USRS_TELEFONO, usr.getTelefono());
			values.put(USRS_ALTURA, usr.getAltura());
			values.put(USRS_PESO, usr.getPeso());
			values.put(USRS_SEXO, usr.getSexo());
			result = db.update(TABLE_USRS, values, "ID =" + usr.getiId(), null);
		}
		catch(SQLiteException e){
			Log.e("SQLUPDATE", e.toString());
		}
		return result;
	}

	public long addDoctor(Doctor doc) {
		long newRowId = -1;
		try {
			//Table Docotores  field strings: nombre especialidad direccion codigopos numero telefono correo
			//Object Doctor attributes: nombre especialidad direccion codigopos numero telefono correo
			ContentValues values = new ContentValues();
			values.put(DOCS_NOMBRE, doc.getNombre());
			values.put(DOCS_ESPECIALIDAD, doc.getEspecialidad());
			values.put(DOCS_DIR, doc.getDireccion());
			values.put(DOCS_CODIGOPOS, doc.getCodigopos());
			values.put(DOCS_CIUDAD, doc.getCiudad());
			values.put(DOCS_TELEFONO, doc.getTelefono());
			values.put(DOCS_CORREO, doc.getCorreo());
			newRowId = db.insert(TABLE_DOCS, null, values);
		}
		catch (SQLException e) {
			Log.e("SQLADD", e.toString());
		}
		return newRowId;
	}

	public long addHistorial(MedicamentoPorTomar med) {
		long newRowId = 0;
		try {
			//Table Medicamentos field strings: nombre tipo dosis horainicio tomarcada comentarios fotoid hastafecha
			//Object Medicamento attributes: nombre tipo dosis horario tomarCada comentarios idImagen hastafecha
			ContentValues values = new ContentValues();
			values.put("ID", med.getId());
			values.put(HIST_MEDICAMENTO, med.getNombre());
			values.put(HIST_DOSIS, med.getDosis());
			values.put(HIST_HORARIO, med.getHorario());
			values.put(HIST_FECHA, med.getFecha());
			if(med.getTomada()) {
				values.put(HIST_TOMADA, 1);
			}
			else {
				values.put(HIST_TOMADA, 0);
			}


			newRowId = db.insert(TABLE_HIST, null, values);
		}
		catch(SQLException e) {
			Log.e("SQL ADD ERROR", e.toString());
		}

		return newRowId;
	}

	public int updateHistorial(MedicamentoPorTomar med) {
		long id = med.getId();
		String nombre = med.getNombre();
		int result = -1;

		try {
			ContentValues values = new ContentValues();
			values.put(HIST_MEDICAMENTO, med.getNombre());
			values.put(HIST_FECHA, med.getFecha());
			values.put(HIST_HORARIO, med.getHorario());
			values.put(HIST_DOSIS, med.getDosis());
			if(med.getTomada()) {
				values.put(HIST_TOMADA, 1);
			}
			else {
				values.put(HIST_TOMADA, 0);
			}

			result = db.update(TABLE_HIST, values, "ID = " + id + " AND " + HIST_MEDICAMENTO + " = " + "'" + nombre + "'", null);
		}
		catch(SQLiteException e){
			Log.e("SQLUPDATE", e.toString());
		}
		return result;
	}

	public Medicamento findMedicamento(long id) {
		Medicamento med = null;
		String query = "SELECT * FROM " + TABLE_MED + " WHERE " + TABLE_MED + ".ID = " + id;

		try {
			Cursor cursor = db.rawQuery(query, null);
			if(cursor.moveToFirst()) {
				med = new Medicamento(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3),
					cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8));
			}
			cursor.close();
		}
		catch(SQLException e) {
			Log.e("SQLFIND ERROR", e.toString());
		}
		
		return med;
	}

	public Medicamento findMedicamentoByHora(String sHora, String sFecha) {
		Medicamento med = null;
		String query = "SELECT * FROM " + TABLE_HORA + " WHERE " + HORA_HORARIO + " = '" + sHora +"' AND " + HORA_FECHA + " = '" + sFecha + "'";
		Log.e("SQL", query);
		try {
			Cursor cursor = db.rawQuery(query, null);
			if(cursor.moveToFirst()) {
				Log.e("SQL", String.valueOf(cursor.getLong(1)));
				med = findMedicamento(cursor.getLong(1));
				//med = new Medicamento(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3),
				//		cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8));
			}
			cursor.close();
		}
		catch(SQLException e) {
			Log.e("SQLFIND ERROR", e.toString());
		}

		return med;
	}

	public long addHora(MedicamentoPorTomar med, long idMed) {
		long newRowId = 0;
		try {
			//Table Medicamentos field strings: nombre tipo dosis horainicio tomarcada comentarios fotoid hastafecha
			//Object Medicamento attributes: nombre tipo dosis horario tomarCada comentarios idImagen hastafecha
			ContentValues values = new ContentValues();
			values.put(HORA_MEDICAMENTO, idMed);
			values.put(HORA_HORARIO, med.getHorario());
			values.put(HORA_FECHA, med.getFecha());

			newRowId = db.insert(TABLE_HORA, null, values);
		} catch (SQLException e) {
			Log.e("SQL ADD ERROR", e.toString());
		}
		return newRowId;
	}

	public Usuario findUsuario() {
		Usuario usr = null;
		String query = "SELECT * FROM " + TABLE_USRS ; //+ " WHERE " + TABLE_USRS + "." + USRS_NOMBRE + " = " + usuarioNombre;

		try {
			Cursor cursor = db.rawQuery(query, null);
			if (cursor.moveToFirst()) {
				usr = new Usuario(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
						cursor.getDouble(6), cursor.getDouble(7));
			}
			cursor.close();
		}
		catch(SQLException e) {
				Log.e("SQLFIND ERROR", e.toString());
		}

		return usr;
	}

	public Doctor findDoctor(String ID) {
		Doctor doc = null;
		String query = "SELECT * FROM " + TABLE_DOCS + " WHERE " + TABLE_DOCS + ".ID" + " = " + ID;

		try {
			Cursor cursor = db.rawQuery(query, null);
			if(cursor.moveToFirst()) {
				doc = new Doctor(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(6),
					cursor.getString(7), cursor.getString(5));
			}
			cursor.close();
		}
		catch(SQLException e) {
			Log.e("SQLFIND ERROR", e.toString());
		}

		return doc;
	}

    public boolean deleteMedicamento(long id) {
        boolean result = false;
        try {
                db.delete(TABLE_MED,
                        "ID" + " = ?",
                        new String[]{Long.toString(id)});
                result = true;
		}
        catch(SQLiteException e){
            Log.e("SQLDELETE", e.toString());
        }
        return result;
    }


	public ArrayList<Medicamento> getAllMedicamentos() {
		Medicamento med = null;
		ArrayList<Medicamento> listaMedicamentos = new ArrayList<Medicamento>();

		String selectQuery = "Select * FROM " + TABLE_MED;

		try {
			Cursor cursor = db.rawQuery(selectQuery, null);
			if (cursor.moveToFirst()) {
				do {
					med = new Medicamento(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3),
					cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8));
					listaMedicamentos.add(med);
				} while (cursor.moveToNext());
			}
			cursor.close();
		}catch(SQLException e) {
			Log.e("SQLGETALL", e.toString());
		}
		return listaMedicamentos;
	}

	public ArrayList<MedicamentoPorTomar> getAllHistorialAsc() {
		MedicamentoPorTomar med = null;
		ArrayList<MedicamentoPorTomar> listaMedicamentos = new ArrayList<MedicamentoPorTomar>();

		String selectQuery = "Select * FROM " + TABLE_HIST + " ORDER BY ID ASC";

		try {
			Cursor cursor = db.rawQuery(selectQuery, null);
			if (cursor.moveToFirst()) {
				do {
					boolean bAux = (cursor.getInt(5) == 1)? true : false;
					med = new MedicamentoPorTomar(cursor.getInt(0), R.drawable.logo, cursor.getString(1), cursor.getString(2),
							cursor.getString(3), bAux, cursor.getString(4));
					listaMedicamentos.add(med);
				} while (cursor.moveToNext());
			}
			cursor.close();
		}catch(SQLException e) {
			Log.e("SQLGETALL", e.toString());
		}
		return listaMedicamentos;
	}

	public ArrayList<MedicamentoPorTomar> getAllHistorial() {
		MedicamentoPorTomar med = null;
		ArrayList<MedicamentoPorTomar> listaMedicamentos = new ArrayList<MedicamentoPorTomar>();

		String selectQuery = "Select * FROM " + TABLE_HIST;

		try {
			Cursor cursor = db.rawQuery(selectQuery, null);
			if (cursor.moveToFirst()) {
				do {
					boolean bAux = (cursor.getInt(5) == 1)? true : false ;
					med = new MedicamentoPorTomar(cursor.getInt(0), R.drawable.logo, cursor.getString(1), cursor.getString(2),
							cursor.getString(3), bAux, cursor.getString(4));
					listaMedicamentos.add(med);
				} while (cursor.moveToNext());
			}
			cursor.close();
		}catch(SQLException e) {
			Log.e("SQLGETALL", e.toString());
		}
		return listaMedicamentos;
	}

	public ArrayList<Medicamento> getAllMedicamentosByDate(String sDate) {
		Medicamento med = null;
		sDate = sDate.replace("/","");
		double dDate = Double.parseDouble(sDate);
		ArrayList<Medicamento> listaMedicamentos = new ArrayList<Medicamento>();

		String selectQuery = "Select * FROM " + TABLE_MED;

		try {
			Cursor cursor = db.rawQuery(selectQuery, null);
			if (cursor.moveToFirst()) {
				do {
					double dAux = Double.parseDouble(cursor.getString(8).replace("/",""));
					if(dAux >= dDate) {
						med = new Medicamento(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3),
								cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8));
						listaMedicamentos.add(med);
					}
				} while (cursor.moveToNext());
			}
			cursor.close();
		}catch(SQLException e) {
			Log.e("SQLGETALL", e.toString());
		}
		return listaMedicamentos;
	}

	public ArrayList<Doctor> getAllDoctores() {
		ArrayList<Doctor> listaDoctores = new ArrayList<Doctor>();

		String selectQuery = "Select * FROM " + TABLE_DOCS;

		try {
			Cursor cursor = db.rawQuery(selectQuery, null);

			if (cursor.moveToFirst()) {
				do {
					Doctor doc = new Doctor(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
						cursor.getString(6), cursor.getString(7));
					listaDoctores.add(doc);
				} while (cursor.moveToNext());
			}
			cursor.close();
		}catch(SQLException e) {
			Log.e("SQLGETALL", e.toString());
		}
		return listaDoctores;
	}

	public int updateMedico(Doctor doc) {
		int result = -1;
		try {
			ContentValues values = new ContentValues();
			values.put(DOCS_NOMBRE, doc.getNombre());
			values.put(DOCS_DIR, doc.getDireccion());
			values.put(DOCS_ESPECIALIDAD, doc.getEspecialidad());
			values.put(DOCS_TELEFONO, doc.getTelefono());
			values.put(DOCS_CORREO, doc.getCorreo());
			values.put(DOCS_CIUDAD, doc.getCiudad());
			values.put(DOCS_CODIGOPOS, doc.getCodigopos());
			result = db.update(TABLE_DOCS, values, "ID =" + doc.getID(), null);
		}
		catch(SQLiteException e){
			Log.e("SQLUPDATE", e.toString());
		}
		return result;
	}

	public boolean deleteDoctor(long id) {
		boolean result = false;
		try {
			db.delete(TABLE_DOCS,
					"ID" + " = ?",
					new String[]{Long.toString(id)});
			result = true;
		}
		catch(SQLiteException e){
			Log.e("SQLDELETE", e.toString());
		}
		return result;
	}

	public long addFecha(long stamp){
		long newRowId = -1;
		try {
			ContentValues values = new ContentValues();
			values.put(FECHAS_TSTAMP, stamp);
			newRowId = db.insert(TABLE_FECHAS, null, values);
		}
		catch (SQLException e) {
			Log.e("SQLADD", e.toString());
		}
		return newRowId;
	}

	public Fecha getFecha(long id){
		Fecha fecha = null;
		String query = "SELECT * FROM " + TABLE_FECHAS + " WHERE " + TABLE_FECHAS + ".ID" + " = " + id;

		try {
			Cursor cursor = db.rawQuery(query, null);
			if(cursor.moveToFirst()) {
				fecha = new Fecha(cursor.getLong(0), cursor.getLong(1));
			}
			cursor.close();
		}
		catch(SQLException e) {
			Log.e("SQLFIND ERROR", e.toString());
		}

		return fecha;
	}

	public ArrayList<Fecha> getAllFechas() {
		ArrayList<Fecha> listaFechas = new ArrayList<Fecha>();

		String selectQuery = "Select * FROM " + TABLE_FECHAS;

		try {
			Cursor cursor = db.rawQuery(selectQuery, null);

			if (cursor.moveToFirst()) {
				do {
					Fecha fe = new Fecha(cursor.getLong(0), cursor.getLong(1));
					listaFechas.add(fe);
				} while (cursor.moveToNext());
			}
			cursor.close();
		} catch (SQLException e) {
			Log.e("SQLGETALL", e.toString());
		}
		return listaFechas;
	}

	public ArrayList<MedicamentoPorTomar> getHistorialByDate(String sDate) {
		MedicamentoPorTomar med = null;
		ArrayList<MedicamentoPorTomar> listaMedicamentos = new ArrayList<MedicamentoPorTomar>();

		String selectQuery = "Select * FROM " + TABLE_HIST + " Where " + HIST_FECHA + " = " + sDate;

		try {
			Cursor cursor = db.rawQuery(selectQuery, null);
			if (cursor.moveToFirst()) {
				do {
					boolean bAux = (cursor.getInt(5) == 1) ? true : false;
					med = new MedicamentoPorTomar(cursor.getLong(0), R.drawable.logo, cursor.getString(1), cursor.getString(2), cursor.getString(3),
							bAux, cursor.getString(4));
					listaMedicamentos.add(med);
				} while (cursor.moveToNext());
			}
			cursor.close();
		} catch (SQLException e) {
			Log.e("SQLGETALL", e.toString());
		}
		return listaMedicamentos;
	}

	public MedicamentoPorTomar getMedicamentoTomado(MedicamentoPorTomar medAux) {
		MedicamentoPorTomar med = null;

		String selectQuery = "Select * FROM " + TABLE_HIST + " WHERE " + HIST_MEDICAMENTO + " = '"
				+ medAux.getNombre() + "' AND " + HIST_DOSIS + " = '" + medAux.getDosis() + "' AND " +
				HIST_HORARIO + " = '" + medAux.getHorario() + "' AND " + HIST_FECHA + " = '" + medAux.getFecha() + "'";

		try {
			Cursor cursor = db.rawQuery(selectQuery, null);
			if (cursor.moveToFirst()) {

					boolean dTomada = (cursor.getInt(5) == 1)? true : false;
					med = new MedicamentoPorTomar(cursor.getLong(0), R.drawable.logo, cursor.getString(1), cursor.getString(2), cursor.getString(3),
							dTomada, cursor.getString(4));
					System.out.println("medicamento tomado: " + cursor.getLong(0) +" "+ R.drawable.logo +" "+ cursor.getString(1)+" "+cursor.getString(2)+" "+ cursor.getString(3) +
							" " + dTomada + " " + cursor.getString(5));

			}
			cursor.close();
		}catch(SQLException e) {
			Log.e("SQLGETALL", e.toString());
		}
		return med;
	}

	public boolean deleteFecha(long id){
		boolean result = false;
		try {
			db.delete(TABLE_FECHAS,
					"ID" + " = ?",
					new String[]{Long.toString(id)});
			result = true;
		}
		catch(SQLiteException e){
			Log.e("SQLDELETE", e.toString());
		}
		return result;
	}


}
