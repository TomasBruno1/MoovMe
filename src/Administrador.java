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

    public void multarCliente(Cliente c){
        Multa multa = new Multa(c);
        c.multa = multa;
    }
    public ArrayList<Activo> crearActivosParaLote(TipoDeActivo nombre, int cantidad, Zona zona, int precio, int tarifa){
        ArrayList<Activo> activos = new ArrayList<>();
        for (int i= 0; i<cantidad; i++){
            activos.add(new Activo(nombre, zona, precio, tarifa));
        }return activos;
    }

    public Lote crearLoteDeCompraDeActivos(String nombreDelLote, TipoDeActivo nombre, int cantidad, Zona zona, int precio, int tarifa){
        ArrayList<Activo> activos = this.crearActivosParaLote(nombre, cantidad, zona, precio, tarifa);
        return new Lote(activos, zona, nombreDelLote);
    }


    public void adjudicarActivoAlCliente(Cliente c, TipoDeActivo tipodeactivo, Lote lote){
        c.setActivoEnUso(lote.activosDelLote.get(1)); //Codigo a cambiar, solo para probar
    }
}
