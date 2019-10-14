import java.io.IOException;
import java.util.ArrayList;

public class OperadorDeZonas {
    public ArrayList<Zona> zonas;
    public ArrayList<TipoDeActivo> tipoDeActivos;

    public OperadorDeZonas () {
        zonas = new ArrayList<>();
        zonas.add(new ZonaNorte());
        zonas.add(new ZonaSur());
        tipoDeActivos = new ArrayList<>();
    }

    public void agregarZona (Zona unaZona){
        zonas.add(unaZona);
    }

    public void eliminarZona (Zona unaZona){
        zonas.remove(unaZona);
    }

    public void agregarTipoDeActivo (String nombreTipoDeActivo) throws IOException {
        for (TipoDeActivo tipodeactivo: tipoDeActivos) {
            if (tipodeactivo.getNombre().equals(nombreTipoDeActivo)) throw new IOException("El tipo de activo ya existe");
        }
        tipoDeActivos.add(new TipoDeActivo(nombreTipoDeActivo));
    }

    public void checkTipoActivo (String nombre) throws IOException {
        for (TipoDeActivo tipodeactivo: tipoDeActivos) {
            if (tipodeactivo.getNombre().equals(nombre)) return;
        }throw new IOException("El tipo de activo no existe");
    }

    public TipoDeActivo getTipoActivo (String nombre) throws IOException {
        checkTipoActivo(nombre);
        for (TipoDeActivo tipodeactivo: tipoDeActivos) {
            if (tipodeactivo.getNombre().equals(nombre))return tipodeactivo;
        }throw new IOException("No se encontro el tipo de activo");
    }

    public ArrayList<Zona> getZonas() {
        return zonas;
    }

    public Zona getZona (String nombre) throws IOException {
        for (Zona zona: zonas) {
            if (zona.getNombre().equals(nombre))return zona;
        }throw new IOException("Zona no encontrada");
    }

    public void eliminarTipoDeActivo (TipoDeActivo unTipoDeActivo){
        tipoDeActivos.remove(unTipoDeActivo);
    }

    public void agregarLoteAZona (Lote unLote, String nombreZona) throws IOException {
        for (Zona zona: zonas) {
            if (zona.getNombre().equals(nombreZona)){
                zona.agregarLote(unLote);
            }
        }throw new IOException("Zona no encontrada");
    }
}