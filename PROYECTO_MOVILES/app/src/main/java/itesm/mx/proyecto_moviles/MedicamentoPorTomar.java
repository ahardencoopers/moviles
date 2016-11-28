package itesm.mx.proyecto_moviles;

/**
 * Created by achs on 24/10/16.
 */
public class MedicamentoPorTomar {
    private long Id;
    private int idImagen;
    private String nombre;
    private String dosis;
    private String horario;
    private Boolean tomada;
    private String fecha;

    public MedicamentoPorTomar(long lId, int idImagen, String nombre, String dosis, String horario, Boolean tomada, String fecha) {
        this.Id = lId;
        this.idImagen = idImagen;
        this.nombre = nombre;
        this.dosis = dosis;
        this.horario = horario;
        this.tomada = tomada;
        this.fecha = fecha;
    }

    public MedicamentoPorTomar(int idImagen, String nombre, String dosis, String horario, Boolean tomada, String fecha) {
        this.idImagen = idImagen;
        this.nombre = nombre;
        this.dosis = dosis;
        this.horario = horario;
        this.tomada = tomada;
        this.fecha = fecha;
    }
    public int getIdImagen() { return idImagen; }

    public long getId() { return Id; }

    public void setId(long id) { this.Id = id; }

    public String getNombre() { return nombre; }

    public String getDosis() { return dosis; }

    public String getHorario() { return horario; }

    public Boolean getTomada() { return tomada; }

    public void setTomada(Boolean tomada) { this.tomada = tomada; }

    public String getFecha() { return fecha; }
}
