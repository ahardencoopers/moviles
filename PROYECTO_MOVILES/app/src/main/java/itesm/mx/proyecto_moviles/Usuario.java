package itesm.mx.proyecto_moviles;

import java.util.SimpleTimeZone;

/**
 * Created by achs on 2/11/16.
 */
public class Usuario {
    private long iId;
    private String sNombre;
    private String sDireccion;
    private String sTelefono;
    private String sSexo;
    private String sFechaNacimiento; //Usuarios.fechanacimiento en BD
    private double dPeso;
    private double dAltura;

    public Usuario(long iId, String sNombre, String sDireccion, String sTelefono, String sSexo, String sFechaNacimiento, double dPeso, double dAltura ) {
        this.iId = iId;
        this.sNombre = sNombre;
        this.sDireccion = sDireccion;
        this.sTelefono = sTelefono;
        this.sSexo = sSexo;
        this.sFechaNacimiento = sFechaNacimiento;
        this.dPeso = dPeso;
        this.dAltura = dAltura;
    }
    public long getiId() { return iId; }
    public String getNombre() { return sNombre; }
    public String getDireccion() { return sDireccion; }
    public String getTelefono() { return sTelefono; }
    public String getSexo() {return sSexo; }
    public String getFechaNacimiento() {return sFechaNacimiento; }
    public double getPeso() { return dPeso; }
    public double getAltura() { return dAltura; }
}
