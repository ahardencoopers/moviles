package itesm.mx.proyecto_moviles;

/**
 * Created by achs on 12/11/16.
 */

public class CalendarioEntry {
    private long id;
    private String nombre;
    private String tipo;
    private double dosis;
    private String horario; //Medicamentos.horainicio en BD
    private String tomarCada;
    private String comentarios;
    private String hastaFecha;
    private String separador;

    public CalendarioEntry(long id, String nombre, String tipo, double dosis, String horario, String tomarCada, String comentarios, String hastaFecha) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.dosis = dosis;
        this.horario = horario;
        this.tomarCada = tomarCada;
        this.comentarios = comentarios;
        // this.idImagen = idImagen;
        this.hastaFecha = hastaFecha;
    }

    public CalendarioEntry(String nombre, String tipo, double dosis, String horario, String tomarCada, String comentarios, String hastaFecha) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.dosis = dosis;
        this.horario = horario;
        this.tomarCada = tomarCada;
        this.comentarios = comentarios;
        //this.idImagen = idImagen;
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

    public String getHastaFecha() {return hastaFecha; }

    public CalendarioEntry(String separador) {
        this.separador = separador;
    }

    public String getSeparador() { return separador; }
}
