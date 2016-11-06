package itesm.mx.a01321004_examen_1_android_ad16;

/**
 * Created by Faintinger on 9/15/2016.
 */
public class Articulo {
    private int iSerie;
    private String sNombre;
    private int iCtd;
    private double dPrecio;
    private int iIdImagen;

    public Articulo(int iS, String sN, int iC, double dP, int iImg) {
        this.iSerie = iS;
        this.sNombre = sN;
        this.iCtd = iC;
        this.dPrecio = dP;
        this.iIdImagen = iImg;
    }

    public void setiSerie(int sSerie) { this.iSerie = iSerie; }

    public void setsNombre(String sNombre) { this.sNombre = sNombre; }

    public void setiCtd(int iCtd) { this.iCtd = iCtd; }

    public void setdPrecio(double dPrecio) { this.dPrecio = dPrecio; }

    public void setiIdImagen(int iIdImagen) { this.iIdImagen = iIdImagen; }

    public int getiSerie() { return iSerie; }

    public String getsNombre() { return sNombre; }

    public int getiCtd() { return iCtd; }

    public double getdPrecio() { return dPrecio; }

    public int getiIdImagen() { return iIdImagen; }
}
