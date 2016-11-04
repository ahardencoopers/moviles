package itesm.mx.proyecto_moviles;

/**
 * Created by achs on 2/11/16.
 */
public class Doctor {
    private String sNombre;
    private String sEspecialidad;
    private String sDireccion;
    private String sCodigopos;
    private String sNumero;
    private String sCorreo;
    private String sTelefono;

    public Doctor(String nombre, String especialidad, String direccion, String codigopos, String numero, String correo, String telefono) {
        this.sNombre = nombre;
        this.sEspecialidad = especialidad;
        this.sDireccion = direccion;
        this.sCodigopos = codigopos;
        this.sNumero = numero;
        this.sCorreo = correo;
        this.sTelefono = telefono;
    }

    public String getNombre() { return sNombre; }
    public String getEspecialidad() { return sEspecialidad; }
    public String getDireccion() { return sDireccion; }
    public String getCodigopos() { return sCodigopos; }
    public String getNumero() { return sNumero; }
    public String getCorreo() { return sCorreo; }
    public String getTelefono() { return sTelefono; }
}