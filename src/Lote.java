import java.util.ArrayList;

public class Lote {

    ArrayList<Activo> activosDelLote;
    Zona zona;
    String nombreDelLote;
    public static int codigo = 1;
    int codigoReal;

    public Lote(ArrayList<Activo> activos, Zona zona, String nombre){
        activosDelLote = activos;
        this.zona = zona;
        nombreDelLote = nombre;
        codigoReal = this.codigo++;
    }

    public int getCodigo(){
        return codigoReal;
    }

    public Activo getActivoPorCodigo(int codigo){
        for(int i= 0; i<this.activosDelLote.size(); i++){
            if(activosDelLote.get(i).getCodigo()== codigo){
                return activosDelLote.get(i);
            }
        }return null;
    }

    public Zona getZona(){
        return zona;
    }
}
