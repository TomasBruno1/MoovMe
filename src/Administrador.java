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
    public ArrayList<Activo> crearActivosParaLote(TipoDeActivo nombre, int cantidad, Terminal terminal, int precio, int tarifa, int puntos){
        ArrayList<Activo> activos = new ArrayList<>();
        for (int i= 0; i<cantidad; i++){
            activos.add(new Activo(nombre, terminal, precio, tarifa, puntos));
        }return activos;
    }

    public Lote crearLoteDeCompraDeActivos( TipoDeActivo nombre, int cantidad, Terminal terminal, int precio, int tarifa, int puntos, int codigo){
        ArrayList<Activo> activos = this.crearActivosParaLote(nombre, cantidad, terminal, precio, tarifa, puntos);
        Lote lote = new Lote(activos, terminal, codigo);
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
