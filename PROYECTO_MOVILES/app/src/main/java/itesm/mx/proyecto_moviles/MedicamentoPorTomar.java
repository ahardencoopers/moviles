package itesm.mx.proyecto_moviles;

/**
 * Created by achs on 24/10/16.
 */
public class MedicamentoPorTomar {
    private int idImagen;
    private String nombre;
    private String dosis;
    private String horario;
    private Boolean tomada;

    public MedicamentoPorTomar(int idImagen, String nombre, String dosis, String horario, Boolean tomada) {
        this.idImagen = idImagen;
        this.nombre = nombre;
        this.dosis = dosis;
        this.horario = horario;
        this.tomada = tomada;
    }
    public int getIdImagen() { return idImagen; }

    public String getNombre() { return nombre; }

    public String getDosis() { return dosis; }

    public String getHorario() { return horario; }

    public Boolean getTomada() { return tomada; }
}
