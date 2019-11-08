import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class OperadorDeZonas implements Serializable {
    public ArrayList<Zona> zonas;
    public ArrayList<TipoDeActivo> tipoDeActivos;
    OperadorDeUsuarios operadorDeUsuarios;
    int codigoActivoActual;
    int codigoLoteActual;

    public OperadorDeZonas () {
        zonas = new ArrayList<>();
        zonas.add(new ZonaNorte());
        zonas.add(new ZonaSur());
        tipoDeActivos = new ArrayList<>();
    }

    public void setOperadorDeUsuarios(OperadorDeUsuarios operadorDeUsuarios) {
        this.operadorDeUsuarios = operadorDeUsuarios;
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

    public void eliminarTipoDeActivo (String nombreTipoDeActivo) throws IOException {
        for (TipoDeActivo tipodeactivo: tipoDeActivos) {
            if (tipodeactivo.getNombre().equals(nombreTipoDeActivo)) {
                tipoDeActivos.remove(tipodeactivo);
                return;
            }
        }throw new IOException("Tipo de activo no encontrado");
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

    public ArrayList<TipoDeActivo> getTipoDeActivos() {
        return tipoDeActivos;
    }

    public Zona getZona (String nombre) throws IOException {
        for (Zona zona: zonas) {
            if (zona.getNombre().equals(nombre))return zona;
        }throw new IOException("Zona no encontrada");
    }

    public void setCodigoActivoActual(int codigoActivoActual) {
        this.codigoActivoActual = codigoActivoActual;
    }

    public void setCodigoLoteActual(int codigoLoteActual) {
        this.codigoLoteActual = codigoLoteActual;
    }

    public void agregarLoteAZona (Lote unLote, String nombreZona, Terminal terminal) throws IOException {
        for (Zona zona: zonas) {
            if (zona.getNombre().equals(nombreZona)){
                zona.getTerminal(terminal.getNombre()).agregarLote(unLote);
                setCodigoLoteActual(unLote.getCodigo()+1);
                setCodigoActivoActual(unLote.getActivosDelLote().get(unLote.getActivosDelLote().size()-1).getCodigo()+1);
                return;
            }
        }throw new IOException("Zona no encontrada");
    }

    public int getCodigoActivoActual() {
        return codigoActivoActual;
    }

    public int getCodigoLoteActual() {
        return codigoLoteActual;
    }
}
