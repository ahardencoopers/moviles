package itesm.mx.proyecto_moviles;

/**
 * Created by achs on 12/11/16.
 */

public class HistorialEntry {
    private long id;
    private String nombre;
    private String tipo;
    private String fecha;
    private double dosis;
    private String horario; //Medicamentos.horainicio en BD
    private String comentarios;
    private String separador;
    private boolean tomada;

    public HistorialEntry(long id, String nombre, String fecha, double dosis, String horario, boolean tomada) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.dosis = dosis;
        this.horario = horario;
        this.comentarios = comentarios;
        this.tomada = tomada;
    }

    public long getId() { return id; }

    public String getNombre() { return nombre; }

    public String getFecha() { return fecha; }

    public double getDosis() { return dosis; }

    public String getHorario() { return horario; }

    public HistorialEntry(String separador) {
        this.separador = separador;
    }

    public String getSeparador() { return separador; }

    public Boolean getTomada() { return  tomada; }
}
