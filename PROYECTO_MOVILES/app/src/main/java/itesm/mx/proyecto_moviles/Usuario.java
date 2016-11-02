package itesm.mx.proyecto_moviles;

/**
 * Created by achs on 2/11/16.
 */
public class Usuario {
    private String nombre;
    private String direccion;
    private String telefono;
    private String sexo;
    private String fechaNaci; //Usuarios.fechanaci en BD
    private double peso;
    private double altura;

    public Usuario(String nombre, String direccion, String telefono, String sexo, String fechaNaci, double peso, double altura ) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.sexo = sexo;
        this.fechaNaci = fechaNaci;
        this.peso = peso;
        this.altura = altura;
    }

    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public String telefono() { return telefono; }
    public String sexo() {return sexo; }
    private String fechaNaci() {return fechaNaci; }
    private double peso() { return peso; }
    private double altura() { return altura; }
}
