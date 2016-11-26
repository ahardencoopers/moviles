package itesm.mx.proyecto_moviles;

/**
 * Created by achs on 23/10/16.
 */
public class Medicamento {
    private long id;
    private String nombre;
    private String tipo;
    private double dosis;
    private String horario; //Medicamentos.horainicio en BD
    private String tomarCada;
    private String comentarios;
    // private int idImagen; //Medicamentos.fotoid en BD
    private String fechaInicio;
    private String hastaFecha;

    public Medicamento(long id, String nombre, String tipo, double dosis, String horario, String tomarCada, String comentarios, String fechaInicio, String hastaFecha) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.dosis = dosis;
        this.horario = horario;
        this.tomarCada = tomarCada;
        this.comentarios = comentarios;
        // this.idImagen = idImagen;
        this.fechaInicio = fechaInicio;
        this.hastaFecha = hastaFecha;
    }

    public Medicamento(String nombre, String tipo, double dosis, String horario, String tomarCada, String comentarios,String fechaInicio, String hastaFecha) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.dosis = dosis;
        this.horario = horario;
        this.tomarCada = tomarCada;
        this.comentarios = comentarios;
        //this.idImagen = idImagen;
        this.fechaInicio = fechaInicio;
        this.hastaFecha = hastaFecha;
    }
    public long getId() { return id; }

    public String getNombre() { return nombre; }

    public String getTipo() { return tipo; }

    public double getDosis() { return dosis; }

    public String getHorario() { return horario; }

    public String getTomarCada() { return tomarCada; }

    public String getComentarios() { return comentarios; }

    // public int getIdImagen() { return idImagen; }

    public String getFechaInicio() { return  fechaInicio; }

    public String getHastaFecha() {return hastaFecha; }


}
