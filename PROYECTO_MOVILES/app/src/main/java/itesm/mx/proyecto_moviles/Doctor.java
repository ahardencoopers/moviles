package itesm.mx.proyecto_moviles;

/**
 * Created by achs on 2/11/16.
 */
public class Doctor {
    private long lId;
    private String sNombre;
    private String sEspecialidad;
    private String sDireccion;
    private String sCodigopos;
    private String sCiudad;
    private String sCorreo;
    private String sTelefono;

    public Doctor(long id, String nombre, String especialidad, String direccion, String codigopos, String ciudad, String correo, String telefono) {
        this.lId = id;
        this.sNombre = nombre;
        this.sEspecialidad = especialidad;
        this.sDireccion = direccion;
        this.sCodigopos = codigopos;
        this.sCiudad = ciudad;
        this.sCorreo = correo;
        this.sTelefono = telefono;
    }

    public long getID() { return lId; }
    public String getNombre() { return sNombre; }
    public String getEspecialidad() { return sEspecialidad; }
    public String getDireccion() { return sDireccion; }
    public String getCodigopos() { return sCodigopos; }
    public String getCiudad() { return sCiudad; }
    public String getCorreo() { return sCorreo; }
    public String getTelefono() { return sTelefono; }
}