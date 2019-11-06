import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Lote implements Serializable {

    ArrayList<Activo> activosDelLote;
    Terminal terminal;
    String nombreDelLote;
    int codigoLote;
    //todo Cambiar responsabilidad del codigo al operador, por persistencia

    public Lote(ArrayList<Activo> activos, Terminal terminal, String nombre, int codigo){
        activosDelLote = activos;
        this.terminal = terminal;
        nombreDelLote = nombre;
        codigoLote = codigo;
    }

    public String getNombreDelLote() {
        return nombreDelLote;
    }

    public int getCodigo(){
        return codigoLote;
    }

    public Activo getActivoPorCodigo(int codigo) throws IOException {
        for(int i= 0; i<this.activosDelLote.size(); i++){
            if(activosDelLote.get(i).getCodigo()== codigo){
                return activosDelLote.get(i);
            }
        }throw new IOException("Activo no encontrado");
    }

    public Terminal getTerminal(){
        return terminal;
    }

    public ArrayList<Activo> getActivosDelLote() {
        return activosDelLote;
    }

    public int getCantidadDeActivos(){
        return getActivosDelLote().size();
    }
}
