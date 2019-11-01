import java.util.ArrayList;
import java.util.HashSet;

public abstract class Zona {
String nombre;
int valor;
ArrayList<Lote> lotes;
HashSet<Terminal> terminales;

    public String getNombre() {
        return nombre;
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

    public ArrayList<Activo>  activosDeZona(){
        ArrayList<Activo> activos = new ArrayList<>();
        for (Lote lote : lotes) {
            for (Activo activo :
                    lote.getActivosDelLote()){
                activos.add(activo);
            }
        }
        return activos;
    }

    public void mostrarActivosDeZona() {
        HashSet<String> nombresDeTipoDeActivo = new HashSet<String>();
        for (Activo activo :
                activosDeZona()) {
            nombresDeTipoDeActivo.add(activo.getNombre().getNombre());
        }
        System.out.println(nombresDeTipoDeActivo.toString());
    }

    public int getValor(){
    return valor;
}

    public void agregarTerminal(Terminal terminal){
        terminales.add(terminal);
    }

    public void eliminarTerminal(Terminal terminal){
        terminales.remove(terminal);
    }
}
