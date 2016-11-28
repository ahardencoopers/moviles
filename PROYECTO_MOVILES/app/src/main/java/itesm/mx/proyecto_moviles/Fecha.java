package itesm.mx.proyecto_moviles;

/**
 * Created by Carlo on 11/26/2016.
 */
public class Fecha {
    private long id;
    private long tstamp;

    public Fecha(long id, long tstamp) {
        this.id = id;
        this.tstamp = tstamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTstamp() {
        return tstamp;
    }

    public void setTstamp(long tstamp) {
        this.tstamp = tstamp;
    }
}