/*
* D.R.© Instituto Tecnológico y de Estudios Superiores de Monterrey, México, 2016.
* Se prohíbe la reproducción total o parcial de esta obra por cualquier medio sin previo y
* expreso consentimiento por escrito del Instituto Tecnológico y de Estudios Superiores de Monterrey.
*/
package itesm.mx.a01321004_examenvinculacion_ahorroenergia;

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
	private Electrodomestico elArticulo;
    private Evento evEvento;

    //Name of the tables
    private static final String TABLE_TIPO = "Tipo";
    private static final String TABLE_EVENTO = "Evento";
    private static final String TABLE_ELECTRODOMESTICO  = "Electrodomestico";

    //fields of table Tipo
    private static final String EVENTO_APARATO = "Aparato";
    private static final String EVENTO_TIEMPOUSO = "TiempoUso";
    private static final String EVENTO_HORA_INICIO = "Inicio";
    private static final String EVENTO_FECHA = "Fecha";

    //fields of table Electrodomestico
    private static final String ELECT_TIPOAPARATO = "Tipo";
    private static final String ELECT_MARCA = "Marca";
    private static final String ELECT_FOTO = "Foto";

    //fields of table Tipo
    private static final String TIPO_NOMBRE = "Nombre";
    private static final String TIPO_CONSUMO = "Consumo";

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
	
	public long addElectrodomestico (Electrodomestico product) {
        long newRowId = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(ELECT_TIPOAPARATO, product.getsTipo());
            values.put(ELECT_MARCA, product.getsMarca());
            values.put(ELECT_FOTO, product.getbmImage());

            newRowId = db.insert(TABLE_ELECTRODOMESTICO, null, values);
        }catch(SQLException e){
            Log.e("SQLADD", e.toString());
        }
        return newRowId;
	}

    public long addEvento (Evento product) {
        long newRowId = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(EVENTO_APARATO, product.getsTipo());
            values.put(EVENTO_TIEMPOUSO, product.getsTiempo());
            values.put(EVENTO_HORA_INICIO, product.getsHora());
            values.put(EVENTO_FECHA, product.getsFecha());

            newRowId = db.insert(TABLE_EVENTO, null, values);
        }catch(SQLException e){
            Log.e("SQLADD", e.toString());
        }
        return newRowId;
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
            evEvento = null;
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
