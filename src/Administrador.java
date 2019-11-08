import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

public class Administrador extends Usuario {
    public Administrador(String nombre, String contrasena){
        this.nombreDeUsuario = nombre;
        this.contrasena = contrasena;
    }

    public void bloquearCliente(Cliente c) throws IOException {
        if(c.getActivoEnUso() == null) return;
        c.isBlocked = true;
        multarCliente(c);
    }

    public void desbloquearCliente(Cliente c){
        c.isBlocked = false;
    }

    public void multarCliente(Cliente c) throws IOException {
        c.bloquearCliente();
    }
    public ArrayList<Activo> crearActivosParaLote(TipoDeActivo nombre, int cantidad, Terminal terminal, int precio, int tarifa, int puntos, int codigoActivo){
        ArrayList<Activo> activos = new ArrayList<>();
        for (int i= 0; i<cantidad; i++){
            activos.add(new Activo(nombre, terminal, precio, tarifa, puntos, codigoActivo));
            codigoActivo++;
        }return activos;
    }

    public Descuento crearDescuento (String descripcion, TipoDeActivo unTipoDeActivo, int puntosMinParaUsar, Zona zonaParaDescuento, int descuentoNumerico){
        return new Descuento(descripcion, unTipoDeActivo, puntosMinParaUsar, zonaParaDescuento, descuentoNumerico);
    }

    public Lote crearLoteDeCompraDeActivos( TipoDeActivo nombre, int cantidad, Terminal terminal, int precio, int tarifa, int puntos, int codigoLote, int codigoActivo){
        ArrayList<Activo> activos = this.crearActivosParaLote(nombre, cantidad, terminal, precio, tarifa, puntos, codigoActivo);
        Lote lote = new Lote(activos, terminal, codigoLote);
        for (int i = 0; i<activos.size(); i++){
            this.adjudicarLoteAlActivo(lote, activos.get(i));
        }
        return lote;
    }

    private void adjudicarLoteAlActivo(Lote lote, Activo activo) {
        activo.cambiarLote(lote);
    }

    @Override
    public boolean isAdmin() {
        return true;
    }

    public LocalTime cambiarHora (int hora, int minutos){
        return LocalTime.of(hora, minutos);
    }
}
