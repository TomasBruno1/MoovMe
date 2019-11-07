import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class Terminal implements Serializable {
    Zona zona;
    ArrayList<Lote> lotes;
    String nombre;

    public Terminal(Zona zona, String nombre){
        this.zona = zona;
        zona.agregarTerminal(this);
        this.nombre = nombre;
        lotes = new ArrayList<>();
    }

    public Zona getZona(){
        return this.zona;
    }

    public void agregarLote (Lote unLote){
        lotes.add(unLote);
    }

    public void eliminarLote (Lote unLote){
        lotes.remove(unLote);
    }

    public ArrayList<Lote> getLotes() {
        return lotes;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Activo>  activosDeTerminal(){
        ArrayList<Activo> activos = new ArrayList<>();
        for (Lote lote : lotes) {
            for (Activo activo : lote.getActivosDelLote()){
                if(activo.getStatus().getCodigoStatus()==1){
                    activos.add(activo);
                }
            }
        }
        return activos;
    }

    public ArrayList<Activo>  activosDeTerminalPorTipo(TipoDeActivo unTipoDeActivo){
        ArrayList<Activo> activos = new ArrayList<>();
        for (Lote lote : lotes) {
            for (Activo activo : lote.getActivosDelLote()){
                if(activo.getStatus().getCodigoStatus()==1 && activo.getTipoDeActivo().getNombre().equals(unTipoDeActivo.getNombre())){
                    activos.add(activo);
                }
            }
        }
        return activos;
    }

    public void mostrarActivosDeTerminal() {
        HashSet<String> nombresDeTipoDeActivo = new HashSet<String>();
        for (Activo activo : activosDeTerminal()) {
            if(activo.getStatus().getCodigoStatus()==1){
                nombresDeTipoDeActivo.add(activo.getTipoDeActivo().getNombre());
            }
        }
        System.out.println(nombresDeTipoDeActivo.toString());
    }
}