import java.io.Serializable;

public class TipoDeActivo implements Serializable {
    public static final long serialVersionUID = 9L;

    String nombre;
    public TipoDeActivo(String tipo){
        nombre= tipo;
    }

    public String getNombre() {
        return nombre;
    }
}
