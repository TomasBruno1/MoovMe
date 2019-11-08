import java.io.Serializable;

public class Descuento implements Serializable {
    String descripcion;
    TipoDeActivo unTipoDeActivo;
    int puntosMinParaUsar;
    Zona zonaParaDescuento;
    int descuentoNumerico;

    public Descuento(String descripcion, TipoDeActivo unTipoDeActivo, int puntosMinParaUsar, Zona zonaParaDescuento, int descuentoNumerico){
        this.descripcion = descripcion;
        this.unTipoDeActivo = unTipoDeActivo;
        this.puntosMinParaUsar = puntosMinParaUsar;
        this.zonaParaDescuento = zonaParaDescuento;
        this.descuentoNumerico = descuentoNumerico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getDescuentoNumerico() {
        return descuentoNumerico;
    }

    public int getPuntosMinParaUsar() {
        return puntosMinParaUsar;
    }

    public TipoDeActivo getUnTipoDeActivo() {
        return unTipoDeActivo;
    }

    public Zona getZonaParaDescuento() {
        return zonaParaDescuento;
    }

}
