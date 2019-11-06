import java.io.Serializable;

public class TipoDeActivo implements Serializable {
    String nombre;
    public TipoDeActivo(String tipo){
        nombre= tipo;
    }

    public String getNombre() {
        return nombre;
    }
}
