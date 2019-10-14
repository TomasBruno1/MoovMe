import java.util.ArrayList;

public abstract class Zona {
String nombre;
int valor;
ArrayList<Lote> lotes;

    public String getNombre() {
        return nombre;
    }

    public void agregarLote (Lote unLote){
        lotes.add(unLote);
    }

    public void eliminarLote (Lote unLote){
        lotes.remove(unLote);
    }

    public int getValor(){
    return valor;
}
}
