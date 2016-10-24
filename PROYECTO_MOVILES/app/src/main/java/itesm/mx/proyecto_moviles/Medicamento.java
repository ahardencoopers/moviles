package itesm.mx.proyecto_moviles;

/**
 * Created by achs on 23/10/16.
 */
public class Medicamento {
    private int idImagen;
    private String nombre;
    private String tomarCada;
    private String horario;

    public Medicamento(int Idimagen, String nombre, String tomarCada, String horario) {
        this.idImagen = idImagen;
        this.nombre = nombre;
        this.tomarCada = tomarCada;
        this.horario = horario;
    }
    public int getIdImagen() { return idImagen; }

    public String getNombre() { return nombre; }

    public String getTomarCada() { return tomarCada; }

    public String getHorario() { return horario; }
}
