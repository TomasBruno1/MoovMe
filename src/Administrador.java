import java.io.IOException;
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
        Multa multa = new Multa(c);
        c.multa = multa;
        c.getActivoEnUso().devolverActivoATerminal(c.getActivoEnUso().getTerminalDeOrigen());
    }
    public ArrayList<Activo> crearActivosParaLote(TipoDeActivo nombre, int cantidad, Terminal terminal, int precio, int tarifa, int puntos){
        ArrayList<Activo> activos = new ArrayList<>();
        for (int i= 0; i<cantidad; i++){
            activos.add(new Activo(nombre, terminal, precio, tarifa, puntos));
        }return activos;
    }

    public Lote crearLoteDeCompraDeActivos(String nombreDelLote, TipoDeActivo nombre, int cantidad, Terminal terminal, int precio, int tarifa, int puntos){
        ArrayList<Activo> activos = this.crearActivosParaLote(nombre, cantidad, terminal, precio, tarifa, puntos);
        Lote lote = new Lote(activos, terminal, nombreDelLote);
        for (int i = 0; i<activos.size(); i++){
            this.adjudicarLoteAlActivo(lote, activos.get(i));
        }
        return lote;
    }

    private void adjudicarLoteAlActivo(Lote lote, Activo activo) {
        activo.cambiarLote(lote);
    }

    public void adjudicarActivoAlCliente(Cliente c, TipoDeActivo tipodeactivo, Lote lote){
        c.setActivoEnUso(lote.activosDelLote.get(1)); //Codigo a cambiar, solo para probar
    }
}
