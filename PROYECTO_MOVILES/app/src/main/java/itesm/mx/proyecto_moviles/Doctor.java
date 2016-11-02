package itesm.mx.proyecto_moviles;

/**
 * Created by achs on 2/11/16.
 */
public class Doctor {
    private String nombre;
    private String especialidad;
    private String direccion;
    private String codigopos;
    private String numero;
    private String correo;

    public Doctor(String nombre, String especialidad, String direccion, String codigopos, String numero, String correo) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.direccion = direccion;
        this.codigopos = codigopos;
        this.numero = numero;
        this.correo = correo;
    }

    public String getNombre() { return nombre; }
    public String getEspecialidad() { return especialidad; }
    public String getDireccion() { return direccion; }
    public String getCodigopos() { return codigopos; }
    public String getNumero() { return numero; }
    public String getCorreo() { return correo; }
}